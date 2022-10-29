package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Grass;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemies.Ballom;
import uet.oop.bomberman.entities.Enemies.Oneal;
import uet.oop.bomberman.entities.Enemies.Doll;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.Items.SpeedItem;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ConfigLevel {
    public static int width;
    public static int height;

    public char[][] config = new char[100][100];

    public void buildConfig(String levelPath) {
        try {
            File myfile = new File(levelPath);
            Scanner myReader = new Scanner(myfile);
            BombermanGame.level = myReader.nextInt();
            this.height = myReader.nextInt();
            this.width = myReader.nextInt();

            String ignore = myReader.nextLine();
            System.out.println(BombermanGame.level + " " + height + " " + width);

            for (int i = 0; i < height; ++i) {
                String str = myReader.nextLine();
                for (int j = 0; j < width; ++j) {
                    config[i][j] = str.charAt(j);
                }
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void createMap() {
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                Entity object;
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (config[i][j] == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                BombermanGame.stillObjects.add(object);
            }
        }
    }

    public void createEntities() {
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                if (config[i][j] == '1') {
                    Ballom e = new Ballom(j, i, Sprite.balloom_left1.getFxImage());
                    BombermanGame.entities.add(e);
                } else if (config[i][j] == '2') {
                    Oneal e = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                    BombermanGame.entities.add(e);
                } else if (config[i][j] == '3') {
                    Doll e = new Doll(j, i, Sprite.doll_left1.getFxImage());
                    //e.setVelocity(1,1);
                    BombermanGame.entities.add(e);
                } else if (config[i][j] == 'f') {
                    FlameItem flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                    BombermanGame.entities.add(flameItem);
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    BombermanGame.entities.add(brick);
                } else if (config[i][j] == 's') {
                    SpeedItem speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                    BombermanGame.entities.add(speedItem);
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    BombermanGame.entities.add(brick);
                } else if (config[i][j] == 'b') {
                    BombItem bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                    BombermanGame.entities.add(bombItem);
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    BombermanGame.entities.add(brick);
                } else if (config[i][j] == '*') {
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    BombermanGame.entities.add(brick);
                } else if (config[i][j] == 'x') {
                    Portal portal = new Portal(j, i, Sprite.portal.getFxImage());
                    BombermanGame.entities.add(portal);
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    BombermanGame.entities.add(brick);
                }
            }
        }

        BombermanGame.bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        BombermanGame.entities.add(BombermanGame.bomberman);
        BombermanGame.bomberman.setVelocity(BombermanGame.bomberStep, BombermanGame.bomberStep);
        BombermanGame.currentFigure_bomber = 0;

        int default_heart = 3;
        BombermanGame.bomberman.setHeart(default_heart);
    }
}
