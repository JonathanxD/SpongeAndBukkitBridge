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
package com.github.jonathanxd.spongeandbukkitbridge.api.text;

import com.github.jonathanxd.spongeandbukkitbridge.api.text.color.Colors;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.ColorTextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.StringTextComponent;
import com.github.jonathanxd.spongeandbukkitbridge.api.text.components.TextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 27/03/16.
 */
public final class Text {

    private final TextComponent[] components;

    Text(TextComponent[] components) {
        this.components = components;
    }

    /**
     * Create a text instance, parameter {@code objects} accept {@link String} and {@link Colors}
     * @param objects
     * @return
     */

    public static Text of(Object... objects) {

        List<TextComponent> textComponents = new ArrayList<>();

        for(Object o : objects) {
            if(o instanceof String) {
                textComponents.add(new StringTextComponent(String.valueOf(o)));
            }else if(o instanceof Colors) {
                textComponents.add(new ColorTextComponent((Colors) o));
            } else {
                throw new RuntimeException("Invalid Text input '"+o+"' read the docs!");
            }
        }

        TextComponent[] textComponentsArray = textComponents.toArray(new TextComponent[textComponents.size()]);

        return new Text(textComponentsArray);
    }


    public TextComponent[] getComponents() {
        return components.clone();
    }
}
