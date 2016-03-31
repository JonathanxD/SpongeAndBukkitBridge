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
 * Empty Box is used by primitive boxes to improve performance.
 */
public interface EmptyBox<T> {

    /**
     * Check box validation
     *
     * @param box Box
     * @throws UnsupportedOperationException If is invalid!
     */
    static void checkValid(EmptyBox<?> box) throws UnsupportedOperationException {
        if (!box.isValid()) {
            throw new UnsupportedOperationException("Invalid Box, make sure the code has not any problems.");
        }
    }

    /**
     * Convert to a boxed value
     *
     * @return Boxed value
     * @throws UnsupportedOperationException If box is invalid or method is not implemented!
     */
    default T boxedValue() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("boxedValue");
    }

    /**
     * Convert to a {@link Box}
     *
     * @return A instance of {@link Box}
     * @throws UnsupportedOperationException If box is invalid!
     */
    default IBox<T> toBox() throws UnsupportedOperationException {
        checkValid(this);

        return new Box<>(boxedValue());
    }

    /**
     * Convert to a {@link IMutableBox}
     *
     * @return A instance of {@link IMutableBox}
     * @throws UnsupportedOperationException If box is invalid!
     */
    default IMutableBox<T> toMutableBox() throws UnsupportedOperationException {
        checkValid(this);

        return new MutableBox<>(boxedValue());
    }

    /**
     * True if box is valid, false otherwise
     *
     * @return True if box is valid, false otherwise
     */
    boolean isValid();

    /**
     * Invalidate is a technical purpose method, if you invalidate this box, classes cannot call
     * {@link IBox#getValue()} or this method. Invalid you no guarantee access to the object, if use
     * reflection you can validate this box or get the value. If the object invalidation is not
     * implemented, this method will have no effects!
     *
     * @throws UnsupportedOperationException If this box is invalid!
     */
    void invalidate() throws UnsupportedOperationException;


}
