package com.javaneversleep.tankwar;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Tank {

    private int x;

    private int y;

    private boolean enemy;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Direction direction;

    public Tank(int x, int y, Direction direction) {
        this(x, y, false, direction);
    }

    public Tank(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    void move() {
        if (this.stopped) return;

        switch (direction) {
            case UP:
                y -= 5;
                break;
            case UPLEFT:
                y -= 5;
                x -= 5;
                break;
            case UPRIGHT:
                y -= 5;
                x += 5;
                break;
            case DOWN:
                y += 5;
                break;
            case DOWNLEFT:
                x -= 5;
                y += 5;
                break;
            case DOWNRIGHT:
                x += 5;
                y += 5;
                break;
            case LEFT:
                x -= 5;
                break;
            case RIGHT:
                x += 5;
                break;
        }
    }

    Image getImage() {
        String prefix = enemy ? "e" : "";
        switch (direction) {
            case UP:
                return new ImageIcon("assets/images/" + prefix + "tankU.gif").getImage();
            case UPLEFT:
                return new ImageIcon("assets/images/" + prefix + "tankLU.gif").getImage();
            case UPRIGHT:
                return new ImageIcon("assets/images/" + prefix + "tankRU.gif").getImage();
            case DOWN:
                return new ImageIcon("assets/images/" + prefix + "tankD.gif").getImage();
            case DOWNLEFT:
                return new ImageIcon("assets/images/" + prefix + "tankDLD.gif").getImage();
            case DOWNRIGHT:
                return new ImageIcon("assets/images/" + prefix + "tankDRD.gif").getImage();
            case LEFT:
                return new ImageIcon("assets/images/" + prefix + "tankDL.gif").getImage();
            case RIGHT:
                return new ImageIcon("assets/images/" + prefix + "tankDR.gif").getImage();
        }
        return null;
    }

    void draw(Graphics g) {
        this.determineDirection();
        this.move();
        g.drawImage(this.getImage(), this.x, this.y, null);
    }

    private boolean up, down, left, right;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: up = true; break;
            case KeyEvent.VK_DOWN: down = true; break;
            case KeyEvent.VK_LEFT: left = true; break;
            case KeyEvent.VK_RIGHT: right = true; break;
        }
        this.determineDirection();
    }

    private boolean stopped;

    private void determineDirection() {
        if (!up && !right && !down && !left) {
            this.stopped = true;
        } else {
            if (up && left && !down && !right) this.direction = Direction.UPLEFT;
            else if (up && !left && !down && right) this.direction = Direction.UPRIGHT;
            else if (up && !left && !down && !right) this.direction = Direction.UP;
            else if (!up && !left && down && !right) this.direction = Direction.DOWN;
            else if (!up && left && down && !right) this.direction = Direction.DOWNLEFT;
            else if (!up && !left && down && right) this.direction = Direction.DOWNRIGHT;
            else if (!up && left && !down && !right) this.direction = Direction.LEFT;
            else if (!up && !left && !down && right) this.direction = Direction.RIGHT;
            this.stopped = false;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: up = false; break;
            case KeyEvent.VK_DOWN: down = false; break;
            case KeyEvent.VK_LEFT: left = false; break;
            case KeyEvent.VK_RIGHT: right = false; break;
        }
        this.determineDirection();
    }
}
