package jorgedizpico;

import java.util.Random;

import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.Enemy;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class LakituLevel extends Level implements LevelInterface {

    private GamePlay playerM;

    public LakituLevel(int width, int height, long seed, int difficulty,
                           int type, GamePlay playerMetrics) {
        super(width, height);
        this.playerM = playerMetrics;
    }
    
    public LakituLevel(int width, int height) {
    	this.playerM = null;
    }
}