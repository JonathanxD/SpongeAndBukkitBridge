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
package com.github.jonathanxd.spongeandbukkitbridge.simpleplugin;

import com.github.jonathanxd.spongeandbukkitbridge.SBBridge;
import com.github.jonathanxd.spongeandbukkitbridge.api.init.EnableEvent;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.Plugin;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;

/**
 * Created by jonathan on 08/04/16.
 */
@Plugin(id = "simple.simpleplugin", name = "SimplePlugin", version = "1.0")
public class SimplePlugin {

    private final Implementation implementation;
    private LoggerSB<?> loggerSB;

    public SimplePlugin() {
        this.implementation = SBBridge.getImplementation();
        // Don't get logger here because logger registration occurs after instantiation
    }

    @EnableEvent
    public void onEnable() {
        // At time has no dependency injection
        this.loggerSB = implementation.getLoggerFor(this).get();
        loggerSB.info("Enabled!");
        implementation.getEventManager().registerListener(this, new EvtListener());
    }

}
