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
package com.github.jonathanxd.spongeandbukkitbridge.api.stats;

/**
 * Created by jonathan on 01/04/16.
 */

import com.github.jonathanxd.spongeandbukkitbridge.utils.Reflection;

import java.lang.reflect.Field;
import java.util.Optional;

public class Achievement {

    public static final Achievement OPEN_INVENTORY = new Achievement();
    public static final Achievement MINE_WOOD = new Achievement(OPEN_INVENTORY);
    public static final Achievement BUILD_WORKBENCH = new Achievement(MINE_WOOD);
    public static final Achievement BUILD_PICKAXE = new Achievement(BUILD_WORKBENCH);
    public static final Achievement BUILD_FURNACE = new Achievement(BUILD_PICKAXE);
    public static final Achievement ACQUIRE_IRON = new Achievement(BUILD_FURNACE);
    public static final Achievement BUILD_HOE = new Achievement(BUILD_WORKBENCH);
    public static final Achievement MAKE_BREAD = new Achievement(BUILD_HOE);
    public static final Achievement BAKE_CAKE = new Achievement(BUILD_HOE);
    public static final Achievement BUILD_BETTER_PICKAXE = new Achievement(BUILD_PICKAXE);
    public static final Achievement COOK_FISH = new Achievement(BUILD_FURNACE);
    public static final Achievement ON_A_RAIL = new Achievement(ACQUIRE_IRON);
    public static final Achievement BUILD_SWORD = new Achievement(BUILD_WORKBENCH);
    public static final Achievement KILL_ENEMY = new Achievement(BUILD_SWORD);
    public static final Achievement KILL_COW = new Achievement(BUILD_SWORD);
    public static final Achievement FLY_PIG = new Achievement(KILL_COW);
    public static final Achievement SNIPE_SKELETON = new Achievement(KILL_ENEMY);
    public static final Achievement GET_DIAMONDS = new Achievement(ACQUIRE_IRON);
    public static final Achievement NETHER_PORTAL = new Achievement(GET_DIAMONDS);
    public static final Achievement GHAST_RETURN = new Achievement(NETHER_PORTAL);
    public static final Achievement GET_BLAZE_ROD = new Achievement(NETHER_PORTAL);
    public static final Achievement BREW_POTION = new Achievement(GET_BLAZE_ROD);
    public static final Achievement END_PORTAL = new Achievement(GET_BLAZE_ROD);
    public static final Achievement THE_END = new Achievement(END_PORTAL);
    public static final Achievement ENCHANTMENTS = new Achievement(GET_DIAMONDS);
    public static final Achievement OVERKILL = new Achievement(ENCHANTMENTS);
    public static final Achievement BOOKCASE = new Achievement(ENCHANTMENTS);
    public static final Achievement EXPLORE_ALL_BIOMES = new Achievement(END_PORTAL);
    public static final Achievement SPAWN_WITHER = new Achievement(THE_END);
    public static final Achievement KILL_WITHER = new Achievement(SPAWN_WITHER);
    public static final Achievement FULL_BEACON = new Achievement(KILL_WITHER);
    public static final Achievement BREED_COW = new Achievement(KILL_COW);
    public static final Achievement DIAMONDS_TO_YOU = new Achievement(GET_DIAMONDS);
    public static final Achievement OVERPOWERED = new Achievement(BUILD_BETTER_PICKAXE);


    private final Achievement parent;
    private final String name;

    public Achievement() {
        this(null, null);
    }

    public Achievement(String name) {
        this(null, name);
    }

    public Achievement(Achievement parent) {
        this(parent, null);
    }

    public Achievement(Achievement parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public Optional<Achievement> getParent() {
        return Optional.ofNullable(parent);
    }

    public String getName() {

        if(name == null)
            determineName();

        return name;
    }

    private void determineName() {
        try{
            for(Field f : this.getClass().getDeclaredFields()) {
                if(f.get(null).equals(this)) {
                    Reflection.setField(this, "name", f.getName());
                }
            }
        }catch (Exception ignored){}
    }
}
