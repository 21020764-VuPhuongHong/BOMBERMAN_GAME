package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.still_block.Grass;
import uet.oop.bomberman.entities.still_block.Wall;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.items.*;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.graphics.Sprite;

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
            height = myReader.nextInt();
            width = myReader.nextInt();

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
                if (config[i][j] == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                BombermanGame.stillObjects.add(object);
            }
        }
    }

    public void createEntities() {
        int posX = 1, posY = 1;

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                switch (config[i][j]) {
                    case 'p':
                        posX = j;
                        posY = i;
                        break;
                    case '1':
                        Ballom e1 = new Ballom(j, i, Sprite.balloom_left1.getFxImage());
                        BombermanGame.listEnemies.add(e1);
                        break;
                    case '2':
                        Oneal e2 = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                        BombermanGame.listEnemies.add(e2);
                        break;
                    case '3':
                        Doll e3 = new Doll(j, i, Sprite.doll_left1.getFxImage());
                        BombermanGame.listEnemies.add(e3);
                        break;
                    case '4':
                        Minvo e4 = new Minvo(j, i, Sprite.minvo_left1.getFxImage());
                        BombermanGame.listEnemies.add(e4);
                        break;
                    case '5':
                        Ovapi e5 = new Ovapi(j, i, Sprite.ovapi_left1.getFxImage());
                        BombermanGame.listEnemies.add(e5);
                        break;
                    case '6':
                        Kondoria e6 = new Kondoria(j, i, Sprite.kondoria_left1.getFxImage());
                        BombermanGame.listEnemies.add(e6);
                        break;
                    case '7':
                        Pass e7 = new Pass(j, i, Sprite.pass_left1.getFxImage());
                        BombermanGame.listEnemies.add(e7);
                        break;
                    case '8':
                        Pontan e8 = new Pontan(j, i, Sprite.pontan_left1.getFxImage());
                        BombermanGame.listEnemies.add(e8);
                        break;
                    case 'f':
                        FlameItem flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                        BombermanGame.entities.add(flameItem);
                        Brick brick1 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick1.isCovering = true;
                        BombermanGame.entities.add(brick1);
                        break;
                    case 's':
                        SpeedItem speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                        BombermanGame.entities.add(speedItem);
                        Brick brick2 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick2.isCovering = true;
                        BombermanGame.entities.add(brick2);
                        break;
                    case 'b':
                        BombItem bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                        BombermanGame.entities.add(bombItem);
                        Brick brick3 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick3.isCovering = true;
                        BombermanGame.entities.add(brick3);
                        break;
                    case 'w':
                        WallPassItem wallPassItem = new WallPassItem(j, i, Sprite.powerup_wallpass.getFxImage());
                        BombermanGame.entities.add(wallPassItem);
                        Brick brick4 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick4.isCovering = true;
                        BombermanGame.entities.add(brick4);
                        break;
                    case 'B':
                        BombPassItem bombPassItem = new BombPassItem(j, i, Sprite.powerup_bombpass.getFxImage());
                        BombermanGame.entities.add(bombPassItem);
                        Brick brick5 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick5.isCovering = true;
                        BombermanGame.entities.add(brick5);
                        break;
                    case 'F':
                        FlamePassItem flamePassItem = new FlamePassItem(j, i, Sprite.powerup_flamepass.getFxImage());
                        BombermanGame.entities.add(flamePassItem);
                        Brick brick6 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick6.isCovering = true;
                        BombermanGame.entities.add(brick6);
                        break;
                    case 'd':
                        DetonatorItem detonatorItem = new DetonatorItem(j, i, Sprite.powerup_detonator.getFxImage());
                        BombermanGame.entities.add(detonatorItem);
                        Brick brick7 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick7.isCovering = true;
                        BombermanGame.entities.add(brick7);
                        break;
                    case '*':
                        Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                        BombermanGame.entities.add(brick);
                        break;
                    case 'x':
                        Portal portal = new Portal(j, i, Sprite.portal.getFxImage());
                        BombermanGame.entities.add(portal);
                        Brick brick0 = new Brick(j, i, Sprite.brick.getFxImage());
                        brick0.isCovering = true;
                        BombermanGame.entities.add(brick0);
                        break;
                }
            }
        }

        BombermanGame.bomberman = new Bomber(posX, posY, Sprite.player_right.getFxImage());
        BombermanGame.entities.add(BombermanGame.bomberman);
        BombermanGame.bomberman.setVelocity(BombermanGame.bomberStep, BombermanGame.bomberStep);
        BombermanGame.currentFigure_bomber = 0;
    }
}
