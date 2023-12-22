package com.dahuan.db;

import com.dahuan.tank.Shot;

import javax.xml.ws.soap.Addressing;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameSave {
    public static BufferedWriter bw = null;
    public static FileWriter fw = null;
    private static int heroHitEnemyNum = 0;
    private static String path = "D:\\tank.txt";

    public static int getHeroHitEnemyNum() {
        return heroHitEnemyNum;
    }

    public static void Save() {
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(String.valueOf(heroHitEnemyNum));
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("保存成功");
    }

    public static synchronized void addHitEnemyNum() {
        heroHitEnemyNum++;
    }
}
