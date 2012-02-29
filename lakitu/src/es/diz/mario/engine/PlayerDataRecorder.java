package es.diz.mario.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import dk.itu.mario.engine.DataRecorder;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.RandomLevel;
import dk.itu.mario.scene.LevelScene;

public class PlayerDataRecorder extends DataRecorder {
	
	protected String playerName;
	protected long timeStamp = 0L;

	public PlayerDataRecorder(String playerName, LevelScene levelScene, RandomLevel level, boolean[] keys) {
		super(levelScene, level, keys);
		this.playerName = playerName;
	}
	
	public void fillGamePlayMetrics(RandomLevel dummyLevel) {
		
		timeStamp = (System.currentTimeMillis() / 1000L);
		
        PlayerGamePlay gpm = new PlayerGamePlay();
        gpm.copyMetrics(this);

		try {
			
			String pathFolder = System.getProperty("user.dir") + "/../players/" + playerName + "/";
			String pathGPM = pathFolder + timeStamp + ".gpm";
			String pathLog = pathFolder + timeStamp + ".log";
			
			File playerFolder = new File(pathFolder);
			playerFolder.mkdirs();
			
			File fileGPM = new File(pathGPM);
			File fileLog = new File(pathLog);
			
			FileOutputStream fosGPM = new FileOutputStream(fileGPM);
			ObjectOutputStream outGPM =  new ObjectOutputStream(fosGPM);
			
			FileWriter fwLog = new FileWriter(fileLog);
			
			fwLog.write(detailedLog);
			outGPM.writeObject(gpm);
			
			fwLog.close();
			outGPM.close();
			fosGPM.close();
			
			// System.out.println(detailedLog);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public int getTotalCoins() {
		return level.COINS;
	}
	
	public int getTotalEnemies() {
		return level.ENEMIES;
	}
	
	public int getTotalEmptyBlocks() {
		return level.BLOCKS_EMPTY;
	}
	
	public int getTotalCoinBlocks() {
		return level.BLOCKS_COINS;
	}
	
	public int getTotalPowerBlocks() {
		return level.BLOCKS_POWER;
	}
	
	public int getDeathsByRedTurtle() {
		return deaths[SpriteTemplate.RED_TURTLE];
	}
	
	public int getDeathsByGreenTurtle() {
		return deaths[SpriteTemplate.GREEN_TURTLE];
	}
	
	public int getDeathsByGoomba() {
		return deaths[SpriteTemplate.GOOMPA];
	}
	
	public int getDeathsByArmoredTurtle() {
		return deaths[SpriteTemplate.ARMORED_TURTLE];
	}
	
	public int getDeathsByJumpFlower() {
		return deaths[SpriteTemplate.JUMP_FLOWER];
	}
	
	public int getDeathsByCannonBall() {
		return deaths[SpriteTemplate.CANNON_BALL];
	}
	
	public int getDeathsByChompFlower() {
		return deaths[SpriteTemplate.CHOMP_FLOWER];
	}
	
	public int getRedTurtlesKilled() {
		return kills[SpriteTemplate.RED_TURTLE];
	}
	
	public int getGreenTurtlesKilled() {
		return kills[SpriteTemplate.GREEN_TURTLE];
	}
	
	public int getGoombasKilled() {
		return kills[SpriteTemplate.GOOMPA];
	}
	
	public int getArmoredTurtlesKilled() {
		return kills[SpriteTemplate.ARMORED_TURTLE];
	}
	
	public int getJumpFlowersKilled() {
		return kills[SpriteTemplate.JUMP_FLOWER];
	}
	
	public int getCannonballKilled() {
		return kills[SpriteTemplate.CANNON_BALL];
	}
	
	public int getChompFlowersKilled() {
		return kills[SpriteTemplate.RED_TURTLE];
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
}
