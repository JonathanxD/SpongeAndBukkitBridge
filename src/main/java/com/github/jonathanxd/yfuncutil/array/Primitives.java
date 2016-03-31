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

/**
 * Created by jonathan on 30/03/16.
 */
public class Primitives {
    public static Byte[] fromPrimitive(byte[] primitive) {
        Byte[] bytes = new Byte[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            bytes[x] = primitive[x];
        }

        return bytes;
    }

    public static Short[] fromPrimitive(short[] primitive) {
        Short[] shorts = new Short[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            shorts[x] = primitive[x];
        }

        return shorts;
    }

    public static Integer[] fromPrimitive(int[] primitive) {
        Integer[] integers = new Integer[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            integers[x] = primitive[x];
        }

        return integers;
    }

    public static Long[] fromPrimitive(long[] primitive) {
        Long[] primitives = new Long[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            primitives[x] = primitive[x];
        }

        return primitives;
    }

    public static Float[] fromPrimitive(float[] primitive) {
        Float[] primitives = new Float[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            primitives[x] = primitive[x];
        }

        return primitives;
    }

    public static Double[] fromPrimitive(double[] primitive) {
        Double[] primitives = new Double[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            primitives[x] = primitive[x];
        }

        return primitives;
    }

    public static Boolean[] fromPrimitive(boolean[] primitive) {
        Boolean[] primitives = new Boolean[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            primitives[x] = primitive[x];
        }

        return primitives;
    }


    public static Character[] fromPrimitive(char[] primitive) {
        Character[] primitives = new Character[primitive.length];

        for (int x = 0; x < primitive.length; ++x) {
            primitives[x] = primitive[x];
        }

        return primitives;
    }


    public static byte[] toPrimitive(Byte[] noPrimitive) {
        byte[] a2 = new byte[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }

    public static short[] toPrimitive(Short[] noPrimitive) {
        short[] a2 = new short[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }

    public static int[] toPrimitive(Integer[] noPrimitive) {
        int[] a2 = new int[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }

    public static long[] toPrimitive(Long[] noPrimitive) {
        long[] a2 = new long[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }

    public static float[] toPrimitive(Float[] noPrimitive) {
        float[] a2 = new float[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }

    public static double[] toPrimitive(Double[] noPrimitive) {
        double[] a2 = new double[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }

    public static boolean[] toPrimitive(Boolean[] noPrimitive) {
        boolean[] a2 = new boolean[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }


    public static char[] toPrimitive(Character[] noPrimitive) {
        char[] a2 = new char[noPrimitive.length];

        for (int x = 0; x < noPrimitive.length; ++x) {
            a2[x] = noPrimitive[x];
        }
        return a2;
    }
}
