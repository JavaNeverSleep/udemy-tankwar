package com.javaneversleep.tankwar;

import java.awt.Graphics;

class Explosion {

    private int x, y;

    Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int step = 0;

    private boolean live = true;

    boolean isLive() {
        return live;
    }

    void setLive(boolean live) {
        this.live = live;
    }

    void draw(Graphics g) {
        if (step > 10) {
            this.setLive(false);
            return;
        }
        g.drawImage(Tools.getImage(step++ + ".gif"), x, y, null);
    }

}
