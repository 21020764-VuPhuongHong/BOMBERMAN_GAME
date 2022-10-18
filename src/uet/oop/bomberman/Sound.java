package uet.oop.bomberman;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Sound {
    public static boolean isSoundGame;
    public static boolean isSoundBomb;
    public static boolean isSoundDie;
    public static Clip soundGame;
    public static Clip soundBomb;
    public static Clip soundDie;
    public static Clip soundClick;
    public static Clip soundMoveInItem;

    public void build() {
        isSoundGame = false;
        isSoundBomb = false;
        isSoundDie = false;
    }

    public void playSoundGame() {
        if(isSoundGame == false) return;
        try {
            String path_SG = "Sounds/SoundGame.wav";
            URL url_SG = this.getClass().getClassLoader().getResource(path_SG);

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url_SG);
            soundGame = AudioSystem.getClip();
            soundGame.open(audioIn);
            //clip.start();
            //clip.loop(10);
        } catch (Exception e) {
            System.out.println("loi 1" + e.getMessage());
        }
        soundGame.start();
        soundGame.loop(10);
    }

    public void playSoundBomb() {
        try {
            String path_SB = "Sounds/SoundBomb.wav";
            URL url_SB = this.getClass().getClassLoader().getResource(path_SB);

            AudioInputStream audioIn_SB = AudioSystem.getAudioInputStream(url_SB);
            soundBomb = AudioSystem.getClip();
            soundBomb.open(audioIn_SB);
        } catch (Exception e) {
            System.out.println("loi 2" + e.getMessage());
        }

        soundBomb.start();
    }

    public void playSoundDie() {
        try {
            String path_die = "Sounds/SoundDie.wav";
            URL url_die = this.getClass().getClassLoader().getResource(path_die);

            AudioInputStream audioIn_click = AudioSystem.getAudioInputStream(url_die);
            soundDie = AudioSystem.getClip();
            soundDie.open(audioIn_click);
        } catch (Exception e) {
            System.out.println("loi 3" + e.getMessage());
        }
        soundDie.start();
    }

    public void playSoundClick() {
        try {
            String path_click = "Sounds/sound_buttonClick.wav";
            URL url_click = this.getClass().getClassLoader().getResource(path_click);

            AudioInputStream audioIn_SB = AudioSystem.getAudioInputStream(url_click);
            soundClick = AudioSystem.getClip();
            soundClick.open(audioIn_SB);
        } catch (Exception e) {
            System.out.println("loi 4" + e.getMessage());
        }

        soundClick.start();
    }

    public void playMoveItem() {
        try {
            String path_MI = "Sounds/moveInItem.wav";
            URL url_MI = this.getClass().getClassLoader().getResource(path_MI);

            AudioInputStream audioIn_MI = AudioSystem.getAudioInputStream(url_MI);
            soundMoveInItem = AudioSystem.getClip();
            soundMoveInItem.open(audioIn_MI);
        } catch (Exception e) {
            System.out.println("loi 5" + e.getMessage());
        }

        soundMoveInItem.start();
    }

    public void update() {
        // mediaPlayerSoundGame.setAutoPlay(true);
        if(isSoundGame == true) {
            playSoundGame();
        }
        if(isSoundBomb == true) {
            playSoundBomb();
            soundBomb.close();
        }
        if(isSoundDie == true) {
            playSoundDie();
        }
    }
}
