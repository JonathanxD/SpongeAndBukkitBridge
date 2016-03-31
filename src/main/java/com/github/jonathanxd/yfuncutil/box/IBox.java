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
package com.github.jonathanxd.yfuncutil.box;

/**
 * Created by jonathan on 23/03/16.
 */

/**
 * IBox is a lightweight version of {@link java.util.concurrent.atomic.AtomicReference}
 *
 * This class is not synchronized/thread-safe.
 *
 * This class holds a Object to be accessed later, from a lambda for example.
 */
public interface IBox<T> extends EmptyBox<T> {

    /**
     * Get current value
     *
     * @return Value Current Value
     * @throws UnsupportedOperationException Will throw exception if this Box is not valid
     */
    T getValue() throws UnsupportedOperationException;


    /**
     * Return true if current value is not null
     * @return true if current value is not null
     */
    default boolean notNull() {
        return getValue() != null;
    }

    @Override
    default T boxedValue() throws UnsupportedOperationException {
        EmptyBox.checkValid(this);
        return getValue();
    }
}
