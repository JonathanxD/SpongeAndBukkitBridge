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

import com.github.jonathanxd.yfuncutil.box.EmptyBox;
import com.github.jonathanxd.yfuncutil.function.ByteUnaryOperator;

/**
 * Created by jonathan on 23/03/16.
 */

/**
 * Optimized to {@link byte} primitive.
 */
public class ByteMutableBox implements EmptyBox<Byte> {

    private byte value = 0;
    private boolean valid = true;

    public ByteMutableBox() {
    }

    public ByteMutableBox(byte value) {
        this.value = value;
    }

    /**
     * Get current box value
     *
     * @return Value to get from box
     */
    public byte getValue() {
        EmptyBox.checkValid(this);

        return this.value;
    }

    /**
     * Set box value
     *
     * @param value Value to set to box
     */
    public void setValue(byte value) {
        EmptyBox.checkValid(this);

        this.value = value;
    }

    /**
     * Add 1 and return old value.
     *
     * @return Old value
     */
    public byte getAndAdd() {
        byte v = this.getValue();
        this.add((byte) 1);
        return v;
    }

    /**
     * Add i and return old value
     *
     * @param i value to add
     * @return Old value
     */
    public byte getAndAdd(byte i) {
        byte v = this.getValue();
        this.add(i);
        return v;
    }

    /**
     * Subtract i and return old value
     *
     * @param i value to subtract
     * @return Old value
     */
    public byte getAndSubtract(byte i) {
        byte v = this.getValue();
        this.subtract(i);
        return v;
    }

    /**
     * Divide i and return old value
     *
     * @param i value to divide
     * @return Old value
     */
    public byte getAndDivide(byte i) {
        byte v = this.getValue();
        this.divide(i);
        return v;
    }

    /**
     * Multiply i and return old value
     *
     * @param i value to multiply
     * @return Old value
     */
    public byte getAndMultiply(byte i) {
        byte v = this.getValue();
        this.multiply(i);
        return v;
    }

    /**
     * Apply operator and return old value
     *
     * @param operator Operator to apply
     * @return Old value
     */
    public byte getAndApply(ByteUnaryOperator operator) {
        byte v = this.getValue();
        this.apply(operator);
        return v;
    }

    /**
     * Add 1 and return result (current value + 1)
     *
     * @return Result (current value + 1)
     */
    public byte addAndGet() {
        this.add((byte) 1);
        return this.getValue();
    }

    /**
     * Add i and return the result
     *
     * @param i Value to add
     * @return Result
     */
    public byte addAndGet(byte i) {
        this.add(i);
        return this.getValue();
    }

    /**
     * Subtract i and return the result
     *
     * @param i Value to subtract
     * @return Result
     */
    public byte subtractAndGet(byte i) {
        this.subtract(i);
        return this.getValue();
    }

    /**
     * Divide i and return the result
     *
     * @param i Value to divide
     * @return Result
     */
    public byte divideAndGet(byte i) {
        this.divide(i);
        return this.getValue();
    }

    /**
     * Multiply i and return the result
     *
     * @param i Value to multiply
     * @return Result
     */
    public byte multiplyAndGet(byte i) {
        this.multiply(i);
        return this.getValue();
    }

    /**
     * Apply operator and return result
     *
     * @param operator Operator to apply
     * @return Result
     */
    public int applyAndGet(ByteUnaryOperator operator) {
        this.apply(operator);
        return this.getValue();
    }

    /**
     * Add 1 to value
     */
    public void add() {
        this.add((byte) 1);
    }

    /**
     * Add i to box value
     *
     * @param i Value to add to Box value
     */
    public void add(byte i) {
        this.setValue((byte) (this.getValue() + i));
    }

    /**
     * Subtract i from box value
     *
     * @param i Value to subtract from Box value
     */
    public void subtract(byte i) {
        this.setValue((byte) (this.getValue() - i));
    }

    /**
     * Multiply i with box value
     *
     * @param i Value to multiply with Box value
     */
    public void multiply(byte i) {
        this.setValue((byte) (this.getValue() * i));
    }

    /**
     * Divide i with box value
     *
     * @param i Value to divide with Box value
     */
    public void divide(byte i) {
        this.setValue((byte) (this.getValue() / i));
    }

    /**
     * Apply a unary operator to box value
     *
     * @param byteUnaryOperator Unary operator to apply to box value;
     */
    public void apply(ByteUnaryOperator byteUnaryOperator) {
        this.setValue(byteUnaryOperator.applyAsByte(getValue()));
    }

    @Override
    public Byte boxedValue() throws UnsupportedOperationException {
        return getValue();
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
