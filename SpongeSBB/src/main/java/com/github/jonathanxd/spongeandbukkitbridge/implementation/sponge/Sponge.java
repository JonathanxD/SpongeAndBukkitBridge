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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge;

import com.google.inject.Inject;

import com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.impl.SpongeImplementation;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.github.jonathanxd.spongeandbukkitbridge.ConvergSB;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.sponge.EventListener;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;

/**
 * Created by jonathan on 27/03/16.
 */
@Plugin(id = "convergsb.spongesbb", name = "SpongeSBB", version = "1.0", description = "Sponge implementation of SBB")
public class Sponge {

    private final Implementation implementation;
    private final Game game;
    private final Logger logger;

    @Inject
    public Sponge(Game game, Logger logger) {
        this.game = game;
        this.logger = logger;
        this.implementation = new SpongeImplementation(game, logger);
    }

    @Listener
    public void start(GameInitializationEvent initializationEvent) {

        game.getEventManager().registerListeners(this, new EventListener(implementation));

        ConvergSB convergSB = new ConvergSB();
        convergSB.init(implementation);
    }
}
