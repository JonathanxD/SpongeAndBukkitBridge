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
package com.github.jonathanxd.yfuncutil.array;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Created by jonathan on 23/03/16.
 */
public class ClassArray {

    public static boolean testAnyMatchable(Class<?>[] toTest, Class<?>[] superClasses) {
        if (superClasses.length > toTest.length)
            throw new IllegalArgumentException("Incompatible sizes 'superClasses `need be` <= toTest'!");

        Class<?>[] copy = superClasses.clone();

        for (Class<?> aToTest : toTest) {
            for (int y = 0; y < copy.length; ++y) {
                if (copy[y].isAssignableFrom(aToTest)) {
                    copy[y] = null;
                    break;
                }
            }
        }

        for(Class<?> c : copy) {
            if(c != null) {
                return false;
            }
        }

        return true;
    }

    public static boolean testAllMatchable(Class<?>[] toTest, Class<?>[] superClasses) {
        if (toTest.length != superClasses.length)
            throw new IllegalArgumentException("Incompatible sizes!");


        for (int x = 0; x < toTest.length; ++x) {
            if (!Objects.requireNonNull(superClasses[x])
                    .isAssignableFrom(Objects.requireNonNull(toTest[x]))) {
                return false;
            }
        }

        return true;
    }

    public static Class<?>[] fromTypeToClassArray(Type[] types) {
        Class<?>[] classes = new Class<?>[types.length];

        for (int x = 0; x < types.length; ++x) {
            try {
                classes[x] = Class.forName(types[x].getTypeName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return classes;
    }

    public static Class<?>[] fromAnnotationToClassArray(Annotation[] annotations) {
        Class<?>[] classes = new Class<?>[annotations.length];

        for (int x = 0; x < annotations.length; ++x) {
            classes[x] = annotations[x].annotationType();
        }

        return classes;
    }

    public static Class<?>[] fromThrowableToClassArray(Throwable[] throwables) {
        Class<?>[] classes = new Class<?>[throwables.length];

        for (int x = 0; x < throwables.length; ++x) {
            classes[x] = throwables[x].getClass();
        }

        return classes;
    }

}
