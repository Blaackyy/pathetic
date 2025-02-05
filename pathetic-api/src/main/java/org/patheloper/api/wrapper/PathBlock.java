package org.patheloper.api.wrapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

/**
 * A Class to represent a block in the world, except exempt of Bukkit
 */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public final class PathBlock {

    private final PathPosition pathPosition;
    private final PathBlockType pathBlockType;

    /**
     * @return Whether the block is air
     */
    public boolean isAir() {
        return this.pathBlockType == PathBlockType.AIR;
    }

    /**
     * @return Whether the block is possible to walk through
     */
    public boolean isPassable() {
        return isAir() || this.pathBlockType == PathBlockType.OTHER;
    }

    public boolean isSolid() {
        return this.pathBlockType == PathBlockType.SOLID;
    }

    /**
     * Gets the X coordinate of the block
     *
     * @return The X coordinate of the block
     */
    public int getBlockX() {
        return this.pathPosition.getBlockX();
    }

    /**
     * Gets the Y coordinate of the block
     *
     * @return The Y coordinate of the block
     */
    public int getBlockY() {
        return this.pathPosition.getBlockY();
    }

    /**
     * Gets the Z coordinate of the block
     *
     * @return The Z coordinate of the block
     */
    public int getBlockZ() {
        return this.pathPosition.getBlockZ();
    }
}
