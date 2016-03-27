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
package com.github.jonathanxd.spongeandbukkitbridge.statics;

import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.PluginContainer;

/**
 * Created by jonathan on 27/03/16.
 */
public abstract class AbstractImplementation implements Implementation {

    private final List<PluginHolder<?>> pluginList = new ArrayList<>();

    public File getPluginsFolder() {
        return CommonProperties.PLUGIN_DIR;
    }

    @Override
    public void pluginLoadStart(PluginHolder<?> pluginHolder) {
        onPluginLoadStart(pluginHolder);
    }

    @Override
    public boolean loadPlugin(PluginHolder<?> pluginHolder) {

        boolean load = onPluginLoad(pluginHolder);

        if(load) {
            pluginList.add(pluginHolder);
        }

        return load;
    }

    public abstract void onPluginLoadStart(PluginHolder<?> pluginHolder);
    public abstract boolean onPluginLoad(PluginHolder<?> pluginHolder);

    @Override
    public Optional<LoggerSB<?>> getLoggerFor(Object plugin) {
        Optional<PluginContainer<?>> plugin1 = getPlugin(plugin);

        if(plugin1.isPresent()) {
            return Optional.of(plugin1.get().getLogger());
        }

        return null;
    }

    @Override
    public Optional<PluginContainer<?>> getPlugin(Object instance) {
        Optional<PluginHolder<?>> any = pluginList.stream().filter(data -> data.getPluginInstance().equals(instance)).findAny();

        if(any.isPresent())
            return Optional.of(any.get().getPluginContainer());

        return Optional.empty();
    }

    @Override
    public Optional<PluginContainer<?>> getPlugin(String id) {
        Optional<PluginHolder<?>> any = pluginList.stream().filter(data -> data.getPluginDataBuilder().getId().equals(id)).findAny();

        if(any.isPresent())
            return Optional.of(any.get().getPluginContainer());

        return null;
    }
}
