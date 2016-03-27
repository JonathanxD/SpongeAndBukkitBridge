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
package com.github.jonathanxd.spongeandbukkitbridge.api.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.priority.Priority;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Reflection;

/**
 * Created by jonathan on 20/01/16.
 */
/**
 * TODO: Rewrite
 */

public class ListenerData implements Comparable<ListenerData> {

    private final Object instance;
    private final Method method;
    private final int position;
    private final Class<?> eventClass;
    private final List<Class<?>> parameters;
    private final Priority priority;

    public ListenerData(Object instance, Method method, int position, Class<?> eventClass, List<Class<?>> parameters, Priority priority) {
        this.instance = instance;
        this.method = method;
        this.position = position;
        this.eventClass = eventClass;
        this.parameters = parameters;
        this.priority = priority;
    }

    public static void invoke(ListenerData data, Event event) {
        Method m = data.getMethod();
        Class<?> eventClass = data.getEventClass();
        List<Class<?>> parameters = data.getParameters();

        if(!eventClass.isAssignableFrom(event.getClass()))
            throw new IllegalMonitorStateException("Non-compatible events: "+eventClass+" <-> "+event.getClass());

        List<Object> invoke = new ArrayList<>();

        for(int x = 0; x < parameters.size(); ++x) {
            Class<?> param = parameters.get(x);
            Optional<Object> paramValue = event.involved().find(param, x);
            if(!paramValue.isPresent())
                throw new IllegalMonitorStateException("Cannot find parameter: "+param);
            invoke.add(paramValue.get());
        }

        invoke.add(data.position, event);

        Object[] invokeObjects = invoke.toArray(new Object[invoke.size()]);
        try {
            m.invoke(data.getInstance(), invokeObjects);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static Collection<ListenerData> fromObject(Object obj) {
        Collection<ListenerData> list = new HashSet<>();
        list.addAll(processMethods(obj, obj.getClass().getDeclaredMethods()));
        list.addAll(processMethods(obj, obj.getClass().getMethods()));
        return list;
    }

    private static List<ListenerData> processMethods(Object obj, Method[] methods) {
        List<ListenerData> list = new ArrayList<>();
        for (Method m : methods) {
            for (Annotation annotation : m.getAnnotations()) {
                if (annotation.annotationType() == Listener.class) {

                    Listener listener = (Listener) annotation;
                    Priority priority = listener.priority();
                    Class<?> eventClass = null;
                    List<Class<?>> parameters = new ArrayList<>();

                    int position = 0;

                    Class<?>[] parameterTypes = m.getParameterTypes();
                    for(int x = 0; x < parameterTypes.length; ++x) {
                        Class<?> paramType = parameterTypes[x];
                        if (Event.class.isAssignableFrom(paramType)) {
                            eventClass = paramType;
                            position = x;
                        } else {
                            parameters.add(paramType);
                        }
                    }

                    if (eventClass != null) {
                        list.add(new ListenerData(obj, m, position, eventClass, parameters, priority));
                    } else {
                        throw new RuntimeException("Error, cannot find event parameter, see parameters: " + parameters);
                    }
                    continue;
                }
            }
        }
        return list;
    }

    public void invoke(Event event) {
        invoke(this, event);
    }

    public Object getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getEventClass() {
        return eventClass;
    }

    public List<Class<?>> getParameters() {
        return parameters;
    }

    public Priority getPriority() {
        return priority;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return compareTo((ListenerData) obj) == 0;
    }

    @Override
    public int hashCode() {
        return method.hashCode();
    }

    @Override
    public int compareTo(ListenerData o) {
        return Reflection.compareHashes(method.hashCode(), o.hashCode());
    }
}
