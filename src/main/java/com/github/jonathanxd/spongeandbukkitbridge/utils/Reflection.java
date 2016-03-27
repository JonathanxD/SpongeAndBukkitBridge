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
package com.github.jonathanxd.spongeandbukkitbridge.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

/**
 * Created by jonathan on 18/01/16.
 */
public class Reflection {

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getField(Object instance, String fieldName) {
        return getField(instance, instance.getClass(), fieldName);
    }

    public static Optional<Field> getFieldWithValue(Object instance, Object value) {

        for(Field f : instance.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                if(f.get(instance).equals(value)) {
                    return Optional.of(f);
                }
            } catch (Exception ignored) {}
        }

        return Optional.empty();
    }

    public static Optional<Field> getField(Class<?> instanceClass, String fieldName) {
        try{
            Field declaredField = instanceClass.getDeclaredField(fieldName);
            if(!declaredField.isAccessible())
                declaredField.setAccessible(true);

            return Optional.of(declaredField);
        } catch(Exception ignored){}

        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getField(Object instance, Class<?> instanceClass, String fieldName) {
        try{
            Field declaredField = instanceClass.getDeclaredField(fieldName);
            if(!declaredField.isAccessible())
                declaredField.setAccessible(true);

            return Optional.of((T) declaredField.get(instance));
        } catch(Exception ignored){}

        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public static boolean setField(Object instance, String fieldName, Object newValue) {
        return setField(instance, instance.getClass(), fieldName, newValue);
    }

    @SuppressWarnings("unchecked")
    public static boolean setField(Object instance, Class<?> instanceClass, String fieldName, Object newValue) {
        try{
            Field declaredField = instanceClass.getDeclaredField(fieldName);
            if(!declaredField.isAccessible())
                declaredField.setAccessible(true);

            int mods = declaredField.getModifiers();
            if(Modifier.isFinal(mods)){
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(declaredField, declaredField.getModifiers() & ~Modifier.FINAL);
            }

            declaredField.set(instance, newValue);
            return true;
        } catch(Exception ignored){
            ignored.printStackTrace();
        }

        return false;
    }

    public static int compareHashes(int hash, int hash2) {
        return hash == hash2 ? 0 : hash > hash2 ? 1 : -1;
    }

    public static Method getMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        for(Method m : clazz.getDeclaredMethods()) {
            try {
                Annotation annotation1 = m.getAnnotation(annotation);
                if(annotation1 != null)
                    return m;
            }catch(Exception ignored){}
        }
        return null;
    }

    public static Object invoke(Object instance, Class<? extends Annotation> annotation) {
        Method m = getMethod(instance.getClass(), annotation);
        if(m != null) {
            try {
                return m.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
