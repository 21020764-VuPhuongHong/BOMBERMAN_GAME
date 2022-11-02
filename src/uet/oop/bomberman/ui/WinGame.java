package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;

public class WinGame {
    private static long startTime;
    private static final int TIME_SHOW_WINNING_GAME_IMG = 5000;
    private static ImageView winGameImgView;

    public static void createWinGameView() {
        startTime = System.currentTimeMillis();

        Image winGameImg = new Image("textures/winning.png");
        winGameImgView = new ImageView(winGameImg);
        winGameImgView.setX(-464.5);
        winGameImgView.setY(-375);
        winGameImgView.setScaleX(0.517);
        winGameImgView.setScaleY(0.3728);

        BombermanGame.root2.getChildren().add(winGameImgView);
        BombermanGame.thisStage.setScene(BombermanGame.scene2);

        while (System.currentTimeMillis() - startTime <= TIME_SHOW_WINNING_GAME_IMG) {

        }

        BombermanGame.thisStage.setScene(BombermanGame.scene);
        BombermanGame.root2.getChildren().remove(winGameImgView);
    }
}
