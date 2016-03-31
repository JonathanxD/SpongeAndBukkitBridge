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
package com.github.jonathanxd.yfuncutil.reflection;

/**
 * Created by jonathan on 23/03/16.
 */
public enum Modifier {

    PUBLIC(java.lang.reflect.Modifier.PUBLIC), PRIVATE(java.lang.reflect.Modifier.PRIVATE), PROTECTED(java.lang.reflect.Modifier.PROTECTED),
    STATIC(java.lang.reflect.Modifier.STATIC), FINAL(java.lang.reflect.Modifier.FINAL), SYNCHRONIZED(java.lang.reflect.Modifier.SYNCHRONIZED),
    VOLATILE(java.lang.reflect.Modifier.VOLATILE), TRANSIENT(java.lang.reflect.Modifier.TRANSIENT), NATIVE(java.lang.reflect.Modifier.NATIVE),
    INTERFACE(java.lang.reflect.Modifier.INTERFACE), ABSTRACT(java.lang.reflect.Modifier.ABSTRACT), STRICT(java.lang.reflect.Modifier.STRICT);

    private final int modifier;

    Modifier(int modifier) {
        this.modifier = modifier;
    }

    public int getModifier() {
        return modifier;
    }

    public boolean test(int mod) {
        return (mod & this.getModifier()) != 0;
    }
}
