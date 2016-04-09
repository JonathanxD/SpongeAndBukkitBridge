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
package com.github.jonathanxd.spongeandbukkitbridge.api.events.achievement;

import com.github.jonathanxd.spongeandbukkitbridge.api.entities.living.player.Player;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.Cancellable;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.entity.livingentity.player.PlayerEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.stats.Achievement;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Holder;

/**
 * Created by jonathan on 01/04/16.
 */
public class GrantAchievementEvent implements PlayerEvent, Cancellable{

    private final Player player;
    private final Achievement achievement;
    private final Holder holder;
    private boolean cancelled;

    public GrantAchievementEvent(Player player, Achievement achievement) {
        this.player = player;
        this.achievement = achievement;
        this.holder = Holder.of(player, achievement);
    }


    public Achievement getAchievement() {
        return achievement;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public Holder involved() {
        return holder;
    }
}
