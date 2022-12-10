package io.github.endergamerhun.dungeongenerator.dungeon;

public enum RoomType {
    X(new RoomDirection[]{RoomDirection.TOP, RoomDirection.BOTTOM, RoomDirection.LEFT, RoomDirection.RIGHT}),
    T(new RoomDirection[]{RoomDirection.LEFT, RoomDirection.RIGHT, RoomDirection.BOTTOM}),
    I(new RoomDirection[]{RoomDirection.TOP, RoomDirection.BOTTOM}),
    L(new RoomDirection[]{RoomDirection.LEFT, RoomDirection.BOTTOM}),
    R(new RoomDirection[]{RoomDirection.RIGHT, RoomDirection.BOTTOM}),
    O(new RoomDirection[]{RoomDirection.BOTTOM});

    public final RoomDirection[] openings;

    RoomType(RoomDirection[] openings) {
        this.openings = openings;
    }
}
