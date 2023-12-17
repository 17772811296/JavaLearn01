package com.dahuan.eventlearn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame{

    public static void main(String[] args) {
        new BallMove();
    }

    public BallMove() throws HeadlessException {
        this.setSize(400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BallPanel ballPanel = new BallPanel();
        this.add(ballPanel);
        this.addKeyListener(ballPanel);
        this.setVisible(true);
    }
}

class BallPanel extends JPanel implements KeyListener {
    private int x;
    private int y;

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        g.fillOval(x,y,20,20);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_DOWN:
                y++;
                break;
            case KeyEvent.VK_UP:
                y--;
                break;
            case KeyEvent.VK_LEFT:
                x--;
                break;
            case KeyEvent.VK_RIGHT:
                x++;
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
