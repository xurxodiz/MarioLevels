package es.diz.mario.scene;

import java.awt.GraphicsConfiguration;
import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.engine.DataRecorder;
import dk.itu.mario.engine.MarioComponent;
import dk.itu.mario.engine.util.FileHandler;
import dk.itu.mario.level.Level;
import dk.itu.mario.level.RandomLevel;
import dk.itu.mario.level.generator.CustomizedLevelGenerator;
import dk.itu.mario.scene.LevelSceneTest;

public class PlayerLevelScene extends LevelSceneTest {
	
	protected String playerName;
	
	public PlayerLevelScene(GraphicsConfiguration graphicsConfiguration,
			MarioComponent renderer, String playerName) {
		super(graphicsConfiguration, renderer, new Random().nextLong(), 0, 0, false);
		this.playerName = playerName;
	}
	
	public void init() {
		
		// change for my level generator
		CustomizedLevelGenerator clg = new CustomizedLevelGenerator();
		// change to read my stats
		GamePlay gp = new GamePlay();
		gp = gp.read("player.txt");
		String detailedInfo = FileHandler.readFile("DetailedInfo.txt");
		//generate
		currentLevel = (Level)clg.generateLevel(gp);
		
		// change to make my own recorder
		recorder = new DataRecorder(this,(RandomLevel)level,keys);
		super.init();
	}

}
