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
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    public static int limW = Sprite.SCALED_SIZE * WIDTH;
    public static int limH = Sprite.SCALED_SIZE * HEIGHT;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    static int currentFigure_bomber_l = 0;
    static int currentFigure_bomber_r = 0;
    static int currentFigure_bomber_u = 0;
    static int currentFigure_bomber_d = 0;

    static Config levelConfig = new Config();
    static int currentFigure_bomber = 0;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        levelConfig.buildConfig();
        canvas = new Canvas(Sprite.SCALED_SIZE * levelConfig.width, Sprite.SCALED_SIZE * levelConfig.height);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        Enemy ballom1 = new Ballom(2, 3, Sprite.balloom_left1.getFxImage());
        entities.add(ballom1);

        // listen event of entity bomber
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if(!bomberman.input.contains(code)) {
                            bomberman.input.add(code);
                        }
                    }
                }
        );

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        bomberman.input.remove(code);
                    }
                }
        );

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                double elapsedTime = 0;//(l - lastNanoTime.value) / 1000000000000.0;
                lastNanoTime.value = l;

                if(bomberman.input.contains("LEFT"))
                {
                    currentFigure_bomber++;
                    int numpic = currentFigure_bomber % 2;
                    currentFigure_bomber = numpic;
                    numpic += 1;

                    bomberman.addVelocity(-Sprite.step,0);
                    if(numpic == 1) { bomberman.setImage(Sprite.player_left_1.getFxImage()); }
                    else {bomberman.setImage(Sprite.player_left_2.getFxImage());}

                } else
                if(bomberman.input.contains("RIGHT")) {
                    currentFigure_bomber++;
                    int numpic = currentFigure_bomber % 2;
                    currentFigure_bomber = numpic;
                    numpic += 1;

                    bomberman.addVelocity(Sprite.step,0);
                    if(numpic == 1) { bomberman.setImage(Sprite.player_right_1.getFxImage()); }
                    else {bomberman.setImage(Sprite.player_right_2.getFxImage());}

                } else
                if(bomberman.input.contains("UP")) {

                    currentFigure_bomber++;
                    int numpic = currentFigure_bomber % 2;
                    currentFigure_bomber = numpic;
                    numpic += 1;

                    bomberman.addVelocity(0, -Sprite.step);
                    if(numpic == 1) { bomberman.setImage(Sprite.player_up_1.getFxImage()); }
                    else {bomberman.setImage(Sprite.player_up_2.getFxImage());}

                } else
                if(bomberman.input.contains("DOWN")) {

                    currentFigure_bomber++;
                    int numpic = currentFigure_bomber % 2;
                    currentFigure_bomber = numpic;
                    numpic += 1;

                    bomberman.addVelocity(0, Sprite.step);
                    if(numpic == 1) { bomberman.setImage(Sprite.player_down_1.getFxImage()); }
                    else {bomberman.setImage(Sprite.player_down_2.getFxImage());}
                }
                else {
                    bomberman.addVelocity(0,0);
                }

                /*
                if (bomberman.input.contains("LEFT")) {
                    currentFigure_bomber_l++;
                    if (currentFigure_bomber_l > 2) {
                        currentFigure_bomber_l = 1;
                    }

                    bomberman.addVelocity(-Sprite.step, 0);
                    bomberman.setImage(
                            Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, currentFigure_bomber_l, 54).getFxImage()
                    );

                } else if (bomberman.input.contains("RIGHT")) {
                    currentFigure_bomber_r++;
                    if (currentFigure_bomber_r > 2) {
                        currentFigure_bomber_r = 1;
                    }

                    bomberman.addVelocity(Sprite.step, 0);
                    bomberman.setImage(
                            Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, currentFigure_bomber_r, 54).getFxImage()
                    );

                } else if (bomberman.input.contains("UP")) {

                    currentFigure_bomber_u++;
                    if (currentFigure_bomber_u > 2) {
                        currentFigure_bomber_u = 1;
                    }

                    bomberman.addVelocity(0, -Sprite.step);
                    bomberman.setImage(
                            Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, currentFigure_bomber_u, 54).getFxImage()
                    );

                } else if (bomberman.input.contains("DOWN")) {

                    currentFigure_bomber_d++;
                    if (currentFigure_bomber_d > 2) {
                        currentFigure_bomber_d = 1;
                    }

                    bomberman.addVelocity(0, Sprite.step);
                    bomberman.setImage(
                            Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, currentFigure_bomber_d, 54).getFxImage()
                    );
                } else {
                    bomberman.addVelocity(0, 0);

                    //currentFigure_bomber_l = 0;
                    //currentFigure_bomber_r = 0;
                    //currentFigure_bomber_u = 0;
                   // currentFigure_bomber_d = 0;
                } */

                createMap();
                update();
            }
        };
        timer.start();

        //render();
        stage.show();
    }

    public void createMap() {
        for (int i = 0; i < levelConfig.width; i++) {
            for (int j = 0; j < levelConfig.height; j++) {
                Entity object;
                if (j == 0 || j == levelConfig.height - 1 || i == 0 || i == levelConfig.width - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else if(levelConfig.getConfigChar(j, i) == '#') {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        for(Entity e :entities) {
            e.update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
