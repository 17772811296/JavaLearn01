package com.dahuan.draw;

import com.dahuan.db.GameSave;
import com.dahuan.tank.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * 界面初始化设置
 */
@SuppressWarnings("all")
public class DrawFrame extends JFrame {
    private GamePanel gamePanel = null;

    public DrawFrame() throws HeadlessException {
        gamePanel = new GamePanel();
        Scanner scanner = null;
        Integer i = 0;
        while (true){
            System.out.println("1.重新开始游戏,2.加载游戏");
            System.out.println("请输入1或者2");
            scanner = new Scanner(System.in);
            try {
                i = scanner.nextInt();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if (!(i instanceof Integer&&(i==1||i==2))){
                System.out.println("输入错误,请重新输入！");
            }else {
                break;
            }
        }
        Vector<Enemy> enemies = null;
        if (i == 2) {
            enemies = GameSave.readSave();
        }
        if (enemies != null && enemies.size() > 0) {
            gamePanel.setEnemies(enemies);
        }
        this.setSize(1300, 750);
        //添加一个panel
        this.add(gamePanel);
        this.addKeyListener(gamePanel);
        //开一个子线程每隔100ms重绘panel
        new Thread(gamePanel).start();
        //点击关闭窗口关闭程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                GameSave.save(gamePanel.getEnemies());
                System.exit(0);
            }
        });
    }
}
