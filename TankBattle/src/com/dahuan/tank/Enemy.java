package com.dahuan.tank;
import java.util.Vector;

/**
 * 敌人坦克对象
 */
@SuppressWarnings("all")
public class Enemy extends Tank implements Runnable {
    private Vector<Shot> shots = new Vector<>();

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    @Override
    public void run() {
        while (true) {
            switch (getDir()) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveRight();
                    break;
                case 2:
                    moveDown();
                    break;
                case 3:
                    moveLeft();
                    break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int index = (int) (Math.random() * 100);
            if (index > 93) { //随机改变坦克的方向
                setDir((int) (Math.random() * 4));
            }
            if (!isLive()){
                break;
            }
        }
    }
}
