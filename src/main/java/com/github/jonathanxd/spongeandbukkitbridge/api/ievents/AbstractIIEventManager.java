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
package com.github.jonathanxd.spongeandbukkitbridge.api.ievents;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonathan on 30/03/16.
 */
public abstract class AbstractIIEventManager implements IEventManager {
    private final Map<Class<? extends IEvent>, Set<IListener<? extends IEvent>>> listeners = new HashMap<>();

    @Override
    public <E extends IEvent, T extends IListener<E>> void register(Class<E> eventClass, T listener) {
        add(eventClass, listener);
    }

    @SuppressWarnings("unchecked")
    private void add(Class<? extends IEvent> clazz, IListener<? extends IEvent> listener) {
        if (!listeners.containsKey(clazz)) {
            listeners.put(clazz, new HashSet<>());
        }

        listeners.get(clazz).add(listener);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends IEvent> E call(E event) {

        listeners.forEach((k, v) -> {
            if(k.isAssignableFrom(event.getClass())) {
                for(IListener<? extends  IEvent> listener : v) {
                    help((IListener<E>) listener, event);
                }
            }
        });

        return event;
    }

    private <E extends IEvent> void help(IListener<E> listener, E event) {
        listener.onEvent(event);
    }
}
