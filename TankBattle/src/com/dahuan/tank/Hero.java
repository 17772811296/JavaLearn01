package com.dahuan.tank;

/**
 * 主角坦克
 */
public class Hero extends Tank{
    private Shot shot;
    public Hero(int x, int y) {
        super(x, y);
    }

    public Shot getShot() {
        return shot;
    }

    /**
     * 用户向敌人坦克开炮
     */
    @SuppressWarnings("all")
    public void shotEnemyTank(){
        switch (getDir()){ //根据坦克方向位置给定子弹方向位置
            case 0://向上
                shot = new Shot(getX()+25,getY(),0);
                break;
            case 1://向右
                shot = new Shot(getX()+60,getY()+25,1);
                break;
            case 2://向下
                shot = new Shot(getX()+25,getY()+60,2);
                break;
            case 3://向左
                shot = new Shot(getX(),getY()+25,3);
                break;
        }
        new Thread(shot).start();
    }

}

