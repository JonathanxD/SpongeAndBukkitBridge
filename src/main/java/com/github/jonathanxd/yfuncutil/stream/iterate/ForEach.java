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
package com.github.jonathanxd.yfuncutil.stream.iterate;

import com.github.jonathanxd.yfuncutil.box.IBox;
import com.github.jonathanxd.yfuncutil.box.IMutableBox;
import com.github.jonathanxd.yfuncutil.box.MutableBox;
import com.github.jonathanxd.yfuncutil.box.primitives.mutable.BooleanMutableBox;
import com.github.jonathanxd.yfuncutil.box.primitives.mutable.IntMutableBox;
import com.github.jonathanxd.yfuncutil.breaks.BreakException;
import com.github.jonathanxd.yfuncutil.iterate.LoopState;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * Created by jonathan on 23/03/16.
 */
public class ForEach {

    /**
     * Ordered and Indexed foreach
     *
     * @param stream   Stream to loop
     * @param consumer Consumer to receive Object and Index
     * @param <E>      Element type
     */
    public static <E> void indexedForEach(Stream<? extends E> stream, BiConsumer<E, Integer> consumer) {
        IntMutableBox count = new IntMutableBox();
        stream.forEachOrdered(e -> consumer.accept(e, count.getAndAdd()));
    }

    /**
     * Ordered, indexed and breakable Foreach. This method creates a new Thread that will run the
     * loop.
     *
     * If loop is break, value will be returned but the thread will be alive waiting end of the loop
     * (I don't wan't to force stop the thread because it is not good).
     *
     * @param stream   Stream to loop
     * @param function Function to receive Object, Index and return state
     * @param <E>      Element Type
     * @return A Box Holding null or the value that Function break loop.
     */
    public static <E> IBox<E> breakableForEach(Stream<? extends E> stream, BiFunction<E, Integer, LoopState> function) {

        final IMutableBox<E> box = new MutableBox<>();

        final IntMutableBox count = new IntMutableBox();
        final BooleanMutableBox bool = new BooleanMutableBox(true);
        try{
            stream.forEachOrdered(e -> {
                if (bool.getValue()) {
                    LoopState state = function.apply(e, count.getAndAdd());
                    if (state == LoopState.BREAK) {
                        bool.setValue(false);
                        box.setValue(e);
                        throw new BreakException();
                    }
                }
            });
        }catch (BreakException ignored) {}

        IBox<E> immutableBox = box.toBox();

        box.invalidate();

        return immutableBox;
    }

}
