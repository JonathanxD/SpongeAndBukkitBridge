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
package com.github.jonathanxd.spongeandbukkitbridge.api.events.manager;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.Event;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.Listener;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.ListenerAnnotationImpl;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.data.EventListenerData;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.listener.EventListener;
import com.github.jonathanxd.spongeandbukkitbridge.api.events.manager.caller.EventCaller;
import com.github.jonathanxd.spongeandbukkitbridge.api.logging.LoggerSB;
import com.github.jonathanxd.spongeandbukkitbridge.statics.Implementation;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jonathan on 27/03/16.
 */
public abstract class AbstractEventManager implements EventManagerSB {

    private static final Set<EventListenerData> listeners = new TreeSet<>(new EventListenerData.Comparator());

    private final Implementation implementation;
    private final LoggerSB<?> logger;
    private final EventCaller eventCaller;

    protected AbstractEventManager(Implementation implementation) {
        this.implementation = implementation;
        this.logger = implementation.getLogger();
        this.eventCaller = new EventCaller(implementation);
    }

    @Override
    public <E extends Event, T extends EventListener<E>> void registerEvent(Object plugin, Class<E> eventClass, T eventHandler, ListenerAnnotationImpl listenerAnnotation) {
        EventListenerData data = new EventListenerData(plugin, eventHandler, eventClass, listenerAnnotation, (Executor.GenericExecutor<E>) eventHandler::onEvent);

        listeners.add(data);


    }

    private void add(Object plugin, Object listener) {
        for (Method m : listener.getClass().getDeclaredMethods()) {
            Listener listenerAnnotation;
            if ((listenerAnnotation = m.getDeclaredAnnotation(Listener.class)) != null) {
                listeners.add(new EventListenerData(plugin, listener, null, listenerAnnotation, new Executor.MethodExecutor(m)));
            }
        }
    }

    @Override
    public void registerListener(Object plugin, Object listener) {
        add(plugin, listener);
    }

    @Override
    public void callEvent(Event event) {
        eventCaller.call(event, listeners);
    }

}
