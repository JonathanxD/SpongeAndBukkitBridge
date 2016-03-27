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
package com.github.jonathanxd.spongeandbukkitbridge.api.logging;

/**
 * Created by jonathan on 20/01/16.
 */
public abstract class LoggerSB<T> {
    private final T loggerBase;

    public LoggerSB(T loggerBase) {
        this.loggerBase = loggerBase;
    }

    public T getLoggerBase() {
        return loggerBase;
    }

    public abstract void log(LogData data);

    public void log(Level level, String format, Object... args) {
        log(LogData.of(level, format, args));
    }

    public void info(String format, Object... args) {
        log(LogData.of(Level.INFO, format, args));
    }

    public void warning(String format, Object... args) {
        log(Level.WARN, format, args);
    }

    public void debug(String format, Object... args) {
        log(Level.DEBUG, format, args);
    }

    public void exception(Throwable ex, String format, Object... args) {
        log(LogData.of(Level.ERROR, format, args, ex));
    }

}
