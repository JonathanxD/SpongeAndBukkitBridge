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
package com.github.jonathanxd.yfuncutil;

import com.github.jonathanxd.yfuncutil.box.IBox;
import com.github.jonathanxd.yfuncutil.box.primitives.mutable.LongMutableBox;
import com.github.jonathanxd.yfuncutil.iterate.LoopState;
import com.github.jonathanxd.yfuncutil.stream.iterate.ForEach;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        strings.add("Bkp");
        strings.add("0090");
        strings.add("S0P3R 0N3");
        for(int x = 0; x < 500; ++x) {
            strings.add("S0P3R 0N3 & "+(x*2));
        }

        IBox<String> stringBox = ForEach.breakableForEach(strings.stream(), (s, index) -> index == 9 ? LoopState.BREAK : LoopState.DEFAULT);

        System.out.println("String box: "+stringBox.getValue());

        LongMutableBox longMutableBox = new LongMutableBox(9);

        Long boxed = longMutableBox.boxedValue();

        System.out.println("Boxed! "+boxed);
    }


    public static Byte[] toBoxed(byte[] bytes) {
        Byte[] bytes1 = new Byte[bytes.length];

        for (int x = 0; x < bytes.length; ++x) {
            bytes1[x] = bytes[x];
        }

        return bytes1;
    }
}
