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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.sponge;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.manager.EventManagerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.entity.livingentity.player.PlayerInteractEvent;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.sponge.entity.humanoid.SpongePlayerWrapper;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;

import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;

/**
 * Created by jonathan on 27/03/16.
 */
public class EventListener {

    private final Implementation implementation;
    private final EventManagerSB eventManagerSB;

    public EventListener(Implementation implementation) {
        this.implementation = implementation;
        this.eventManagerSB = implementation.getEventManager();
    }

    @Listener
    public void interact(InteractBlockEvent event, @First Player player) {

        PlayerInteractEvent event1 = new PlayerInteractEvent(new SpongePlayerWrapper(player));

        eventManagerSB.callEvent(event1);

        event.setCancelled(event1.isCancelled());

    }


}
