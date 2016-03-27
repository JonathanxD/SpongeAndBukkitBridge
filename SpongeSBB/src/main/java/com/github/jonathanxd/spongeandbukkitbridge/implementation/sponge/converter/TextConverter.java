/*
 *      SpongeSBB - Sponge Implementation of SpongeAndBukkitBridge <https://github.com/JonathanxD/WCommands>
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
package com.github.jonathanxd.spongeandbukkitbridge.implementation.sponge.converter;

import com.github.jonathanxd.spongeandbukkitbridge.api.text.Text;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.color.Colors;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.ColorTextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.StringTextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.TextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Reflection;

import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jonathan on 27/03/16.
 */
public class TextConverter {


    public static org.spongepowered.api.text.Text convert(Text text) {
        org.spongepowered.api.text.Text.Builder builder = org.spongepowered.api.text.Text.builder();

        List<Object> currentTexts = new ArrayList<>();

        for(TextComponent component : text.getComponents()) {
            if(component instanceof ColorTextComponent) {

                builder.append(org.spongepowered.api.text.Text.of(currentTexts.toArray()));

                currentTexts.clear();

                currentTexts.add(org.spongepowered.api.text.Text.of(convert((ColorTextComponent) component)));
            }
            if(component instanceof StringTextComponent) {
                String content = ((StringTextComponent) component).getContent();

                currentTexts.add(content);
            }
        }

        if(!currentTexts.isEmpty()) {
            builder.append(org.spongepowered.api.text.Text.of(currentTexts.toArray()));

            currentTexts.clear();
        }

        return builder.build();
    }

    public static TextColor convert(ColorTextComponent colorTextComponent) {
        Colors color = colorTextComponent.getColor();

        Optional<Field> fieldWithValue = Reflection.getFieldWithValue(null, Colors.class, color);

        if (fieldWithValue.isPresent()) {
            Field field = fieldWithValue.get();

            Optional<TextColor> colorOptional = Reflection.getField(null, TextColors.class, field.getName().toUpperCase());

            if (colorOptional.isPresent()) {
                return colorOptional.get();
            }

        }

        return null;

    }

}
