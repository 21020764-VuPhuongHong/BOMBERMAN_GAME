package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.level.Level1;

public class GameOver {
    public static void createGameOver() {
        Image gameOver = new Image("textures/game_over.png");
        ImageView gameOverView = new ImageView(gameOver);
        gameOverView.setX(96);
        gameOverView.setY(0);
        gameOverView.setScaleX(1.24);

        Image replay = new Image("textures/replay_button1.png");
        ImageView replayButtonView = new ImageView(replay);
        replayButtonView.setX(88);
        replayButtonView.setY(270);
        replayButtonView.setScaleX(0.4);
        replayButtonView.setScaleY(0.4);

        BombermanGame.root.getChildren().add(gameOverView);
        BombermanGame.root.getChildren().add(replayButtonView);

        replayButtonView.setOnMouseEntered(mouseEvent -> {
            replayButtonView.setImage(new Image("textures/replay_button2.png"));
        });
        replayButtonView.setOnMouseExited(mouseEvent -> {
            replayButtonView.setImage(new Image("textures/replay_button1.png"));
        });
        replayButtonView.setOnMouseClicked(mouseEvent -> {
            BombermanGame.root.getChildren().remove(gameOverView);
            BombermanGame.root.getChildren().remove(replayButtonView);

            Level1 level1 = new Level1();
            level1.build();

            NextLevel.createLevelImage();
        });
    }
}
