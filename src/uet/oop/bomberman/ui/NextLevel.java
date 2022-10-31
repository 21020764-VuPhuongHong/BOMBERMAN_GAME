package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.level.Level2;
import uet.oop.bomberman.level.Level3;

public class NextLevel {
    private static Image levelImg;
    private static ImageView levelImgView;
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
        levelImgView.setX(-464.5);
        levelImgView.setY(-375);
        levelImgView.setScaleX(0.517);
        levelImgView.setScaleY(0.3728);

        startLevelImgScene = System.currentTimeMillis();
        BombermanGame.root2.getChildren().add(levelImgView);
        BombermanGame.thisStage.setScene(BombermanGame.scene2);

        while (System.currentTimeMillis() - startTime <= TIME_SHOW_LEVEL_IMG) {

        }

        BombermanGame.currentTime = System.currentTimeMillis();
        BombermanGame.thisStage.setScene(BombermanGame.scene);
        BombermanGame.root2.getChildren().remove(levelImgView);
    }

    public static void nextLevel() {
        switch (BombermanGame.level) {
            case 1:
                BombermanGame.soundControl.playSoundNextLevel();
                Level2 level2 = new Level2();
                level2.build();
                createLevelImage();
                break;
            case 2:
                BombermanGame.soundControl.playSoundNextLevel();
                Level3 level3 = new Level3();
                level3.build();
                createLevelImage();
                break;
            case 3:
                BombermanGame.entities.clear();
                BombermanGame.stillObjects.clear();
                BombermanGame.listBombs.clear();
                BombermanGame.listEnemies.clear();

                BombermanGame.soundControl.playSoundWinGame();

                HighScore.handleScore();
                WinGame.createWinGameView();
                HighScore.renderYourScoreView();
        }
    }
}
