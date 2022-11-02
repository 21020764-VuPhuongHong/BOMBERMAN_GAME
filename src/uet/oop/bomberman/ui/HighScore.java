package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.level.Level1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class HighScore {
    private static Text infoText, yourScoreText, yourScore, rank1Score, rank2Score, rank3Score;
    private static ImageView scoreBgView, rank1View, rank2View, rank3View, replayButtonView, returnButtonView;

    public static void fileWrite() throws IOException {
        FileWriter fileWriter = new FileWriter("res/high_score/HighScore.txt");
        for (int i = 0; i < BombermanGame.NUM_HIGH_SCORES; i++) {
            fileWriter.write(BombermanGame.highScore[i] + " ");
        }
        fileWriter.close();
    }

    public static void handleScore() {
        if (BombermanGame.score > BombermanGame.highScore[0]) {
            BombermanGame.highScore[0] = BombermanGame.score;
            Arrays.sort(BombermanGame.highScore);
            for (int i = 0; i < BombermanGame.NUM_HIGH_SCORES; i++) {
                if (BombermanGame.score == BombermanGame.highScore[i]) {
                    BombermanGame.rank = BombermanGame.NUM_HIGH_SCORES - i;
                    break;
                }
            }
            try {
                fileWrite();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (BombermanGame.score == BombermanGame.highScore[0]) {
            BombermanGame.rank = BombermanGame.NUM_HIGH_SCORES;
        }
    }

    public static void renderYourScoreView() {
        Image scoreBg = new Image("textures/high_score_bg.png");
        scoreBgView = new ImageView(scoreBg);
        scoreBgView.setX(190);
        scoreBgView.setY(20);
        scoreBgView.setScaleX(1.62091);
        scoreBgView.setScaleY(1.098);
        BombermanGame.root.getChildren().add(scoreBgView);

        if (BombermanGame.rank > 0) {
            infoText = new Text("Congratulations! You're in the top 3!");
            infoText.setX(190);
        } else {
            infoText = new Text("Top 3 highest scores: ");
            infoText.setX(320);
        }
        infoText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 48));
        infoText.setFill(Color.WHITE);
        infoText.setY(120);

        yourScoreText = new Text("Your score: ");
        yourScoreText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 42));
        yourScoreText.setFill(Color.WHITE);
        yourScoreText.setX(270);
        yourScoreText.setY(215);

        yourScore = new Text("" + BombermanGame.score);
        yourScore.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 44));
        yourScore.setFill(Color.WHITE);
        yourScore.setX(380);
        yourScore.setY(275);

        Image rank1Img = new Image("textures/rank1.png");
        rank1View = new ImageView(rank1Img);
        rank1View.setX(500);
        rank1View.setY(110);
        rank1View.setScaleX(0.5);
        rank1View.setScaleY(0.5);

        Image rank2Img = new Image("textures/rank2.png");
        rank2View = new ImageView(rank2Img);
        rank2View.setX(500);
        rank2View.setY(180);
        rank2View.setScaleX(0.5);
        rank2View.setScaleY(0.5);

        Image rank3Img = new Image("textures/rank3.png");
        rank3View = new ImageView(rank3Img);
        rank3View.setX(500);
        rank3View.setY(250);
        rank3View.setScaleX(0.5);
        rank3View.setScaleY(0.5);

        rank1Score = new Text("" + BombermanGame.highScore[2]);
        rank1Score.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 38));
        if (BombermanGame.rank == 1) {
            rank1Score.setFill(Color.YELLOW);
        } else {
            rank1Score.setFill(Color.WHITE);
        }
        rank1Score.setX(610);
        rank1Score.setY(175);

        rank2Score = new Text("" + BombermanGame.highScore[1]);
        rank2Score.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 38));
        if (BombermanGame.rank == 2) {
            rank2Score.setFill(Color.YELLOW);
        } else {
            rank2Score.setFill(Color.WHITE);
        }
        rank2Score.setX(610);
        rank2Score.setY(245);

        rank3Score = new Text("" + BombermanGame.highScore[0]);
        rank3Score.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 38));
        if (BombermanGame.rank == 3) {
            rank3Score.setFill(Color.YELLOW);
        } else {
            rank3Score.setFill(Color.WHITE);
        }
        rank3Score.setX(610);
        rank3Score.setY(320);

        Image replayButton = new Image("textures/replay_button1.png");
        replayButtonView = new ImageView(replayButton);
        replayButtonView.setX(84);
        replayButtonView.setY(250);
        replayButtonView.setScaleX(0.37);
        replayButtonView.setScaleY(0.37);

        BombermanGame.root.getChildren().addAll(infoText, yourScoreText, yourScore, replayButtonView);
        BombermanGame.root.getChildren().addAll(rank1View, rank2View, rank3View, rank1Score, rank2Score, rank3Score);

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
            BombermanGame.root.getChildren().removeAll(scoreBgView, infoText, yourScoreText, yourScore, replayButtonView);
            BombermanGame.root.getChildren().removeAll(rank1View, rank2View, rank3View, rank1Score, rank2Score, rank3Score);
            Level1 level1 = new Level1();
            level1.build();
            NextLevel.createLevelImage();
        });
    }

    public static void renderHighScoreView() {
        Image scoreBg = new Image("textures/high_score_bg.png");
        scoreBgView = new ImageView(scoreBg);
        scoreBgView.setX(190);
        scoreBgView.setY(20);
        scoreBgView.setScaleX(1.62091);
        scoreBgView.setScaleY(1.098);
        BombermanGame.root.getChildren().add(scoreBgView);

        infoText = new Text("Top 3 highest scores: ");
        infoText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 48));
        infoText.setFill(Color.WHITE);
        infoText.setX(325);
        infoText.setY(120);

        Image rank1Img = new Image("textures/rank1.png");
        rank1View = new ImageView(rank1Img);
        rank1View.setX(200);
        rank1View.setY(180);
        rank1View.setScaleX(0.75);
        rank1View.setScaleY(0.75);

        Image rank2Img = new Image("textures/rank2.png");
        rank2View = new ImageView(rank2Img);
        rank2View.setX(405);
        rank2View.setY(175);
        rank2View.setScaleX(0.7);
        rank2View.setScaleY(0.7);

        Image rank3Img = new Image("textures/rank3.png");
        rank3View = new ImageView(rank3Img);
        rank3View.setX(610);
        rank3View.setY(165);
        rank3View.setScaleX(0.75);
        rank3View.setScaleY(0.75);

        rank1Score = new Text("" + BombermanGame.highScore[2]);
        rank1Score.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 42));
        rank1Score.setFill(Color.WHITE);
        rank1Score.setX(300);
        rank1Score.setY(240);

        rank2Score = new Text("" + BombermanGame.highScore[1]);
        rank2Score.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 42));
        rank2Score.setFill(Color.WHITE);
        rank2Score.setX(510);
        rank2Score.setY(240);

        rank3Score = new Text("" + BombermanGame.highScore[0]);
        rank3Score.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 42));
        rank3Score.setFill(Color.WHITE);
        rank3Score.setX(715);
        rank3Score.setY(240);

        Image returnButton = new Image("textures/return_button1.png");
        returnButtonView = new ImageView(returnButton);
        returnButtonView.setX(265);
        returnButtonView.setY(280);
        returnButtonView.setScaleX(0.35);
        returnButtonView.setScaleY(0.35);

        BombermanGame.root.getChildren().addAll(infoText, returnButtonView);
        BombermanGame.root.getChildren().addAll(rank1View, rank2View, rank3View, rank1Score, rank2Score, rank3Score);

        returnButtonView.setOnMouseEntered(mouseEvent1 -> {
            BombermanGame.soundControl.playSoundClick();
            returnButtonView.setImage(new Image("textures/return_button2.png"));
        });
        returnButtonView.setOnMouseExited(mouseEvent1 -> {
            BombermanGame.soundControl.playSoundClick();
            returnButtonView.setImage(new Image("textures/return_button1.png"));
        });
        returnButtonView.setOnMouseClicked(mouseEvent1 -> {
            BombermanGame.soundControl.playSoundClick();
            BombermanGame.root.getChildren().removeAll(scoreBgView, infoText, returnButtonView);
            BombermanGame.root.getChildren().removeAll(rank1View, rank2View, rank3View, rank1Score, rank2Score, rank3Score);
        });
    }
}
