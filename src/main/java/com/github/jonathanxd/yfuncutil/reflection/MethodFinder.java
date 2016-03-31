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

import com.github.jonathanxd.yfuncutil.optional.Option;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by jonathan on 23/03/16.
 */
public class MethodFinder {

    /**
     * Find method in a class
     * @param methodSpec Method specification
     * @param clazz Class to find method
     * @param analiseSuper Analise super methods
     * @return Method or empty Optional
     */
    public static Optional<Method> find(MethodSpec methodSpec, Class<?> clazz, boolean analiseSuper) {
        return Option.orElseAnd(
                find(methodSpec, clazz.getDeclaredMethods()),
                /* not present and */ analiseSuper,
                /* then */ find(methodSpec, clazz.getMethods()));
    }


    private static Optional<Method> find(MethodSpec methodSpec, Method[] methods) {
        for(Method m : methods) {
            if(methodSpec.matches(m)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }


    public static Object callIgnoringException(Object o, Method m, Object... args) {
        try {
            return Objects.requireNonNull(m)
                    .invoke(Objects.requireNonNull(o), Objects.requireNonNull(args));
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }
}
