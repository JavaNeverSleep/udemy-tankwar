package com.javaneversleep.tankwar;

import java.awt.Graphics;
import java.awt.Image;

class Missile {

    private static final int SPEED = 12;

    private int x;

    private int y;

    private final boolean enemy;

    private final Direction direction;

    Missile(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }
    
    private Image getImage() {
        return direction.getImage("missile");
    }

    private void move() {
        x += direction.xFactor * SPEED;
        y += direction.yFactor * SPEED;
    }

    void draw(Graphics g) {
        move();
        if (x < 0 || x > 800 || y < 0 || y > 600) {
            return;
        }
        g.drawImage(getImage(), x, y, null);
    }
}
