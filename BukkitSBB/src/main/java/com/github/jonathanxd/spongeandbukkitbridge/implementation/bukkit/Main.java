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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit;

import com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.impl.BukkitImplementation;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jonathanxd.spongeandbukkitbridge.ConvergSB;
import com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.listener.AllListener;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;

public class Main extends JavaPlugin {

    private final Implementation implementation;
    private static Plugin base;

    public Main() {
        base = this;
        implementation = new BukkitImplementation();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AllListener(this), this);
        ConvergSB convergSB = new ConvergSB();
        convergSB.init(implementation);
    }

    public static Plugin getBase() {
        return base;
    }

    public Implementation getImplementation() {
        return implementation;
    }
}
