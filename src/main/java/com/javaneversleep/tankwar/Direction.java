package com.javaneversleep.tankwar;

import java.awt.Image;

public enum Direction {

    UP("U", 0, -1, 4),
    DOWN("D", 0, 1, 8),
    LEFT("L", -1, 0, 1),
    RIGHT("R", 1, 0, 2),
    LEFT_UP("LU", -1, -1, 5),
    RIGHT_UP("RU", 1, -1, 6),
    LEFT_DOWN("LD", -1, 1, 9),
    RIGHT_DOWN("RD", 1, 1, 10);

    private final String abbrev;

    final int xFactor, yFactor;

    final int code;

    Direction(String abbrev, int xFactor, int yFactor, int code) {
        this.abbrev = abbrev;
        this.xFactor = xFactor;
        this.yFactor = yFactor;
        this.code = code;
    }

    static Direction get(int code) {
        for (Direction dir : Direction.values()) {
            if (dir.code == code) {
                return dir;
            }
        }
        return null;
    }

    Image getImage(String prefix) {
        return Tools.getImage(prefix + abbrev + ".gif");
    }

}
