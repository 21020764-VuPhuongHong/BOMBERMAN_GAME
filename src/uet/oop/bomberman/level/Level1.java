package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.ConfigLevel;

public class Level1 {
    private static ConfigLevel level1 = new ConfigLevel();
    public Level1() {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        
        level1.buildConfig("res\\levels\\Level1.txt");
    }

    public void build() {
        level1.createMap();
        level1.createEntities();
    }
}