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

import com.github.jonathanxd.yfuncutil.array.ClassArray;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.EnumSet;

/**
 * Created by jonathan on 23/03/16.
 */
public final class MethodSpec {

    private final String name;
    private final Class<?> returnType;
    private final Class<?>[] paramTypes;
    private final Class<? extends Annotation>[] anyOfAnnotations;
    private final Class<?>[] anyOfExceptionTypes;
    private final EnumSet<Modifier> modifiers;

    public MethodSpec(String name, Class<?> returnType, Class<?>[] paramTypes, Class<? extends Annotation>[] anyOfAnnotations, Class<?>[] anyOfExceptionTypes, EnumSet<Modifier> modifiers) {
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.anyOfAnnotations = anyOfAnnotations;
        this.anyOfExceptionTypes = anyOfExceptionTypes;
        this.modifiers = modifiers;
    }

    public MethodSpec(String name) {
        this(name, null, null, null, null, null);
    }

    public MethodSpec(String name, Class<?> returnType) {
        this(name, returnType, null, null, null, null);
    }

    public MethodSpec(String name, Class<?> returnType, Class<?>[] paramTypes) {
        this(name, returnType, paramTypes, null, null, null);
    }

    public MethodSpec(String name, Class<?>[] paramTypes, Class<? extends Annotation>[] annotations) {
        this(name, null, paramTypes, annotations, null, null);
    }

    @SuppressWarnings("unchecked")
    public MethodSpec(Class<? extends Annotation> annotations) {
        this(null, null, null, new Class[]{annotations}, null, null);
    }

    public MethodSpec(Class<? extends Annotation>[] annotations) {
        this(null, null, null, annotations, null, null);
    }

    public boolean matches(Method m) {
        try {
            if (this.name != null && !m.getName().equals(this.name)) {
                return false;
            }

            if (this.returnType != null && !m.getReturnType().equals(this.returnType)) {
                return false;
            }

            if (this.paramTypes != null && !ClassArray.testAllMatchable(this.paramTypes, m.getParameterTypes())) {
                return false;
            }

            if (this.anyOfAnnotations != null && !ClassArray.testAnyMatchable(
                /*to test*/
                    ClassArray.fromAnnotationToClassArray(m.getDeclaredAnnotations()),
                /*super*/
                    this.anyOfAnnotations)) {
                return false;
            }

            if (this.anyOfExceptionTypes != null && !ClassArray.testAllMatchable(
                /*to test*/
                    m.getExceptionTypes(),
                /*super*/
                    this.anyOfExceptionTypes)) {
                return false;
            }

            if (this.modifiers != null) {

                int mod = m.getModifiers();

                for (Modifier modifier : modifiers) {
                    if (!modifier.test(mod)) {
                        return false;
                    }
                }
            }


            return true;

        } catch (Throwable t) {
            return false;
        }
    }

}
