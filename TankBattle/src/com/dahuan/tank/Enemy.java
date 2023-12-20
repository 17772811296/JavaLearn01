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
            if (Math.random()>0.95){
                shotEnemyTank();
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

    private Shot shot;
    public void shotEnemyTank() {
        if (shots.size() > 5) {
            return;
        }
        switch (getDir()) { //根据坦克方向位置给定子弹方向位置
            case 0://向上
                shot = new Shot(getX() + 25, getY(), 0);
                break;
            case 1://向右
                shot = new Shot(getX() + 60, getY() + 25, 1);
                break;
            case 2://向下
                shot = new Shot(getX() + 25, getY() + 60, 2);
                break;
            case 3://向左
                shot = new Shot(getX(), getY() + 25, 3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }
}
