package com.dahuan.tank;

/**
 * 坦克基本类
 */
public class Tank {
    private int x;
    private int y;
    private int speed = 1;
    private int dir = 0;
    private boolean isLive = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void moveUp() {
        if (getY() > 0) {
            y -= speed;
        }
    }

    public void moveRight() {
        if (getX() + 60 < 1000) {
            x += speed;
        }
    }

    public void moveDown() {
        if (getY() + 60 < 750) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            x -= speed;
        }
    }
}
