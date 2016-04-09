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
package com.github.jonathanxd.spongeandbukkitbridge;


import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.loader.ClassLoadEvent;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.CommonPluginLoader;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.PluginLoader;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.dependency.CommonDependencySolver;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.dependency.DependencySolver;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Reflection;

import java.io.File;

public class SBBridge implements ISBBridge {

    private static final Implementation implementation = null;
    private static final PluginLoader loader = null;

    public static Implementation getImplementation() {
        return SBBridge.implementation;
    }

    @SuppressWarnings("ConstantConditions")
    public void init(Implementation implementation) throws IllegalStateException {
        if (SBBridge.implementation == null && SBBridge.loader == null) {
            Reflection.setField(null, SBBridge.class, "implementation", implementation);
            Reflection.setField(null, SBBridge.class, "loader", new CommonPluginLoader(implementation, new CommonDependencySolver()));
        } else {
            throw new IllegalStateException("Already initialized!");
        }

        initListeners();
        initConvergPlugins(implementation.getPluginsFolder());
        //initOtherPlugins(implementation)
    }

    private void initListeners() {
        implementation.getIEventManager().register(ClassLoadEvent.class, new SBListener(implementation));
    }

    public static DependencySolver getDependecySolver() {
        return loader.getSolver();
    }

    private void initConvergPlugins(File pluginsFolder) {
        loader.indexPlugins(pluginsFolder, ".jar");
        loader.loadAll();
    }
}
