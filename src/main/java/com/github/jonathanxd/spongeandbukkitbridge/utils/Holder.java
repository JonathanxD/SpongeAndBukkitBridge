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
package com.github.jonathanxd.spongeandbukkitbridge.utils;

import com.github.jonathanxd.spongeandbukkitbridge.api.events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by jonathan on 20/01/16.
 */
public class Holder {

    private final List<Object> values;
    private final List<Holder> child = new ArrayList<>();

    public Holder(List<?> values) {
        this.values = new ArrayList<>(values);
    }

    public static Holder of(Object... values) {
        return new Holder(new ArrayList<>(Arrays.asList(values)));
    }

    public static Holder extendToNew(Holder holderToExtend, List<Object> moreValues) {

        List<Object> newList = new ArrayList<>(holderToExtend.values);
        newList.addAll(moreValues);

        return new Holder(newList);
    }

    public List<Holder> getChild() {
        return child;
    }

    /**
     * Child holders aren't passed to events as parameters, is only accessible via {@link #getChild()}
     */
    public void addChild(Holder holder) {
        this.child.add(holder);
    }

    public void add(Object o) {
        values.add(o);
    }

    public void add(Object... o) {
        for(Object os : o) {
            this.add(os);
        }

    }

    public void remove(Object o) {
        values.remove(o);
    }

    public void remove(Object... o) {
        for (Object o1 : o) {
            values.remove(o1);
        }
    }


    @SuppressWarnings("unchecked")
    public Optional<Object> find(Class<?> clazz) {
        return find(clazz, 0);
    }

    @SuppressWarnings("unchecked")
    public Optional<Object> find(Class<?> clazz, int offset) {
        for (int x = 0; x < values.size(); ++x) {
            if (x < offset)
                continue;

            Object value = values.get(x);
            if (clazz.isAssignableFrom(value.getClass()))
                return Optional.of(value);
        }
        return Optional.empty();
    }

    public Optional<Object> findAndRemove(Class<?> clazz) {
        return find(clazz, 0);
    }

    @SuppressWarnings("unchecked")
    public Optional<Object> findAndRemove(Class<?> clazz, int offset) {
        for (int x = 0; x < values.size(); ++x) {
            if (x < offset)
                continue;

            Object value = values.get(x);
            if (clazz.isAssignableFrom(value.getClass())) {
                values.remove(x);
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }


    public Holder extendToNew(Object... moreValues) {
        return this.extendToNew(Arrays.asList(moreValues));
    }

    public Holder extendToNew(List<Object> moreValues) {
        return Holder.extendToNew(this, moreValues);
    }

    @Override
    public Holder clone() {
        return new Holder(new ArrayList<>(this.values));
    }
}
