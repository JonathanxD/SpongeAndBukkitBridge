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
package com.github.jonathanxd.spongeandbukkitbridge.plugin;

import java.lang.annotation.Annotation;

import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.Plugin;

/**
 * Created by jonathan on 20/01/16.
 */
public class PluginDataBuilder {

    private final String name;
    private final String id;
    private final String version;
    private final String[] dependencies;
    private final Class<?> mainClass;

    public PluginDataBuilder(String name, String id, String version, String[] dependencies, Class<?> mainClass) {
        this.name = name;
        this.id = id;
        this.version = version;
        this.dependencies = dependencies;
        this.mainClass = mainClass;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public Class<?> getMainClass() {
        return mainClass;
    }

    public static PluginDataBuilder fromAnnotation(Class<?> annotationIn, Annotation annotation) {
        if(annotation.annotationType() == Plugin.class) {
            Plugin plugin = (Plugin) annotation;
            return new PluginDataBuilder(plugin.name(), plugin.id(), plugin.version(), plugin.dependencies(), annotationIn);
        }else{
            throw new ClassCastException("Cannot cast "+annotation.annotationType()+" to "+Plugin.class);
        }
    }
}
