package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class InfoLevel {
    private static ImageView bombImgView;
    private static Text levelText, numBombsText, timeLeftText;

    public static void createLevelInfo() {
        levelText = new Text("Level: 1");
        levelText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 28));
        levelText.setFill(Color.WHITE);
        levelText.setX(450);
        levelText.setY(26);

        Image bombImg = new Image("textures/bomb_infoLevel.png");
        bombImgView = new ImageView(bombImg);
        bombImgView.setX(635);
        bombImgView.setY(-2);
        bombImgView.setScaleX(0.7);
        bombImgView.setScaleY(0.7);

        numBombsText = new Text(": " + Bomb.numOfBombs);
        numBombsText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 28));
        numBombsText.setFill(Color.WHITE);
        numBombsText.setX(660);
        numBombsText.setY(26);

        timeLeftText = new Text("Time: " + BombermanGame.TIME_FOR_LEVEL);
        timeLeftText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 28));
        timeLeftText.setFill(Color.WHITE);
        timeLeftText.setX(800);
        timeLeftText.setY(26);

        Pane pane = new Pane();
        pane.setMinSize(BombermanGame.WIDTH * Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        pane.setMaxSize(BombermanGame.WIDTH * Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        pane.setStyle("-fx-background-color: #000033");
        pane.getChildren().addAll(levelText, bombImgView, numBombsText, timeLeftText);

        BombermanGame.root.getChildren().add(pane);
    }

    public static void updateLevelInfo() {
        if (levelText != null && numBombsText != null && timeLeftText != null) {
            levelText.setText("Level: " + BombermanGame.level);
            numBombsText.setText(": " + Bomb.numOfBombs);
            timeLeftText.setText("Time: " + BombermanGame.timeLeft);
        }
    }
}
