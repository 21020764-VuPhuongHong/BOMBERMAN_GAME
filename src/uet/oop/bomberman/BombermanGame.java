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
    static int currentFigure_bomber = 0;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
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
                double elapsedTime = (l - lastNanoTime.value) / 10000000000.0;
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
                createMap();
                update(elapsedTime);
            }
        };
        timer.start();

        //render();
        stage.show();
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update(double time) {
        for(Entity e :entities) {
            e.update(time);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
