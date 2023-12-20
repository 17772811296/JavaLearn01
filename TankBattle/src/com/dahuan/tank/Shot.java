package com.dahuan.tank;

/**
 * 子弹 炮弹
 */
@SuppressWarnings("all")
public class Shot implements Runnable {

    private int x;
    private int y;
    private int dir;
    private int speed = 2;
    private boolean isLive = true;

    public Shot(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @SuppressWarnings("all")
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (dir) {
                case 0://向上
                    y -= speed;
                    break;
                case 1://向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;
            }
//            System.out.println("子弹的x" + x + "子弹的y" + y);
//            System.out.println(this.hashCode());
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive())) {
//                System.out.println("子弹超出边界");
                isLive = false;
                break;
            }
        }
    }
}
