package com.dahuan.draw;

import com.dahuan.tank.Enemy;
import com.dahuan.tank.Hero;
import com.dahuan.tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 绘图面板
 */
public class GamePanel extends JPanel implements KeyListener {
    private Hero hero = null;

    private Vector<Enemy> enemies = new Vector<>();

    public GamePanel() {
        hero = new Hero(100, 100);
        hero.setSpeed(5);

        for (int i = 0; i < 3; i++) {
            Enemy enemy = new Enemy(100*(i+1),0);
            enemy.setDir(2);
            enemies.add(enemy);
        }
    }

    //初始化调用、frame大小改变调用、frame重新打开调用、repaint时候调用
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        paintTank(hero.getX(), hero.getY(), g, hero.getDir(), 0);
        for (Enemy enemy : enemies) {
            paintTank(enemy.getX(), enemy.getY(), g, enemy.getDir(), 1);
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
                hero.move(1, -1);
                break;
            case KeyEvent.VK_D:
                hero.setDir(1);
                hero.move(0, 1);
                break;
            case KeyEvent.VK_S:
                hero.setDir(2);
                hero.move(1, 1);
                break;
            case KeyEvent.VK_A:
                hero.setDir(3);
                hero.move(0, -1);
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
