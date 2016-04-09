/*
 *      BukkitSBB - Bukkit Implementation of SpongeAndBukkitBridge <https://github.com/JonathanxD/WCommands>
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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.impl;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.manager.EventManagerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.AbstractIIEventManager;
import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.IEventManager;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.impl.logging.LoggerSBBukkit;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginData;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginHolder;
import com.github.jonathanxd.spongeandbukkitbridge.statics.AbstractImplementation;

import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by jonathan on 20/01/16.
 */
public class BukkitImplementation extends AbstractImplementation {
    private static final LoggerSB logger = new LoggerSBImpl();
    private static final IEventManager imanager = new AbstractIIEventManager() {
    };
    private final EventManagerSB manager = new EventManagerSBImpl(this);

    @Override
    public String getName() {
        return Bukkit.getName();
    }

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public String getGameVersion() {
        return "1.7.9";
    }

    @Override
    public void onPluginLoadStart(PluginHolder<?> pluginHolder) {
        pluginHolder.setLoggerSB(new LoggerSBBukkit(pluginHolder.getPluginData(), Logger.getGlobal()));
    }

    @Override
    public boolean onPluginLoad(PluginHolder<?> pluginHolder) {

        PluginData pluginData = pluginHolder.getPluginData();

        System.out.println("Loading plugin: Name = " + pluginData.getName());
        System.out.println("Loading plugin: Id = " + pluginData.getId());
        System.out.println("Loading plugin: Version = " + pluginData.getVersion());
        System.out.println("Loading plugin: Dependencies = " + Arrays.asList(pluginData.getDependencies()));
        return true;
    }

    @Override
    public LoggerSB getLogger() {
        return logger;
    }

    @Override
    public EventManagerSB getEventManager() {
        return manager;
    }

    @Override
    public IEventManager getIEventManager() {
        return imanager;
    }
}
