package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.ui.InfoLevel;
import uet.oop.bomberman.ui.Menu;
import uet.oop.bomberman.ui.NextLevel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    private GraphicsContext gc;
    private Canvas canvas;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();

    public static Bomber bomberman;
    public static int currentFigure_bomber = 0;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static int bomberStep = 8;
    public static Group root;
    public static Scene scene;
    public static Stage thisStage;
    public static final int TIME_FOR_LEVEL = 300;
    public static int timeLeft = TIME_FOR_LEVEL;
    public static long currentTime;
    public static int level;
    public static boolean isStart = false;

    public static Sound soundControl;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        thisStage = stage;

        // Tao Canvas
        // Canvas(double width, double height)
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH * 1.0, Sprite.SCALED_SIZE * HEIGHT * 1.0);
        canvas.setTranslateY(Sprite.SCALED_SIZE);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        root = new Group();
        root.getChildren().add(canvas);

        soundControl = new Sound();
        soundControl.build();
        Sound.isSoundGame = true;

        Menu.createMenu();
        Menu.handleMenuButtons();

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        thisStage.setScene(scene);
        thisStage.setTitle("BOMBERMAN");
        Image logo = new Image("textures/icon.png");
        thisStage.getIcons().add(logo);
        thisStage.setResizable(false);


        // listen event of entity bomber

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        if (!isStart) {
                            e.consume();
                            System.out.println("e cosumed");
                            if (System.currentTimeMillis() - NextLevel.startLevelImgScene >= NextLevel.TIME_SHOW_LEVEL_IMG) {
                                isStart = true;
                            }
                        } else if (bomberman.getAliveState() && !e.isConsumed()) {
                            String code = e.getCode().toString();
                            if (code.equals("SPACE")) {
                                System.out.println(Bomb.timeWaitForPutting2ndBomb);
                                if ((BombItem.hasBombItem && BombItem.numBomsPut < 2) || Bomb.timeWaitForPutting2ndBomb >= Bomb.TIME_BETWEEN_2_BOMBS) {
                                    Bomb bomb = new Bomb((bomberman.getX() + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE, (bomberman.getY() + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                                    bomb.putBomb();
                                    entities.add(bomb);
                                    if (BombItem.hasBombItem) {
                                        if (BombItem.numBomsPut < 2 && Bomb.timeWaitForPutting2ndBomb < Bomb.TIME_BETWEEN_2_BOMBS) {
                                            BombItem.numBomsPut++;
                                        } else {
                                            BombItem.numBomsPut = 1;
                                        }
                                    }
                                }
                            } else if (code.equals("LEFT")) {
                                currentFigure_bomber++;
                                currentFigure_bomber %= 3;

                                Move.moveLeft(bomberman);

                                if (currentFigure_bomber == 0) {
                                    bomberman.setImage(Sprite.player_left.getFxImage());
                                } else if (currentFigure_bomber == 1) {
                                    bomberman.setImage(Sprite.player_left_1.getFxImage());
                                } else {
                                    bomberman.setImage(Sprite.player_left_2.getFxImage());
                                }
                            } else if (code.equals("RIGHT")) {
                                currentFigure_bomber++;
                                currentFigure_bomber %= 3;

                                Move.moveRight(bomberman);

                                if (currentFigure_bomber == 0) {
                                    bomberman.setImage(Sprite.player_right.getFxImage());
                                } else if (currentFigure_bomber == 1) {
                                    bomberman.setImage(Sprite.player_right_1.getFxImage());
                                } else {
                                    bomberman.setImage(Sprite.player_right_2.getFxImage());
                                }

                            } else if (code.equals("UP")) {
                                currentFigure_bomber++;
                                currentFigure_bomber %= 3;

                                Move.moveUp(bomberman);

                                if (currentFigure_bomber == 0) {
                                    bomberman.setImage(Sprite.player_up.getFxImage());
                                } else if (currentFigure_bomber == 1) {
                                    bomberman.setImage(Sprite.player_up_1.getFxImage());
                                } else {
                                    bomberman.setImage(Sprite.player_up_2.getFxImage());
                                }
                            } else if (code.equals("DOWN")) {
                                currentFigure_bomber++;
                                currentFigure_bomber %= 3;

                                Move.moveDown(bomberman);

                                if (currentFigure_bomber == 0) {
                                    bomberman.setImage(Sprite.player_down.getFxImage());
                                } else if (currentFigure_bomber == 1) {
                                    bomberman.setImage(Sprite.player_down_1.getFxImage());
                                } else {
                                    bomberman.setImage(Sprite.player_down_2.getFxImage());
                                }
                            }
                        }
                    }
                }
        );

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        /*

        try {
            //String path = "D:\\WorkSpace\\bomberman-starter\\res\\Sounds\\SoundGame.wav";
            String path = "Sounds/SoundGame.wav";
            URL url = this.getClass().getClassLoader().getResource(path);

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            //clip.loop(10);
        }
        catch (Exception e) {
            System.out.println("lỗi" + e.getMessage());
        } */
        soundControl.update();
        timer.start();
        //Sound.build();
        //Sound.update();

        //render();
        thisStage.show();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void update() {
        countTimeLeft();
        for (Entity e : entities) {
            e.update();
        }
        Bomb.timeWaitForPutting2ndBomb = System.currentTimeMillis() - Bomb.timePutBomb;
        InfoLevel.updateLevelInfo();
    }

    public void countTimeLeft() {
        if (System.currentTimeMillis() - currentTime >= 1000) {
            timeLeft--;
            currentTime = System.currentTimeMillis();
        }

        if (timeLeft <= 0) {
            bomberman.loseHeart();
            if(bomberman.getHeart() == 0)
            {
                bomberman.setAliveState(false);
            }
            //bomberman.setAliveState(false);
        }
    }
}