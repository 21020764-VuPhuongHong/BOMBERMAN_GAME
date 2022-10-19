package uet.oop.bomberman.control;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    public static Clip soundGame;
    public static Clip soundBomb;
    public static Clip soundBomberDie;
    public static Clip soundClick;
    public static Clip soundMoveInItem;
    public static Clip soundWinGame;
    public static Clip soundNextLevel;
    public static Clip soundGameOver;
    public static Clip soundPutBomb;
    public static Clip soundEnemyDie;
    public static Clip soundLoseHeart;

    public void playSoundGame() {
        try {
            String path_SG = "Sounds/SoundGame.wav";
            URL url_SG = this.getClass().getClassLoader().getResource(path_SG);

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url_SG);
            soundGame = AudioSystem.getClip();
            soundGame.open(audioIn);
        } catch (Exception e) {
            System.out.println("loi 1" + e.getMessage());
        }
        soundGame.start();
        soundGame.loop(Clip.LOOP_CONTINUOUSLY);
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

    public void playSoundBomberDie() {
        try {
            String path_click = "Sounds/SoundBomberDie.wav";
            URL url_click = this.getClass().getClassLoader().getResource(path_click);

            AudioInputStream audioIn_click = AudioSystem.getAudioInputStream(url_click);
            soundBomberDie = AudioSystem.getClip();
            soundBomberDie.open(audioIn_click);
        } catch (Exception e) {
            System.out.println("loi 3" + e.getMessage());
        }
        soundBomberDie.start();
    }

    public void playSoundClick() {
        try {
            String path_SC = "Sounds/sound_buttonClick.wav";
            URL url_SC = this.getClass().getClassLoader().getResource(path_SC);

            AudioInputStream audioIn_SC = AudioSystem.getAudioInputStream(url_SC);
            soundClick = AudioSystem.getClip();
            soundClick.open(audioIn_SC);
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

    public void playSoundWinGame() {
        try {
            String path_WG = "Sounds/win_game_sound.wav";
            URL url_WG = this.getClass().getClassLoader().getResource(path_WG);

            AudioInputStream audioIn_WG = AudioSystem.getAudioInputStream(url_WG);
            soundWinGame = AudioSystem.getClip();
            soundWinGame.open(audioIn_WG);
        } catch (Exception e) {
            System.out.println("loi 6" + e.getMessage());
        }

        soundWinGame.start();
    }

    public void playSoundNextLevel() {
        try {
            String path_NL = "Sounds/next_level.wav";
            URL url_NL = this.getClass().getClassLoader().getResource(path_NL);

            AudioInputStream audioIn_NL = AudioSystem.getAudioInputStream(url_NL);
            soundNextLevel = AudioSystem.getClip();
            soundNextLevel.open(audioIn_NL);
        } catch (Exception e) {
            System.out.println("loi 7" + e.getMessage());
        }

        soundNextLevel.start();
    }

    public void playSoundGameOver() {
        try {
            String path_GO = "Sounds/game_over.wav";
            URL url_GO = this.getClass().getClassLoader().getResource(path_GO);

            AudioInputStream audioIn_GO = AudioSystem.getAudioInputStream(url_GO);
            soundGameOver = AudioSystem.getClip();
            soundGameOver.open(audioIn_GO);
        } catch (Exception e) {
            System.out.println("loi 8" + e.getMessage());
        }

        soundGameOver.start();
    }

    public void playSoundPutBomb() {
        try {
            String path_PB = "Sounds/put_bomb_sound.wav";
            URL url_PB = this.getClass().getClassLoader().getResource(path_PB);

            AudioInputStream audioIn_PB = AudioSystem.getAudioInputStream(url_PB);
            soundPutBomb = AudioSystem.getClip();
            soundPutBomb.open(audioIn_PB);
        } catch (Exception e) {
            System.out.println("loi 9" + e.getMessage());
        }

        soundPutBomb.start();
    }

    public void playSoundEnemyDie() {
        try {
            String path_ED = "Sounds/enemy_die.wav";
            URL url_ED = this.getClass().getClassLoader().getResource(path_ED);

            AudioInputStream audioIn_ED = AudioSystem.getAudioInputStream(url_ED);
            soundEnemyDie = AudioSystem.getClip();
            soundEnemyDie.open(audioIn_ED);
        } catch (Exception e) {
            System.out.println("loi 10" + e.getMessage());
        }

        soundEnemyDie.start();
    }

    public void playSoundLoseHeart() {
        try {
            String path_LH = "Sounds/bomber_loses_heart.wav";
            URL url_LH = this.getClass().getClassLoader().getResource(path_LH);

            AudioInputStream audioIn_LH = AudioSystem.getAudioInputStream(url_LH);
            soundLoseHeart = AudioSystem.getClip();
            soundLoseHeart.open(audioIn_LH);
        } catch (Exception e) {
            System.out.println("loi 11" + e.getMessage());
        }

        soundLoseHeart.start();
    }
}
