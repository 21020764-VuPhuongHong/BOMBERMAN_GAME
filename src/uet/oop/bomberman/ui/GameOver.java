package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;

public class GameOver {
    private static long startTime;
    private static final int TIME_SHOW_GAME_OVER_IMG = 5000;
    private static ImageView gameOverView;
    public static void createGameOver() {
        startTime = System.currentTimeMillis();

        Image gameOver = new Image("textures/game_over.png");
        gameOverView = new ImageView(gameOver);
        gameOverView.setX(96);
        gameOverView.setY(1);
        gameOverView.setScaleX(1.24);

        BombermanGame.root2.getChildren().add(gameOverView);
        BombermanGame.thisStage.setScene(BombermanGame.scene2);

        while (System.currentTimeMillis() - startTime <= TIME_SHOW_GAME_OVER_IMG) {

        }
        BombermanGame.thisStage.setScene(BombermanGame.scene);
        BombermanGame.root2.getChildren().remove(gameOverView);
    }
}
