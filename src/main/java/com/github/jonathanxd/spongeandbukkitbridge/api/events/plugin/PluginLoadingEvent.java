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
package com.github.jonathanxd.spongeandbukkitbridge.api.events.plugin;

import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.PluginContainer;

import java.io.File;
import java.util.List;

/**
 * Created by jonathan on 30/03/16.
 */
public class PluginLoadingEvent extends PluginEvent {

    private final List<Class<?>> classes;
    private final File directory;

    public PluginLoadingEvent(PluginContainer<?> pluginContainer, List<Class<?>> classes, File directory) {
        super(pluginContainer);
        this.classes = classes;
        this.directory = directory;

        involved().add(pluginContainer, classes, directory);
    }

    /**
     * Mutable list of plugin classes
     *
     * @return Mutable List of plugin classes
     */
    public List<Class<?>> getClasses() {
        return classes;
    }

    /**
     * Plugin directory
     *
     * @return Plugin directory
     */
    public File getDirectory() {
        return directory;
    }
}
