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
package com.github.jonathanxd.spongeandbukkitbridge.api.ievents.loader;

import com.github.jonathanxd.spongeandbukkitbridge.api.ievents.IEvent;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginClassLoader;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Holder;
import com.github.jonathanxd.yfuncutil.box.primitives.mutable.array.ByteArrayMutableBox;

/**
 * Created by jonathan on 30/03/16.
 */
public class ByteClassEvent implements IEvent {

    private final String name;
    private final ByteArrayMutableBox byteArrayBox;
    private final PluginClassLoader pluginClassLoader;

    private final Holder holder;

    public ByteClassEvent(String name, ByteArrayMutableBox byteArrayBox, PluginClassLoader pluginClassLoader) {
        this.name = name;
        this.byteArrayBox = byteArrayBox;
        this.pluginClassLoader = pluginClassLoader;
        this.holder = Holder.of(name, byteArrayBox, pluginClassLoader);
    }


    public ByteArrayMutableBox getByteArrayBox() {
        return byteArrayBox;
    }

    /**
     * Get value of {@link #byteArrayBox}
     * @return Byte array contained in {@link #byteArrayBox}
     */
    public byte[] getBytesBox() {
        return byteArrayBox.getValue();
    }

    public String getName() {
        return name;
    }

    public PluginClassLoader getPluginClassLoader() {
        return pluginClassLoader;
    }

    @Override
    public Holder involved() {
        return holder;
    }
}
