/*
 *      SpongeAndBukkitBridge - Sponge & Bukkit Bridge - A Plugin API! <https://github.com/JonathanxD/WCommands>
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2016 TheRealBuggy/JonathanxD (https://github.com/JonathanxD/ & https://github.com/TheRealBuggy/) <jonathan.scripter@programmer.net>
 *      Copyright (c) contributors
 *
 *
 *      Permission is hereby granted, free of charge, to any person obtaining a copy
 *      of this software and associated documentation files (the "Software"), to deal
 *      in the Software without restriction, including without limitation the rights
 *      to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *      copies of the Software, and to permit persons to whom the Software is
 *      furnished to do so, subject to the following conditions:
 *
 *      The above copyright notice and this permission notice shall be included in
 *      all copies or substantial portions of the Software.
 *
 *      THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *      IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *      FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *      AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *      LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *      OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *      THE SOFTWARE.
 */
package com.github.jonathanxd.spongeandbukkitbridge.plugin;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.classloader.ByteClassEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.classloader.LoadingClassEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.init.EnableEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.init.EnableFail;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.init.LoadEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.Plugin;
import com.github.jonathanxd.spongeandbukkitbridge.exceptions.PluginLoadException;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Reflection;
import com.github.jonathanxd.yfuncutil.box.primitives.mutable.array.ByteArrayMutableBox;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by jonathan on 20/01/16.
 */

/**
 * TODO: Rewrite
 */
public class PluginLoader {

    private final Implementation implementation;
    private final LoggerSB logger;

    public PluginLoader(Implementation implementation) {
        this.implementation = implementation;
        this.logger = implementation.getLogger();
    }

    private static byte[] getBytes(InputStream is) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = is.read(buffer)) != -1; )
                os.write(buffer, 0, len);

            os.flush();
            return os.toByteArray();
        }
    }

    public List<Object> load(File fileToLoad) {
        List<Object> mainClasses = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        try {

            JarFile jarFile = new JarFile(fileToLoad);
            Enumeration<?> e = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + fileToLoad + "!/")};

            PluginClassLoader cl = new PluginClassLoader(urls, this.getClass().getClassLoader(), fileToLoad, implementation);

            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();

                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }

                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                try {

                    LoadingClassEvent event = new LoadingClassEvent(className, cl);
                    implementation.getEventManager().callEvent(event);

                    if (event.isCancelled())
                        continue;

                    jarFile.getInputStream(je);

                    byte[] bytes = getBytes(jarFile.getInputStream(je));

                    ByteClassEvent byteClassEvent = new ByteClassEvent(className, new ByteArrayMutableBox(bytes), cl);

                    implementation.getEventManager().callEvent(byteClassEvent);

                    bytes = byteClassEvent.getBytes().getValue();

                    //Class<?> c = Class.forName(className, true, cl);
                    Class<?> c = cl.defineClass(className, bytes);
                    classes.add(c);
                } catch (Throwable e2) {
                    //System.err.println("=== Error, dependencies in "+f.getName()+" class: "+className+" error: "+e2.getMessage());
                    //EXCEPTION LOGGIN FOR DEPENDENCIES
                }

            }

            for (Class<?> xc : classes) {

                if (xc.isAnnotationPresent(Plugin.class)) {
                    final Plugin annotation = xc.getAnnotation(Plugin.class);
                    PluginDataBuilder pluginData = PluginDataBuilder.fromAnnotation(xc, annotation);

                    logger.info("Trying to load plugin '%s'", pluginData.getName());

                    Object instance = xc.newInstance();
                    mainClasses.add(instance);

                    PluginHolder<?> pluginHolder = new PluginHolder<>(pluginData, instance);

                    implementation.pluginLoadStart(pluginHolder);

                    Reflection.invoke(instance, LoadEvent.class);


                    if (implementation.loadPlugin(pluginHolder)) {
                        Reflection.invoke(instance, EnableEvent.class);
                    } else {
                        Reflection.invoke(instance, EnableFail.class);
                    }
                }
            }
            cl.close();
            jarFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(mainClasses.isEmpty()) {
            throw new PluginLoadException("Cannot find main class in file '"+fileToLoad+"'");
        }

        return mainClasses;
    }
}
