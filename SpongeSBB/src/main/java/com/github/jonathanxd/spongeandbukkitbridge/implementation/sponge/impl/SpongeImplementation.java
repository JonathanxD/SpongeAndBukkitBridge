/*
 *      SpongeSBB - Sponge Implementation of SpongeAndBukkitBridge <https://github.com/JonathanxD/WCommands>
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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.impl;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.manager.EventManagerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.AbstractIIEventManager;
import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.IEventManager;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.impl.event.EventManagerSBImpl;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.impl.logger.LoggerSBImpl;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginDataBuilder;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginHolder;
import com.github.jonathanxd.spongeandbukkitbridge.statics.AbstractImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.Game;

/**
 * Created by jonathan on 27/03/16.
 */
public class SpongeImplementation extends AbstractImplementation {

    private static final String VERSION = "1.0";
    private static final String SPONGE_TARGET_VERSION = "4.0.3";
    private static final String MC_TARGET_VERSION = "1.8.9";
    private static final EventManagerSB manager = new EventManagerSBImpl();
    private static final IEventManager imanager = new AbstractIIEventManager() {};
    private final LoggerSB loggerSB;
    private final Game game;
    private final Logger logger;

    public SpongeImplementation(Game game, Logger logger) {
        this.game = game;
        this.logger = logger;
        this.loggerSB = new LoggerSBImpl(logger);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getVersion() {
        return VERSION + " (Sponge: " + SPONGE_TARGET_VERSION + ", MC: " + MC_TARGET_VERSION + ")";
    }

    @Override
    public String getGameVersion() {
        return game.getPlatform().getMinecraftVersion().getName();
    }

    @Override
    public void onPluginLoadStart(PluginHolder<?> pluginHolder) {
        PluginDataBuilder pluginDataBuilder = pluginHolder.getPluginDataBuilder();

        logger.info(String.format("Loading plugin %s", pluginDataBuilder.getName()));

        pluginHolder.setLoggerSB(new LoggerSBImpl(LoggerFactory.getLogger(pluginDataBuilder.getName())));
    }

    @Override
    public boolean onPluginLoad(PluginHolder<?> pluginHolder) {
        PluginDataBuilder pluginDataBuilder = pluginHolder.getPluginDataBuilder();

        logger.info(String.format("Loaded plugin %s!", pluginDataBuilder.getName()));

        return true;
    }

    @Override
    public LoggerSB getLogger() {
        return loggerSB;
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
