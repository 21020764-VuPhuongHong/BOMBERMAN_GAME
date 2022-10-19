package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.level.Level1;

public class WinGame {

    public static void createWinGameView() {
        Image winGameImg = new Image("textures/winning.png");
        ImageView winGameImgView = new ImageView(winGameImg);
        winGameImgView.setX(-464.5);
        winGameImgView.setY(-375);
        winGameImgView.setScaleX(0.517);
        winGameImgView.setScaleY(0.3728);
        BombermanGame.root.getChildren().add(winGameImgView);

        Image replayButton = new Image("textures/replay_button1.png");
        ImageView replayButtonView = new ImageView(replayButton);
        replayButtonView.setX(83);
        replayButtonView.setY(260);
        replayButtonView.setScaleX(0.4);
        replayButtonView.setScaleY(0.4);

        BombermanGame.root.getChildren().add(replayButtonView);

        replayButtonView.setOnMouseEntered(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            replayButtonView.setImage(new Image("textures/replay_button2.png"));
        });

        replayButtonView.setOnMouseExited(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            replayButtonView.setImage(new Image("textures/replay_button1.png"));
        });

        replayButtonView.setOnMouseClicked(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            BombermanGame.root.getChildren().remove(winGameImgView);
            BombermanGame.root.getChildren().remove(replayButtonView);
            Level1 level1 = new Level1();
            level1.build();
            NextLevel.createLevelImage();
        });
    }
}
