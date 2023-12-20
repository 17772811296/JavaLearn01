package com.dahuan.draw;

import com.dahuan.tank.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 绘图面板
 */
@SuppressWarnings("all")
public class GamePanel extends JPanel implements KeyListener, Runnable {
    private Hero hero = null;

    private Vector<Enemy> enemies = new Vector<>();
    private Vector<Bomb> bombs = new Vector<>();
    Image image;
    Image image1;
    Image image2;

    @SuppressWarnings("all")
    public GamePanel() {
        //初始化主角
        hero = new Hero(100, 100);
        hero.setSpeed(5);
        //初始化敌人
        for (int i = 0; i < 3; i++) {
            Enemy enemy = new Enemy(100 * (i + 1), 0);
            enemy.setDir(2);
            new Thread(enemy).start();
            Shot shot = null;
            switch (enemy.getDir()) {
                case 2:
                    shot = new Shot(enemy.getX() + 25, enemy.getY() + 60, enemy.getDir());
                    break;
            }
            enemy.getShots().add(shot);
            new Thread(shot).start();
            enemies.add(enemy);
        }
        //加载爆炸效果美术资源
        image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

    }

    /**
     * 击中坦克
     *
     * @param shot 子弹
     * @param tank 坦克
     */
    public void hitEnemyTank(Shot shot, Tank tank) {
        if (!(shot != null && shot.isLive() && tank.isLive())) {
            return;
        }
        switch (tank.getDir()) {
            case 0:
            case 2:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 45
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 60) {
                    shot.setLive(false);
                    tank.setLive(false);
                    if (tank instanceof Enemy){
                        enemies.remove(tank);
                    }
                    bombs.add(new Bomb(tank.getX(),tank.getY(),true));
                }
                break;
            case 1:
            case 3:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 60
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 45) {
                    shot.setLive(false);
                    tank.setLive(false);
                    if (tank instanceof Enemy){
                        enemies.remove(tank);
                    }
                    bombs.add(new Bomb(tank.getX(),tank.getY(),true));
                }
                break;
        }
    }

    //初始化调用、frame大小改变调用、frame重新打开调用、repaint时候调用
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制地图大小
        g.fillRect(0, 0, 1000, 750);
        //绘制敌人坦克
        for (Enemy enemy : enemies) {
            if (enemy.isLive()) {//坦克存活的时候才绘制
                paintTank(enemy.getX(), enemy.getY(), g, enemy.getDir(), 1);
                for (int i = 0; i < enemy.getShots().size(); i++) {
                    Shot shot = enemy.getShots().get(i);
                    //绘制子弹
                    if (shot.isLive()) {//子弹存活的时候才进行绘制
                        g.setColor(Color.yellow);
                        g.draw3DRect(shot.getX(), shot.getY(), 2, 2, false);
                    }
                }
            }
        }
        //绘制主角坦克
        paintTank(hero.getX(), hero.getY(), g, hero.getDir(), 0);
        //绘制主角子弹
        if (hero.getShot() != null && hero.getShot().isLive()) {
            g.setColor(Color.yellow);
            g.draw3DRect(hero.getShot().getX(), hero.getShot().getY(), 2, 2, false);
        }

        //绘制爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isLive()){
                bombs.remove(i);
                return;
            }
            if (bomb.getLife()>9){
                g.drawImage(image,bomb.getX(),bomb.getY(),60,60,this);
            } else if (bomb.getLife()>6) {
                g.drawImage(image1,bomb.getX(),bomb.getY(),60,60,this);
            }else {
                g.drawImage(image2,bomb.getX(),bomb.getY(),60,60,this);
            }
            bomb.lifeDown();
        }
    }

    /**
     * 绘制坦克
     *
     * @param x    坦克横坐标
     * @param y    坦克纵坐标
     * @param g    图形
     * @param dir  坦克方向
     * @param type 坦克类型 0 表示自己 1表示敌方
     */
    public void paintTank(int x, int y, Graphics g, int dir, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.YELLOW);
                break;
        }
        switch (dir) {
            case 0://向上的方向
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 40, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 30, 40, false);
                g.fillOval(x + 10, y + 15, 30, 30);
                g.fillRect(x + 23, y, 5, 30);
                break;
            case 1://向右的方向
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 40, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 30, false);
                g.fillOval(x + 15, y + 10, 30, 30);
                g.fillRect(x + 30, y + 23, 30, 5);
                break;
            case 2://向下方向
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 40, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 30, 40, false);
                g.fillOval(x + 10, y + 15, 30, 30);
                g.fillRect(x + 23, y + 30, 5, 30);
                break;
            case 3://向左方向
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 40, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 30, false);
                g.fillOval(x + 15, y + 10, 30, 30);
                g.fillRect(x, y + 23, 30, 5);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                hero.setDir(0);
                hero.moveUp();
                break;
            case KeyEvent.VK_D:
                hero.setDir(1);
                hero.moveRight();
                break;
            case KeyEvent.VK_S:
                hero.setDir(2);
                hero.moveDown();
                break;
            case KeyEvent.VK_A:
                hero.setDir(3);
                hero.moveLeft();
                break;
            case KeyEvent.VK_J:
                hero.shotEnemyTank();
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @SuppressWarnings("all")
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();//每隔100ms重新绘制GamePanel

            for (int i = 0; i < enemies.size(); i++) {
                hitEnemyTank(hero.getShot(), enemies.get(i));
            }
        }
    }
}
