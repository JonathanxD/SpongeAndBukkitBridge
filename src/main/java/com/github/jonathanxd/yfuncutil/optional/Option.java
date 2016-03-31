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
package com.github.jonathanxd.yfuncutil.optional;

import com.github.jonathanxd.yfuncutil.yif.Else;
import com.github.jonathanxd.yfuncutil.yif.ResultOrElse;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by jonathan on 23/03/16.
 */
public class Option {


    /**
     * Return first optional if present, or second otherwise
     *
     * @param first  First optional
     * @param second Second optional
     * @param <T>    Type
     * @return Return first optional if present, or second otherwise
     */
    public static <T> Optional<T> orElse(Optional<T> first, Optional<T> second) {
        if (!first.isPresent())
            return second;
        else
            return first;
    }

    /**
     * Return first optional if present, or second if {@code expr} is true
     *
     * @param first  First optional
     * @param second Second optional
     * @param expr   Expression
     * @param <T>    Type
     * @return Return first optional if present, or second if {@code expr} is true
     */
    public static <T> Optional<T> orElseAnd(Optional<T> first, boolean expr, Optional<T> second) {

        if (expr && !first.isPresent()) {
            return second;
        }

        return first;
    }

    public static <T> ElseOptional<T> ifPresent(Optional<T> optional, Consumer<T> consumer) {

        Objects.requireNonNull(optional);

        if(optional.isPresent()) {
            consumer.accept(optional.get());
            return new ElseOptional<>(null);
        } else {
            return new ElseOptional<>(optional);
        }
    }

    public static <T, R> ResultOrElseOptional<T, R> ifPresent(Optional<T> optional, Function<T, R> function) {

        Objects.requireNonNull(optional);

        if(optional.isPresent()) {
            R ret = function.apply(optional.get());
            return new ResultOrElseOptional<>(null, ret);
        } else {
            return new ResultOrElseOptional<>(optional, null);
        }
    }

    public static class ResultOrElseOptional<T, R> implements ResultOrElse<Optional<T>, R> {

        private final Optional<T> optional;
        private final Optional<R> result;

        public ResultOrElseOptional(Optional<T> optional, R result) {
            this.optional = optional;
            this.result = Optional.ofNullable(result);
        }


        @Override
        public Optional<R> resultOrElse(Consumer<Optional<T>> consumer) {
            if(optional != null)
                consumer.accept(optional);

            return result;
        }

    }

    public static class ElseOptional<T> implements Else<Optional<T>> {

        private final Optional<T> optional;

        public ElseOptional(Optional<T> optional) {
            this.optional = optional;
        }

        @Override
        public void otherwise(Consumer<Optional<T>> consumer) {
            if(optional != null)
                consumer.accept(optional);
        }
    }
}
