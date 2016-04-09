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

import com.github.jonathanxd.spongeandbukkitbridge.api.plugin.Plugin;
import com.github.jonathanxd.spongeandbukkitbridge.exceptions.IncompatibleDependencyFound;
import com.github.jonathanxd.spongeandbukkitbridge.utils.Format;

import java.lang.annotation.Annotation;

/**
 * Created by jonathan on 20/01/16.
 */
public class PluginData {

    private final String name;
    private final String id;
    private final String version;
    private final Dependency[] dependencies;
    private final Class<?> mainClass;

    public PluginData(String name, String id, String version, Dependency[] dependencies, Class<?> mainClass) {
        this.name = name;
        this.id = id;
        this.version = version;
        this.dependencies = dependencies;
        this.mainClass = mainClass;
    }

    public static PluginData fromAnnotation(Class<?> annotationIn, Annotation annotation) {
        if (annotation.annotationType() == Plugin.class) {
            Plugin plugin = (Plugin) annotation;
            return new PluginData(plugin.name(), plugin.id(), plugin.version(), Dependency.fromAnnotation(plugin.dependencies()), annotationIn);
        } else {
            throw new ClassCastException("Cannot cast " + annotation.annotationType() + " to " + Plugin.class);
        }
    }

    public boolean hasDependencyFor(PluginData another) {
        for(Dependency dependency : getDependencies()) {
            if(dependency.getId().equals(another.getId()))
                return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PluginData)) {
            return false;
        }

        PluginData pluginData = (PluginData) obj;

        return this.getId().equals(pluginData.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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

    public Dependency[] getDependencies() {
        return dependencies;
    }

    public Class<?> getMainClass() {
        return mainClass;
    }

    public static class Dependency {
        private final String id;
        private final String versionRegex;
        private final String excludedVersionsRegex;
        private final boolean isOptional;

        public Dependency(String id, String versionRegex, String excludedVersionsRegex, boolean isOptional) {
            this.id = id;
            this.versionRegex = versionRegex;
            this.excludedVersionsRegex = excludedVersionsRegex;
            this.isOptional = isOptional;
        }

        public static Dependency[] fromAnnotation(com.github.jonathanxd.spongeandbukkitbridge.api.plugin.Dependency[] annotations) {
            Dependency[] dependencies = new Dependency[annotations.length];

            for (int x = 0; x < annotations.length; ++x) {
                com.github.jonathanxd.spongeandbukkitbridge.api.plugin.Dependency annotation = annotations[x];
                dependencies[x] = new Dependency(annotation.name(), annotation.version(), annotation.incompatibleVersions(), annotation.isOptional());
            }

            return dependencies;
        }

        public static boolean test(String testingVersion, String versionRegex, String excludedVersionsRegex) {
            return (excludedVersionsRegex.isEmpty() || !testingVersion.matches(excludedVersionsRegex)) && (testingVersion.isEmpty() || testingVersion.matches(versionRegex));
        }

        public String getId() {
            return id;
        }

        public String getVersionRegex() {
            return versionRegex;
        }

        public boolean isOptional() {
            return isOptional;
        }

        public boolean testVersion(PluginData testing, PluginData pluginData) {
            if (pluginData.getId().equals(this.getId())) {
                boolean result;
                if (!(result = test(pluginData.getVersion(), this.versionRegex, this.excludedVersionsRegex)) && !this.isOptional()) {

                    String exceptionMessage = Format.format("Plugin '${requiredPlugin}' require plugin '${dependency}', " +
                                    "but, current version of '${dependency}' is incompatible. " +
                                    "Required Version Pattern: '${requiredVersion}', " +
                                    "Excluded Versions Pattern: '${excludedVersions}'. " +
                                    "Found Version: '${dependencyVersion}'",
                            Format.Variables.var("requiredPlugin", testing.getId())
                                    .set("dependency", pluginData.getId())
                                    .set("requiredVersions", this.versionRegex)
                                    .set("excludedVersions", this.excludedVersionsRegex)
                                    .set("excludedVersions", this.excludedVersionsRegex)
                                    .set("dependencyVersion", pluginData.getVersion()));

                    throw new IncompatibleDependencyFound(exceptionMessage);
                }

                return result;
            }

            throw new RuntimeException("Incompatible ids. Id: "+this.getId()+" != "+pluginData.getId());
        }

        @Override
        public boolean equals(Object obj) {

            if(!(obj instanceof Dependency)) {
                return false;
            }

            Dependency dependency = (Dependency) obj;

            return dependency.getId().equals(this.getId());
        }
    }
}
