package uet.oop.bomberman.ui;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;

public class NextLevel {
    private static Image levelImg;
    private static ImageView levelImgView;
    private static Group root2 = new Group();
    private static Scene scene2 = new Scene(root2);
    private static long startTime;
    public static long startLevelImgScene;
    public static final int TIME_SHOW_LEVEL_IMG = 2000;

    public static void createLevelImage() {
        startTime = System.currentTimeMillis();
        switch (BombermanGame.level) {
            case 1:
                levelImg = new Image("textures/level1.png");
                break;
            case 2:
                levelImg = new Image("textures/level2.png");
                break;
            case 3:
                levelImg = new Image("textures/level3.png");
                break;
        }

        levelImgView = new ImageView(levelImg);
        levelImgView.setX(-466);
        levelImgView.setY(-376);
        levelImgView.setScaleX(0.517);
        levelImgView.setScaleY(0.3728);

        root2.getChildren().add(levelImgView);
        BombermanGame.thisStage.setScene(scene2);
        startLevelImgScene = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime <= TIME_SHOW_LEVEL_IMG) {

        }

        BombermanGame.currentTime = System.currentTimeMillis();
        BombermanGame.thisStage.setScene(BombermanGame.scene);
    }
}
