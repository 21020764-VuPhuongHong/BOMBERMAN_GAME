package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Grass;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.entities.Enemies.Ballom;
import uet.oop.bomberman.entities.Enemies.Oneal;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.graphics.Config;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    private GraphicsContext gc;
    private Canvas canvas;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();

    public static Bomber bomberman;

    static Config levelConfig = new Config();
    static int currentFigure_bomber = 0;

    public static int WIDTH = 20;
    public static int HEIGHT = 15;

    public static int step = Sprite.DEFAULT_SIZE;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        levelConfig.buildConfig();
        // Canvas(double width, double height)
        canvas = new Canvas(Sprite.SCALED_SIZE * levelConfig.width * 1.0, Sprite.SCALED_SIZE * levelConfig.height * 1.0);
        gc = canvas.getGraphicsContext2D();

        WIDTH = levelConfig.width;
        HEIGHT = levelConfig.height;

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        bomberman.setVelocity(step, step);

        /** create enemy and brick depend on config file**/
        for (int j = 0; j < levelConfig.width; j++) {
            for (int i = 0; i < levelConfig.height; i++) {
                if (levelConfig.getConfigChar(i, j) == '1') {
                    Ballom e = new Ballom(j, i, Sprite.balloom_left1.getFxImage());
                    entities.add(e);
                } else if (levelConfig.getConfigChar(i, j) == '2') {
                    Oneal e = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                    entities.add(e);
                } else if (levelConfig.getConfigChar(i, j) == 'f') {
                    FlameItem flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                    entities.add(flameItem);
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    entities.add(brick);
                } else if (levelConfig.getConfigChar(i, j) == 's') {
                    SpeedItem speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                    entities.add(speedItem);
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    entities.add(brick);
                } else if (levelConfig.getConfigChar(i, j) == 'b') {
                    BombItem bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                    entities.add(bombItem);
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    entities.add(brick);
                } else if (levelConfig.getConfigChar(i, j) == '*') {
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    entities.add(brick);
                }
            }
        }

        // listen event of entity bomber
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (code.equals("SPACE")) {
                            System.out.println(Bomb.timeRemainBomb);
                            if ((BombItem.hasBombItem && BombItem.numBomsPut < 2) || Bomb.timeRemainBomb >= Bomb.TIME_BETWEEN_2_BOMBS) {
                                Bomb bomb = new Bomb(bomberman.getX() / Sprite.SCALED_SIZE, bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                                bomb.putBomb();
                                entities.add(bomb);
                                if (BombItem.hasBombItem) {
                                    if (BombItem.numBomsPut < 2 && Bomb.timeRemainBomb < Bomb.TIME_BETWEEN_2_BOMBS) {
                                        BombItem.numBomsPut++;
                                    } else {
                                        BombItem.numBomsPut = 1;
                                    }
                                }
                            }
                        } else if (code.equals("LEFT")) {
                            currentFigure_bomber++;
                            currentFigure_bomber %= 2;
                            int numpic = currentFigure_bomber + 1;

                            Move.moveLeft(bomberman);

                            if (numpic == 1) {
                                bomberman.setImage(Sprite.player_left_1.getFxImage());
                            } else {
                                bomberman.setImage(Sprite.player_left_2.getFxImage());
                            }
                        } else if (code.equals("RIGHT")) {
                            currentFigure_bomber++;
                            currentFigure_bomber %= 2;
                            int numpic = currentFigure_bomber + 1;

                            Move.moveRight(bomberman);

                            if (numpic == 1) {
                                bomberman.setImage(Sprite.player_right_1.getFxImage());
                            } else {
                                bomberman.setImage(Sprite.player_right_2.getFxImage());
                            }

                        } else if (code.equals("UP")) {
                            currentFigure_bomber++;
                            currentFigure_bomber %= 2;
                            int numpic = currentFigure_bomber + 1;

                            Move.moveUp(bomberman);

                            if (numpic == 1) {
                                bomberman.setImage(Sprite.player_up_1.getFxImage());
                            } else {
                                bomberman.setImage(Sprite.player_up_2.getFxImage());
                            }
                        } else if (code.equals("DOWN")) {
                            currentFigure_bomber++;
                            currentFigure_bomber %= 2;
                            int numpic = currentFigure_bomber + 1;

                            Move.moveDown(bomberman);

                            if (numpic == 1) {
                                bomberman.setImage(Sprite.player_down_1.getFxImage());
                            } else {
                                bomberman.setImage(Sprite.player_down_2.getFxImage());
                            }
                        }
                    }
                }
        );

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                createMap();
                update();
            }
        };
        timer.start();

        //render();
        stage.show();
    }

    public void createMap() {

        for (int j = 0; j < levelConfig.width; j++) {
            for (int i = 0; i < levelConfig.height; i++) {
                Entity object;
                if (i == 0 || i == levelConfig.height - 1 || j == 0 || j == levelConfig.width - 1) {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (levelConfig.getConfigChar(i, j) == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void update() {
        for (Entity e : entities) {
            e.update();
        }
        Bomb.timeRemainBomb = System.currentTimeMillis() - Bomb.timePutBomb;
    }
}