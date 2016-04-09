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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.impl.logging;

import com.github.jonathanxd.spongeandbukkitbridge.api.logging.Level;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LogData;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginData;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by jonathan on 27/03/16.
 */
public class LoggerSBBukkit extends LoggerSB<Logger> {
    private final PluginData pluginData;


    public LoggerSBBukkit(PluginData pluginData, Logger logger) {
        super(logger);
        this.pluginData = pluginData;
    }

    @SuppressWarnings("Duplicates")
    private static java.util.logging.Level wrap(Level level) {

        switch (level) {
            case DEBUG: {
                return java.util.logging.Level.FINE;
            }
            case INFO: {
                return java.util.logging.Level.INFO;
            }
            case ERROR: {
                return java.util.logging.Level.SEVERE;
            }
            case WARN: {
                return java.util.logging.Level.WARNING;
            }
        }

        return java.util.logging.Level.ALL;
    }

    @Override
    public void log(LogData data) {

        Optional<Throwable> throwableOptional = data.getThrowable();
        String tag = "["+ pluginData.getName()+"] ";
        if(throwableOptional.isPresent()) {
            getLoggerBase().log(wrap(data.getLevel()), tag + data.getFormatted(), throwableOptional.get());
        }else{
            getLoggerBase().log(wrap(data.getLevel()), tag + data.getFormatted());
        }


    }
}
