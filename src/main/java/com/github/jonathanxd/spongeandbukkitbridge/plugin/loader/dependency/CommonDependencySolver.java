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
package com.github.jonathanxd.spongeandbukkitbridge.plugin.loader.dependency;

import com.github.jonathanxd.spongeandbukkitbridge.exceptions.CircularDependencyException;
import com.github.jonathanxd.spongeandbukkitbridge.exceptions.DependencyException;
import com.github.jonathanxd.spongeandbukkitbridge.plugin.PluginData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by jonathan on 31/03/16.
 */
public class CommonDependencySolver implements DependencySolver {

    private final Set<PluginData.Dependency> solvedDependencies = new HashSet<>();

    private final Set<PluginData> solvedList = new HashSet<>();
    private final LinkedList<PluginData> solvingList = new LinkedList<>();

    private static String getCircularity(PluginData current, List<PluginData> pluginDatas) {
        StringJoiner sj = new StringJoiner(" -> ");

        for (PluginData pluginData : pluginDatas) {
            sj.add(pluginData.getId());
        }

        sj.add(current.getId());

        return sj.toString();
    }

    @Override
    public void solve(PluginData plugin, List<PluginData> allPlugins) {

        if (isSolving(plugin)) {
            throw new CircularDependencyException("Cannot solve dependency for plugin: '" + solvingList.getFirst().getId() + "'. Circular Dependency: " + getCircularity(plugin, solvingList));
        }

        if (!isSolved(plugin)) {
            if (plugin.getDependencies().length == 0) {
                solvedList.add(plugin);
            } else {
                solvingList.add(plugin);

                PluginData.Dependency[] dependencies = plugin.getDependencies();

                for (PluginData.Dependency dependency : dependencies) {
                    Optional<PluginData> pluginForIdOpt = forId(plugin, dependency.getId(), allPlugins);
                    if (pluginForIdOpt.isPresent()) {

                        PluginData pluginData = pluginForIdOpt.get();

                        if (!isSolved(pluginData)) {
                            solve(pluginData, allPlugins);
                        }

                        boolean test = dependency.testVersion(plugin, pluginData);

                        if (test) {
                            solvedDependencies.add(dependency);
                        }
                    } else if(!dependency.isOptional()){
                        throw new DependencyException("Cannot find required dependency '"+dependency.getId()+"'");
                    }
                }

                solvingList.remove(plugin);
            }
        }
    }

    @Override
    public boolean isSolved(PluginData plugin) {
        return solvedList.contains(plugin);
    }

    @Override
    public boolean isDependencyPresent(PluginData.Dependency dependency) {
        return this.solvedDependencies.contains(dependency);
    }

    @Override
    public boolean isDependencyPresent(String pluginId) {
        return this.solvedDependencies.stream().filter(dependency -> dependency.getId().equals(pluginId)).findAny().isPresent();
    }

    @Override
    public boolean isSolving(PluginData plugin) {
        return solvingList.contains(plugin);
    }

    public Optional<PluginData> forId(PluginData current, String pluginId, List<PluginData> allPlugins) {
        return allPlugins.stream().filter(plugin -> !plugin.equals(current) && plugin.getId().equals(pluginId)).findAny();
    }
}
