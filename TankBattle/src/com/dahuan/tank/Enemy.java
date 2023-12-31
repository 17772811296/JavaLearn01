package com.dahuan.tank;
import java.util.ArrayList;
import java.util.Vector;
import java.util.zip.DeflaterOutputStream;

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

    private Vector<Enemy> enemies = null;

    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    @Override
    public void run() {
        while (true) {
            switch (getDir()) {
                case 0:
                    if (!isTouchTank(enemies))
                        moveUp();
                    break;
                case 1:
                    if (!isTouchTank(enemies))
                        moveRight();
                    break;
                case 2:
                    if (!isTouchTank(enemies))
                        moveDown();
                    break;
                case 3:
                    if (!isTouchTank(enemies))
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

    /**
     * 坦克发射子弹
     */
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

    /**
     * 判断坦克是否重叠
     * @param tanks 敌方坦克
     * @return
     */
    public boolean isTouchTank(Vector<Enemy> tanks) {
        if (tanks==null) return false;
        for (int i = 0; i < tanks.size(); i++) {
            Enemy tank = tanks.get(i);
            if (this == tank) {
               continue;
            }
            switch (this.getDir()) {
                case 0://向上
                    if (getX() >= tankRange(tank).get(0) && getX() <= tankRange(tank).get(1) && getY() >= tankRange(tank).get(2) && getY() <= tankRange(tank).get(3)) {
                        return true;
                    }
                    if (getX() + 50 >= tankRange(tank).get(0) && getX() + 50 <= tankRange(tank).get(1) && getY() >= tankRange(tank).get(2) && getY() <= tankRange(tank).get(3)) {
                        return true;
                    }
                case 2://向下
                    if (getX() >= tankRange(tank).get(0) && getX() <= tankRange(tank).get(1) && getY() + 60 >= tankRange(tank).get(2) && getY() + 60 <= tankRange(tank).get(3)) {
                        return true;
                    }
                    if (getX() + 50 >= tankRange(tank).get(0) && getX() + 50 <= tankRange(tank).get(1) && getY() + 60 >= tankRange(tank).get(2) && getY() + 60 <= tankRange(tank).get(3)) {
                        return true;
                    }
                    break;
                case 1://向右
                    if (getX() + 60 >= tankRange(tank).get(0) && getX() + 60 <= tankRange(tank).get(1) && getY() >= tankRange(tank).get(2) && getY() <= tankRange(tank).get(3)) {
                        return true;
                    }
                    if (getX() + 60 >= tankRange(tank).get(0) && getX() + 60 <= tankRange(tank).get(1) && getY() + 50 >= tankRange(tank).get(2) && getY() + 50 <= tankRange(tank).get(3)) {
                        return true;
                    }
                    break;
                case 3://向左
                    if (getX() >= tankRange(tank).get(0) && getX() <= tankRange(tank).get(1) && getY() >= tankRange(tank).get(2) && getY() <= tankRange(tank).get(3)) {
                        return true;
                    }
                    if (getX() >= tankRange(tank).get(0) && getX() <= tankRange(tank).get(1) && getY() + 50 >= tankRange(tank).get(2) && getY() + 50 <= tankRange(tank).get(3)) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }
}
