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
package com.github.jonathanxd.yfuncutil.box.primitives.immutable.array;

import com.github.jonathanxd.yfuncutil.array.Primitives;
import com.github.jonathanxd.yfuncutil.box.EmptyBox;

/**
 * Created by jonathan on 23/03/16.
 */

/**
 * Optimized to {@link byte} primitive.
 */
public class ByteArrayBox implements EmptyBox<Byte[]> {

    private final byte[] value;
    private boolean valid = true;

    public ByteArrayBox() {
        this(new byte[0]);
    }

    public ByteArrayBox(byte[] value) {
        this.value = value;
    }

    /**
     * Get current box value
     *
     * @return Value to get from box
     */
    public byte[] getValue() throws UnsupportedOperationException {
        EmptyBox.checkValid(this);

        return this.value;
    }

    @Override
    public Byte[] boxedValue() throws UnsupportedOperationException {
        return Primitives.fromPrimitive(getValue());
    }

    @Override
    public boolean isValid() {
        return this.valid;
    }

    @Override
    public void invalidate() throws UnsupportedOperationException {
        EmptyBox.checkValid(this);
        this.valid = false;
    }
}
