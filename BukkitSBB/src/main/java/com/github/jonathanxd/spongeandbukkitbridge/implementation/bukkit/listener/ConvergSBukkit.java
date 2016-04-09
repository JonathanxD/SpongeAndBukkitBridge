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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.listener;

import java.util.Optional;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.Event;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.achievement.GrantAchievementEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.entity.livingentity.player.PlayerInteractEvent;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.impl.achievement.BukkitAchievement;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.impl.bukkit.entity.living.BukkitPlayer;

/**
 * Created by jonathan on 20/01/16.
 */
public class ConvergSBukkit {

    public static Optional<Event> convert(org.bukkit.event.Event event){

        if(event instanceof org.bukkit.event.player.PlayerInteractEvent) {
            org.bukkit.event.player.PlayerInteractEvent interactEvent = (org.bukkit.event.player.PlayerInteractEvent) event;
            PlayerInteractEvent otherEvent = new PlayerInteractEvent(new BukkitPlayer(interactEvent.getPlayer()));
            return Optional.of(otherEvent);
        }

        if(event instanceof org.bukkit.event.player.PlayerAchievementAwardedEvent) {
            org.bukkit.event.player.PlayerAchievementAwardedEvent achievementAwardedEvent = (org.bukkit.event.player.PlayerAchievementAwardedEvent) event;

            GrantAchievementEvent otherEvent = new GrantAchievementEvent(new BukkitPlayer(achievementAwardedEvent.getPlayer()), BukkitAchievement.get(achievementAwardedEvent.getAchievement()));

            return Optional.of(otherEvent);

        }

        return Optional.empty();
    }

}
