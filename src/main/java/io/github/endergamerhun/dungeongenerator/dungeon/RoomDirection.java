package io.github.endergamerhun.dungeongenerator.dungeon;

public enum RoomDirection {
    TOP(1, 0),
    BOTTOM(-1, 0),
    LEFT(0, -1),
    RIGHT(0,1);

    public final int x;
    public final int z;

    RoomDirection(int x, int z) {
        this.x = x;
        this.z = z;
    }
}
