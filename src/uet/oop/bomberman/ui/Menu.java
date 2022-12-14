package uet.oop.bomberman.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.level.Level1;

public class Menu {
    private static ImageView menuBgView;
    private static ImageView nameView;
    private static ImageView playButtonView;
    private static ImageView exitButtonView;
    private static ImageView instructionButtonView;
    private static ImageView highScoreButtonView;

    public static void createMenuBackground() {
        Image menuBg = new Image("textures/menuBg.png");
        menuBgView = new ImageView(menuBg);
        menuBgView.setX(0);
        menuBgView.setY(-76.5);
        menuBgView.setScaleY(0.8799);
        BombermanGame.root.getChildren().add(menuBgView);
    }

    public static void createBbmText() {
        Image name = new Image("textures/Bomberman_Logo.png");
        nameView = new ImageView(name);
        nameView.setX(358);
        nameView.setY(64);
        BombermanGame.root.getChildren().add(nameView);
    }

    public static void createPlayButton() {
        Image playButton = new Image("textures/play_button1.png");
        playButtonView = new ImageView(playButton);
        playButtonView.setX(130);
        playButtonView.setY(262);
        playButtonView.setScaleX(0.5);
        playButtonView.setScaleY(0.5);
        BombermanGame.root.getChildren().add(playButtonView);
    }

    public static void createExitButton() {
        Image exitButton = new Image("textures/exit_button1.png");
        exitButtonView = new ImageView(exitButton);
        exitButtonView.setX(530);
        exitButtonView.setY(260);
        exitButtonView.setScaleX(0.5);
        exitButtonView.setScaleY(0.5);
        BombermanGame.root.getChildren().add(exitButtonView);
    }

    public static void createInstructionButton() {
        Image instructionButton = new Image("textures/instruction1.png");
        instructionButtonView = new ImageView(instructionButton);
        instructionButtonView.setX(-7);
        instructionButtonView.setY(-140);
        instructionButtonView.setScaleX(0.15);
        instructionButtonView.setScaleY(0.15);
        BombermanGame.root.getChildren().add(instructionButtonView);
    }

    public static void createHighScoreButton() {
        Image highScoreButton = new Image("textures/high_score_button1.png");
        highScoreButtonView = new ImageView(highScoreButton);
        highScoreButtonView.setX(421);
        highScoreButtonView.setY(236);
        highScoreButtonView.setScaleX(0.43);
        highScoreButtonView.setScaleY(0.43);
        BombermanGame.root.getChildren().add(highScoreButtonView);
    }

    public static void createMenu() {
        createMenuBackground();
        createBbmText();
        createPlayButton();
        createExitButton();
        createInstructionButton();
        createHighScoreButton();
    }

    public static void handleInsSubmenu() {
        Image submenu = new Image("textures/instruction_submenu.png");
        ImageView submenuView = new ImageView(submenu);
        submenuView.setX(180);
        submenuView.setY(40);
        submenuView.setScaleX(1.3);
        submenuView.setScaleY(1.3);

        Image returnButton = new Image("textures/return_button1.png");
        ImageView returnButtonView = new ImageView(returnButton);
        returnButtonView.setX(265);
        returnButtonView.setY(270);
        returnButtonView.setScaleX(0.3);
        returnButtonView.setScaleY(0.3);

        BombermanGame.root.getChildren().addAll(submenuView, returnButtonView);

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
            BombermanGame.root.getChildren().removeAll(returnButtonView, submenuView);
        });
    }

    public static void handleMenuButtons() {
        playButtonView.setOnMouseEntered(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            playButtonView.setImage(new Image("textures/play_button2.png"));
        });
        playButtonView.setOnMouseExited(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            playButtonView.setImage(new Image("textures/play_button1.png"));
        });
        playButtonView.setOnMouseClicked(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();

            BombermanGame.root.getChildren().removeAll(menuBgView, nameView);
            BombermanGame.root.getChildren().removeAll(playButtonView, exitButtonView, instructionButtonView, highScoreButtonView);

            Level1 level1 = new Level1();
            level1.build();
            InfoLevel.createLevelInfo();
            NextLevel.createLevelImage();
        });

        exitButtonView.setOnMouseEntered(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            exitButtonView.setImage(new Image("textures/exit_button2.png"));
        });
        exitButtonView.setOnMouseExited(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            exitButtonView.setImage(new Image("textures/exit_button1.png"));
        });
        exitButtonView.setOnMouseClicked(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            BombermanGame.thisStage.close();
        });

        instructionButtonView.setOnMouseEntered(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            instructionButtonView.setImage(new Image("textures/instruction2.png"));
        });
        instructionButtonView.setOnMouseExited(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            instructionButtonView.setImage(new Image("textures/instruction1.png"));
        });
        instructionButtonView.setOnMouseClicked(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            handleInsSubmenu();
        });

        highScoreButtonView.setOnMouseEntered(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            highScoreButtonView.setImage(new Image("textures/high_score_button2.png"));
        });
        highScoreButtonView.setOnMouseExited(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            highScoreButtonView.setImage(new Image("textures/high_score_button1.png"));
        });
        highScoreButtonView.setOnMouseClicked(mouseEvent -> {
            BombermanGame.soundControl.playSoundClick();
            HighScore.renderHighScoreView();
        });
    }
}
