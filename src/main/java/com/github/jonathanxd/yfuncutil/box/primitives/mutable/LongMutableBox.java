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
package com.github.jonathanxd.yfuncutil.box.primitives.mutable;

import com.github.jonathanxd.yfuncutil.box.AbstractValidatedBox;
import com.github.jonathanxd.yfuncutil.box.EmptyBox;
import com.github.jonathanxd.yfuncutil.box.primitives.toboxed.ToBoxed;

import java.util.function.LongUnaryOperator;

/**
 * Created by jonathan on 23/03/16.
 */

/**
 * Optimized to {@link long} primitive.
 */
public class LongMutableBox extends AbstractValidatedBox<Long> {

    private long value = 0;
    public LongMutableBox() {
    }

    public LongMutableBox(long value) {
        this.value = value;
    }

    /**
     * Get current box value
     *
     * @return Value to get from box
     */
    @ToBoxed
    public long getValue() {
        EmptyBox.checkValid(this);

        return this.value;
    }

    /**
     * Set box value
     *
     * @param value Value to set to box
     */
    public void setValue(long value) {
        EmptyBox.checkValid(this);

        this.value = value;
    }

    /**
     * Add 1 and return old value.
     *
     * @return Old value
     */
    public long getAndAdd() {
        long v = this.getValue();
        this.add(1);
        return v;
    }

    /**
     * Add i and return old value
     *
     * @param i value to add
     * @return Old value
     */
    public long getAndAdd(long i) {
        long v = this.getValue();
        this.add(i);
        return v;
    }

    /**
     * Subtract i and return old value
     *
     * @param i value to subtract
     * @return Old value
     */
    public long getAndSubtract(long i) {
        long v = this.getValue();
        this.subtract(i);
        return v;
    }

    /**
     * Divide i and return old value
     *
     * @param i value to divide
     * @return Old value
     */
    public long getAndDivide(long i) {
        long v = this.getValue();
        this.divide(i);
        return v;
    }

    /**
     * Multiply i and return old value
     *
     * @param i value to multiply
     * @return Old value
     */
    public long getAndMultiply(long i) {
        long v = this.getValue();
        this.multiply(i);
        return v;
    }

    /**
     * Apply operator and return old value
     *
     * @param operator Operator to apply
     * @return Old value
     */
    public long getAndApply(LongUnaryOperator operator) {
        long v = this.getValue();
        this.apply(operator);
        return v;
    }

    /**
     * Add 1 and return result (current value + 1)
     *
     * @return Result (current value + 1)
     */
    public long addAndGet() {
        this.add(1);
        return this.getValue();
    }

    /**
     * Add i and return the result
     *
     * @param i Value to add
     * @return Result
     */
    public long addAndGet(long i) {
        this.add(i);
        return this.getValue();
    }

    /**
     * Subtract i and return the result
     *
     * @param i Value to subtract
     * @return Result
     */
    public long subtractAndGet(long i) {
        this.subtract(i);
        return this.getValue();
    }

    /**
     * Divide i and return the result
     *
     * @param i Value to divide
     * @return Result
     */
    public long divideAndGet(long i) {
        this.divide(i);
        return this.getValue();
    }

    /**
     * Multiply i and return the result
     *
     * @param i Value to multiply
     * @return Result
     */
    public long multiplyAndGet(long i) {
        this.multiply(i);
        return this.getValue();
    }

    /**
     * Apply operator and return result
     *
     * @param operator Operator to apply
     * @return Result
     */
    public long applyAndGet(LongUnaryOperator operator) {
        this.apply(operator);
        return this.getValue();
    }

    /**
     * Add 1 to value
     */
    public void add() {
        this.add(1);
    }

    /**
     * Add i to box value
     *
     * @param i Value to add to Box value
     */
    public void add(long i) {
        this.setValue(this.getValue() + i);
    }

    /**
     * Subtract i from box value
     *
     * @param i Value to subtract from Box value
     */
    public void subtract(long i) {
        this.setValue(this.getValue() - i);
    }

    /**
     * Multiply i with box value
     *
     * @param i Value to multiply with Box value
     */
    public void multiply(long i) {
        this.setValue(this.getValue() * i);
    }

    /**
     * Divide i with box value
     *
     * @param i Value to divide with Box value
     */
    public void divide(long i) {
        this.setValue(this.getValue() / i);
    }

    /**
     * Apply a unary operator to box value
     *
     * @param operator Unary operator to apply to box value;
     */
    public void apply(LongUnaryOperator operator) {
        this.setValue(operator.applyAsLong(getValue()));
    }

}
