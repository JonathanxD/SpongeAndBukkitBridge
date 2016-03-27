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

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by jonathan on 27/03/16.
 */
public class LogData {
    private final String format;
    private final Object[] args;
    private final Throwable throwable;
    private final Level level;

    public LogData(Level level, String format, Object[] args, Throwable throwable) {
        this.format = format;
        this.args = args;
        this.throwable = throwable;
        this.level = level;
    }

    public LogData(Level level, String format, Object... args) {
        this(level, format, args, null);
    }

    public LogData(Level level, String formatted, Throwable throwable) {
        this(level, formatted, (Object[]) null, throwable);
    }

    public LogData(Level level, String formatted) {
        this(level, formatted, (Object[]) null, null);
    }

    public String getFormat() {
        return format;
    }

    public Optional<Object[]> getArgs() {
        return Optional.ofNullable(args);
    }

    public Optional<Throwable> getThrowable() {
        return Optional.ofNullable(throwable);
    }

    public Level getLevel() {
        return level;
    }

    public String getFormatted() {

        Optional<Object[]> argsOptional = getArgs();

        if(argsOptional.isPresent()) {
            return String.format(format, argsOptional.get());
        }else{
            return format;
        }
    }

    @Override
    public String toString() {
        return "LogData["
                +"format="+getFormat()
                +", args="+(getArgs().isPresent() ? Arrays.toString(getArgs().get()) : Optional.empty())
                +", throwable="+(getThrowable().isPresent() ? getThrowable().get() : Optional.empty())
                +", level="+getLevel()
                +"]";

    }

    public static LogData of(Level level, String format, Object[] args, Throwable throwable) {
        return new LogData(level, format, (Object[]) args, throwable);
    }

    public static LogData of(Level level, String format, Object... args) {
        return new LogData(level, format, args);
    }

    public static LogData of(Level level, String formatted, Throwable throwable) {
        return new LogData(level, formatted, throwable);
    }

    public static LogData of(Level level, String formatted) {
        return new LogData(level, formatted);
    }
}
