package es.diz.mario.scene;

import java.awt.GraphicsConfiguration;
import java.util.Random;

import dk.itu.mario.engine.MarioComponent;
import dk.itu.mario.level.RandomLevel;
import dk.itu.mario.scene.LevelSceneTest;
import es.diz.mario.engine.PlayerDataRecorder;

public class PlayerLevelSceneTest extends LevelSceneTest {
	
	protected String playerName;
	
	public PlayerLevelSceneTest(GraphicsConfiguration graphicsConfiguration,
			MarioComponent renderer, String playerName) {
		super(graphicsConfiguration, renderer, new Random().nextLong(), 0, 0, false);
		this.playerName = playerName;
	}
	
	public void init() {
		
		// change for my level generator
		// CustomizedLevelGenerator clg = new CustomizedLevelGenerator();
		// change to read my stats
		// GamePlay gp = new GamePlay();
		// gp = gp.read("player.txt");
		// String detailedInfo = FileHandler.readFile("DetailedInfo.txt");
		//generate
		// currentLevel = (Level)clg.generateLevel(gp);
		// currentLevel = new RandomLevel(320, 15, levelSeed, levelDifficulty, levelType);
		currentLevel = new RandomLevel(320, 15, levelSeed, levelDifficulty, 2);
		
        try {
			 level = currentLevel.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
        sprites.removeAll(sprites);
        
		// change to make my own recorder
		recorder = new PlayerDataRecorder(playerName, this, (RandomLevel)level, keys);
		super.init();
	}

}
