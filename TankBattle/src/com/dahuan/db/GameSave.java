package com.dahuan.db;

import com.dahuan.tank.Enemy;

import java.io.*;
import java.util.Vector;

public class GameSave {
    public static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static int heroHitEnemyNum = 0;
    private static String path = "src\\tank.txt";

    public static int getHeroHitEnemyNum() {
        return heroHitEnemyNum;
    }

    public static void save(Vector<Enemy> enemies) {
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(String.valueOf(heroHitEnemyNum));
            bw.newLine();
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                String str = String.valueOf(enemy.getX()) + "/" +
                        enemy.getY() + "/" + enemy.getDir();
                bw.write(str);
                bw.newLine();
            }
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

    public static Vector<Enemy> readSave() {
        Vector<Enemy> enemies = null;
        try {
            if (!new File(path).exists()) return null;
            br = new BufferedReader(new FileReader(path));
            String line = null;
            if ((line = br.readLine()) != null) {
                System.out.println(line);
                heroHitEnemyNum = Integer.valueOf(line);
            }
            enemies = new Vector<>();
            while ((line = br.readLine()) != null) {
                String str = line;
                String[] split = str.split("/");
                Enemy enemy = null;
                for (int i = 0; i < split.length; i++) {
                    enemy = new Enemy(Integer.valueOf(split[0]),
                            Integer.valueOf(split[1]));
                    enemy.setDir(Integer.valueOf(split[2]));
                }
                new Thread(enemy).start();
                enemies.add(enemy);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("读取成功");
        return enemies;
    }

    public static synchronized void addHitEnemyNum() {
        heroHitEnemyNum++;
    }
}
