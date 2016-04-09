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
package com.github.jonathanxd.spongeandbukkitbridge.api.events.manager.caller;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.Cancellable;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.Event;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.data.EventListenerData;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.manager.Executor;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.PluginContainer;
import com.github.jonathanxd.spongeandbukkitbridge.exceptions.PluginException;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Holder;

import java.lang.reflect.Parameter;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jonathan on 01/04/16.
 */
public class EventCaller {

    private final LoggerSB<?> loggerSB;
    private final Implementation implementation;

    public EventCaller(Implementation implementation) {
        this.implementation = implementation;
        this.loggerSB = implementation.getLogger();
    }

    public void call(Event event, Set<EventListenerData> set) {
        Cancellable cancellable = null;

        if (event instanceof Cancellable) {
            cancellable = (Cancellable) event;
        }


        for (EventListenerData data : set) {

            if(data.canHandle(event.getClass())) {
                try {
                    if (cancellable != null && data.getAnnotation().ignoreIfCancelled() && cancellable.isCancelled()) {
                        continue;
                    }

                    invoke(data.getPlugin(), data.getInstance(), data.getExecutor(), event);
                } catch (Throwable t) {
                    Optional<PluginContainer<?>> pluginHolder = implementation.getPlugin(data.getPlugin());
                    loggerSB.exception(t, "Event call exception. Plugin: '" + pluginHolder.get().getData().getName() + "'");
                }
            }
        }
    }

    private void invoke(Object plugin, Object instance, Executor executor, Event event) {

        Optional<PluginContainer<?>> pluginHolder = implementation.getPlugin(plugin);

        if (!pluginHolder.isPresent()) {
            loggerSB.exception(new PluginException("Cannot find plugin of instance '" + plugin + "'!"), "Plugin not found!");
            return;
        }

        if (executor instanceof Executor.MethodExecutor) {
            Executor.MethodExecutor methodExecutor = (Executor.MethodExecutor) executor;

            Parameter[] parameters = methodExecutor.getMethod().getParameters();
            Object[] objectsToPass = new Object[parameters.length];
            Holder involved = event.involved().clone();

            int offset = 0;
            for (int x = 0; x < parameters.length; ++x) {
                Parameter parameterAtX = parameters[x];
                Class<?> parameterType = parameterAtX.getType();

                if (event.getClass().isAssignableFrom(parameterType)) {
                    objectsToPass[x] = event;
                } else {
                    Optional<Object> optional = involved.findAndRemove(parameterType);
                    if (optional.isPresent()) {
                        objectsToPass[x] = optional.get();
                    } else {
                        return;
                    }
                }
            }

            executor.execute(loggerSB, pluginHolder.get(), event, instance, objectsToPass);
        } else {
            executor.execute(loggerSB, pluginHolder.get(), event, instance);
        }

    }

}
