/*
 *      BukkitSBB - Bukkit Implementation of SpongeAndBukkitBridge <https://github.com/JonathanxD/WCommands>
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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.bukkit.converter;

import com.github.jonathanxd.spongeandbukkitbridge.api.text.Text;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.color.Colors;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.ColorTextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.StringTextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.TextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Reflection;

import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Created by jonathan on 27/03/16.
 */
public class TextConverter {


    public static String convertLegacy(Text text) {

        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : text.getComponents()) {
            if (component instanceof ColorTextComponent) {
                stringBuilder.append(convert((ColorTextComponent) component));
            }
            if (component instanceof StringTextComponent) {
                stringBuilder.append(((StringTextComponent) component).getContent());
            }
        }

        return stringBuilder.toString();

    }

    public static ChatColor convert(ColorTextComponent colorTextComponent) {
        Colors color = colorTextComponent.getColor();
        Optional<Field> fieldWithValue = Reflection.getFieldWithValue(null, Colors.class, color);

        if (fieldWithValue.isPresent()) {
            Field field = fieldWithValue.get();

            return ChatColor.valueOf(field.getName().toUpperCase());
        }

        return null;
    }

}
