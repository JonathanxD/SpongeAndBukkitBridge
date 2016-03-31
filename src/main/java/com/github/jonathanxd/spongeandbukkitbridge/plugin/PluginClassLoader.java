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

import com.github.jonathanxd.spongeandbukkitbridge.api.events.classloader.FindingClassEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.loader.ClassLoadEvent;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by jonathan on 30/03/16.
 */
public class PluginClassLoader extends URLClassLoader {

    private final File pluginFile;
    private final Implementation implementation;

    public PluginClassLoader(URL[] urls, ClassLoader parent, File pluginFile, Implementation implementation) {
        super(urls, parent);
        this.pluginFile = pluginFile;
        this.implementation = implementation;
    }

    public Class<?> defineClass(String name, byte[] data) {
        return defineClass(name, data, 0, data.length);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        ClassLoadEvent event = new ClassLoadEvent(name, this);
        implementation.getIEventManager().call(event);

        if (event.isCancelled()) {
            throw new ClassNotFoundException(name);
        }

        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        ClassLoadEvent event = new ClassLoadEvent(name, this);
        implementation.getIEventManager().call(event);

        if (event.isCancelled()) {
            throw new ClassNotFoundException(name);
        }

        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        ClassLoadEvent event = new ClassLoadEvent(name, this);
        implementation.getIEventManager().call(event);

        if (event.isCancelled()) {
            throw new ClassNotFoundException(name);
        }

        return super.findClass(name);
    }

    public File getPluginFile() {
        return pluginFile;
    }
}
