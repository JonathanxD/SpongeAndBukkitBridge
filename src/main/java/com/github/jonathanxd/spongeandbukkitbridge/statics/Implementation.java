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

import com.github.jonathanxd.spongeandbukkitbridge.SBBridge;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.manager.EventManagerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.AbstractIIEventManager;
import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.IEventManager;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.PluginContainer;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginHolder;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.dependency.DependencySolver;

import java.io.File;
import java.util.Optional;

/**
 * Created by jonathan on 20/01/16.
 */
public interface Implementation {

    /**
     * Implementation Name
     *
     * @return Implementation Name
     */
    String getName();

    /**
     * Implementation Version
     *
     * @return Implementation Version
     */
    String getVersion();

    /**
     * Game version (like: 1.9)
     *
     * @return Game version
     */
    String getGameVersion();

    /**
     * Plugins directory (like: converg/)
     *
     * @return Plugins directory
     */
    File getPluginsFolder();

    /**
     * Called when plugin load start. (Normally register the logger and all instances necessary)
     * @param pluginHolder Plugin Holder
     */
    void pluginLoadStart(PluginHolder<?> pluginHolder);

    /**
     * Called during plugin registration
     *
     * @param pluginHolder Plugin holder
     * @return True to accept plugin, false to reject
     */
    boolean loadPlugin(PluginHolder<?> pluginHolder);

    /**
     * Get global logger
     *
     * @return Global Logger
     */
    LoggerSB<?> getLogger();

    /**
     * Get logger for plugin
     *
     * @param plugin Plugin
     * @return Plugin Logger
     */
    Optional<LoggerSB<?>> getLoggerFor(Object plugin);

    /**
     * Get IEventManager
     *
     * @return IEventManager
     */
    EventManagerSB getEventManager();

    IEventManager getIEventManager();

    Optional<PluginContainer<?>> getPlugin(Object instance);

    Optional<PluginContainer<?>> getPlugin(String id);

    default DependencySolver getDependencySolver() {
        return SBBridge.getDependecySolver();
    }
}
