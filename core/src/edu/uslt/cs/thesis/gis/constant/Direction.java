package edu.uslt.cs.thesis.gis.constant;

public enum Direction {

    NORTH(0, 1, "north"),
    NORTH_EAST(1, 1, "north-east"),
    NORTH_WEST(-1, 1, "north-west"),


    SOUTH(0, -1, "south"),
    SOUTH_EAST(1, -1, "south-east"),
    SOUTH_WEST(-1, -1, "south-west"),

    WEST(1, 0, "west"),
    EAST(-1, 0, "east");

//    NORTH(0, 1, "north"),
//    NORTH_EAST(1, 1, "north-east"),
//    NORTH_WEST(-1, 1, "north-west"),
//
//
//    SOUTH(0, -1, "south"),
//    SOUTH_EAST(1, -1, "south-east"),
//    SOUTH_WEST(-1, -1, "south-west"),
//
//    WEST(1, 0, "west"),
//    EAST(-1, 0, "east");

    public final int offsetX;
    public final int offsetY;
    public final String drawableValue;

    Direction(int offsetX, int offsetY, String drawableValue) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.drawableValue = drawableValue;
    }
}

