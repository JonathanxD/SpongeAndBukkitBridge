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
package com.github.jonathanxd.spongeandbukkitbridge.api.entities;

/**
 * Created by jonathan on 20/01/16.
 */
public interface Entity {

    // TODO EntityType getType();

    // TODO World getWorld();

    // TODO Location<World> getLocation();

    // TODO void setLocation(Location<World> location);

    // TODO getRotation();

    // TODO void setRotation(rotation);

    // TODO void setLocationAndRotation(Location<World> location, rotation);

    // TODO Optional<Entity> getPassenger();

    // TODO boolean setPassenger(Entity entity);

    // TODO Optional<Entity> getVehicle();

    // TODO boolean setVehicle(Entity entity);

    // TODO Entity getBaseVehicle();

    // TODO default double getVelocity();

    // TODO boolean setVelocity(Vector3d vector3d);

    // TODO boolean isOnGround();

    // TODO boolean isRemoved();

    // TODO boolean isLoaded();

    // TODO void remove();

    // TODO damage(double damage, Source damageSource)?

    // TODO default Collection<Entity> getNearbyEntities(double distance);

    // TODO default Collection<Entity> getNearbyEntities(Predicate<Entity> predicate);

    // TODO Optional<UUID> getCreator();

    // TODO Optional<UUID> getNotifier();

    // TODO void setCreator(@Nullable UUID uuid);

    // TODO void setNotifier(@Nullable UUID uuid);

    // TODO default boolean canSee(Entity entity);

}
