package com.dahuan.draw;

import javax.swing.*;
import java.awt.*;

/**
 * 界面初始化设置
 */
@SuppressWarnings("all")
public class DrawFrame extends JFrame {
    private GamePanel gamePanel = null;

    public DrawFrame() throws HeadlessException {
        gamePanel = new GamePanel();
        this.setSize(1000, 750);
        //添加一个panel
        this.add(gamePanel);
        this.addKeyListener(gamePanel);
        //开一个子线程每隔100ms重绘panel
        new Thread(gamePanel).start();
        //点击关闭窗口关闭程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
