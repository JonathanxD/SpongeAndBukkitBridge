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
package com.github.jonathanxd.spongeandbukkitbridge.plugin.loader;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.plugin.LoadingClassEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.init.EnableEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.init.EnableFail;
import com.github.jonathanxd.spongeandbukkitbridge.api.init.LoadEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.plugin.PluginLoadStateEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.Plugin;
import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.PluginState;
import com.github.jonathanxd.spongeandbukkitbridge.exceptions.CannotCancelEnableEvent;
import com.github.jonathanxd.spongeandbukkitbridge.exceptions.CannotCancelEnableFailEvent;
import com.github.jonathanxd.spongeandbukkitbridge.exceptions.CannotCancelEvent;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginClassLoader;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginData;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginHolder;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.dependency.DependencyComparator;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.dependency.DependencySolver;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Reflection;
import com.github.jonathanxd.spongeandbukkitbridge.utils.function.ThrowingSupplier;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * Created by jonathan on 31/03/16.
 */
public class CommonPluginLoader implements PluginLoader {

    private final Implementation implementation;
    private final DependencySolver solver;
    private final LoggerSB<?> logger;
    private final Set<PluginLoadData> pluginLoadDatas = new TreeSet<>(new DependencyComparator());

    public CommonPluginLoader(Implementation implementation, DependencySolver solver) {
        this.implementation = implementation;
        this.solver = solver;
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

    @Override
    public void indexPlugins(File directory, String extension) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(extension)) {
                    index(file);
                }
            }
        }
    }

    @Override
    public void loadAll() {

        List<PluginData> allPlugins = pluginLoadDatas.stream().map(PluginLoadData::getPluginData).collect(Collectors.toList());

        for (PluginLoadData pluginLoadData : pluginLoadDatas) {
            solver.solve(pluginLoadData.getPluginData(), allPlugins);
        }



        //solveDependencies();

        for (PluginLoadData pluginLoadData : pluginLoadDatas) {
            File file = pluginLoadData.getFile();

            Class<?> xc = pluginLoadData.getaClass();

            PluginData pluginData = pluginLoadData.getPluginData();

            List<Class<?>> classes = pluginLoadData.getClassList();

            PluginClassLoader cl = pluginLoadData.getPluginClassLoader();

            //////////////////////////////////////////////////
            Reflection.setField(cl, "pluginData", pluginData);

            logger.info("Trying to load plugin '%s'", pluginData.getName());

            if (!onState(pluginData, classes, file, PluginState.ABOUT_TO_LOAD)) {
                logger.info("Skipped plugin %s.", pluginData.getName());
                continue;
            }

            Object instance = tryDo(pluginData, classes, file, (ThrowingSupplier<Object>) xc::newInstance);

            Objects.requireNonNull(instance);

            PluginHolder<?> pluginHolder = new PluginHolder<>(pluginData, instance);

            implementation.pluginLoadStart(pluginHolder);


            tryDo(pluginData, classes, file, (ThrowingSupplier<Object>) () -> Reflection.invoke(instance, LoadEvent.class));


            if (!onState(pluginData, classes, file, PluginState.LOADING)) {
                tryDo(pluginData, classes, file, (ThrowingSupplier<Object>) () -> Reflection.invoke(instance, EnableFail.class));
                continue;
            }


            if (implementation.loadPlugin(pluginHolder)) {

                tryDo(pluginData, classes, file, (ThrowingSupplier<Object>) () -> Reflection.invoke(instance, EnableEvent.class));


                if (!onState(pluginData, classes, file, PluginState.LOADED)) {
                    throw new CannotCancelEnableEvent();
                }
            } else {
                if (!onState(pluginData, classes, file, PluginState.FAILED)) {
                    throw new CannotCancelEnableFailEvent();
                }

                tryDo(pluginData, classes, file, (ThrowingSupplier<Object>) () -> Reflection.invoke(instance, EnableFail.class));

            }
        }
    }

    private void index(File file) {
        List<Class<?>> classes = new ArrayList<>();
        PluginClassLoader cl = null;
        try {

            JarFile jarFile = new JarFile(file);
            Enumeration<?> e = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + file + "!/")};

            cl = new PluginClassLoader(urls, this.getClass().getClassLoader(), file, implementation);

            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();

                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }

                String className = getName(je);

                try {

                    /** Handle event**/
                    LoadingClassEvent event = new LoadingClassEvent(className, cl);
                    implementation.getEventManager().callEvent(event);

                    if (event.isCancelled())
                        continue;

                    jarFile.getInputStream(je);

                    byte[] bytes = getBytes(jarFile.getInputStream(je));

                    Class<?> c = cl.defineClass(className, bytes);

                    classes.add(c);

                } catch (Throwable e2) {
                    //System.err.println("=== Error, dependencies in "+f.getId()+" class: "+className+" error: "+e2.getMessage());
                    //EXCEPTION LOGGIN FOR DEPENDENCIES
                    logger.exception(e2, "Exception during class load in file '%s'", file.getPath());
                }

            }

            jarFile.close();
            cl.close();

        } catch (Throwable t) {
            logger.exception(t, "Exception during load of file '%s'.", file.getPath());
        }

        for (Class<?> clazz : classes) {
            Plugin plugin = null;

            if ((plugin = clazz.getDeclaredAnnotation(Plugin.class)) != null) {
                pluginLoadDatas.add(new PluginLoadData(file, PluginData.fromAnnotation(clazz, plugin), clazz, classes, Objects.requireNonNull(cl, "Null plugin class loader!")));
            }

        }

    }

    private <T> T tryDo(PluginData pluginData, List<Class<?>> classes, File fileToLoad, Supplier<T> runnable) {

        try {
            return runnable.get();
        } catch (Throwable t) {
            if (!onState(pluginData, classes, fileToLoad, PluginState.ERRORED)) {
                throw new CannotCancelEvent();
            }

            return null;
        }

    }

    /**
     * Call Plugin load event
     *
     * @param pluginData Plugin Data
     * @param classes    Plugin jar classes
     * @param fileToLoad File to load
     * @param state      Plugin load state
     * @return True if event is NOT cancelled
     */
    private boolean onState(PluginData pluginData, List<Class<?>> classes, File fileToLoad, PluginState state) {
        return !implementation.getEventManager().call(new PluginLoadStateEvent(pluginData, classes, fileToLoad, state)).isCancelled();
    }

    private String getName(JarEntry jarEntry) {
        String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6); // .class = [., c, l, a, s, s].length = 6
        return className.replace('/', '.');
    }

    @Override
    public DependencySolver getSolver() {
        return solver;
    }
}
