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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.impl.logger;

import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;

import org.slf4j.Logger;

import com.github.jonathanxd.spongeandbukkitbridge.api.logging.Level;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LogData;

/**
 * Created by jonathan on 27/03/16.
 */
public class LoggerSBImpl extends LoggerSB<Logger> {


    public LoggerSBImpl(Logger loggerBase) {
        super(loggerBase);
    }

    @Override
    public void log(LogData data) {
        logLevel(data.getLevel(), data, data.getThrowable().orElse(null));
    }

    private void logLevel(Level level, LogData logData, Throwable error) {

        Logger loggerBase = getLoggerBase();

        String message = logData.getFormatted();

        switch (level) {
            case INFO: {
                if (error == null)
                    loggerBase.info(message);
                else
                    loggerBase.info(message, error);

                break;
            }
            case DEBUG: {
                if (error == null)
                    loggerBase.debug(message);
                else
                    loggerBase.debug(message, error);

                break;
            }

            case ERROR: {
                if (error == null)
                    loggerBase.error(message);
                else
                    loggerBase.error(message, error);

                break;
            }

            case WARN: {
                if (error == null)
                    loggerBase.warn(message);
                else
                    loggerBase.warn(message, error);

                break;
            }

            default: {
                if (error == null)
                    loggerBase.info(message);
                else
                    loggerBase.info(message, error);
            }
        }
    }


}
