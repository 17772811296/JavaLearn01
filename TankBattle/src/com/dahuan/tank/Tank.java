package com.dahuan.tank;

/**
 * 坦克基本类
 */
public class Tank {
    private int x;
    private int y;
    private int speed = 1;
    private int dir = 0;

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

    /**
     * 坦克的移动
     * @param xOrY 0表示x轴移动 1表示y轴移动
     * @param dirXorY 轴向正负向移动 -1 or 1
     */
    public void move(int xOrY, int dirXorY) {
        if (xOrY == 0) {
            x += dirXorY * speed;
        } else {
            y += dirXorY * speed;
        }
    }
}
