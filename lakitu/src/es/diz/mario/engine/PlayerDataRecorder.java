package es.diz.mario.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dk.itu.mario.engine.DataRecorder;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.RandomLevel;
import dk.itu.mario.scene.LevelScene;

public class PlayerDataRecorder extends DataRecorder {
	
	protected String playerName;

	public PlayerDataRecorder(String playerName, LevelScene levelScene, RandomLevel level,
			boolean[] keys) {
		super(levelScene, level, keys);
		this.playerName = playerName;
	}
	
	public void fillGamePlayMetrics(RandomLevel dummyLevel) {

		try {
			long ts = (System.currentTimeMillis() / 1000L);

			File file = new File("players/" + playerName + "/" + ts + ".log");
			File playerFolder = file.getParentFile();

			if (null != playerFolder)
			    playerFolder.mkdirs();

			FileWriter fw = new FileWriter(file);
			fw.write(detailedLog);
			fw.close();
			dumpGamePlay(ts);
			System.out.println(detailedLog);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	protected void dumpGamePlay(long timeStamp){
        PlayerGamePlay gpm = new PlayerGamePlay(playerName, timeStamp);
		gpm.completionTime = getCompletionTime();
		gpm.totalTime = getTotalTime();////sums all the time, including from previous games if player died
		gpm.jumpsNumber = getTimesJumped();
		gpm.timeSpentDucking = getTotalDuckTime();
		gpm.duckNumber = getTimesDucked();
		gpm.timeSpentRunning = getTotalRunTime();
		gpm.timesPressedRun = getTimesRun();
		gpm.timeRunningRight = getTotalRightTime();
		gpm.timeRunningLeft =  getTotalLeftTime();
		gpm.coinsCollected =  getCoinsCollected();
		gpm.totalCoins = level.COINS;
		gpm.emptyBlocksDestroyed = getBlocksEmptyDestroyed();
		gpm.totalEmptyBlocks = level.BLOCKS_EMPTY;
		gpm.coinBlocksDestroyed = getBlocksCoinDestroyed();
		gpm.totalCoinBlocks = level.BLOCKS_COINS;
		gpm.powerBlocksDestroyed = getBlocksPowerDestroyed();
		gpm.totalpowerBlocks = level.BLOCKS_POWER;
		gpm.kickedShells =  getShellsUnleashed(); //kicked
		gpm.enemyKillByFire = getKillsFire();//Number of Kills by Shooting Enemy
		gpm.enemyKillByKickingShell = getKillsShell();//Number of Kills by Kicking Shell on Enemy
		gpm.totalEnemies = level.ENEMIES;

		gpm.totalTimeLittleMode = getTotalLittleTime();
		gpm.totalTimeLargeMode = getTotalLargeTime();//Time Spent Being Large Mario
		gpm.totalTimeFireMode = getTotalFireTime();//Time Spent Being Fire Mario
		gpm.timesSwichingPower = getSwitchedPower();//Number of Times Switched Between Little, Large or Fire Mario
		gpm.aimlessJumps = J();//aimless jumps
		gpm.percentageBlocksDestroyed = nb();//percentage of all blocks destroyed
		gpm.percentageCoinBlocksDestroyed = ncb();//percentage of coin blocks destroyed
		gpm.percentageEmptyBlockesDestroyed = neb();//percentage of empty blocks destroyed
		gpm.percentagePowerBlockDestroyed = np();//percentage of power blocks destroyed
		gpm.timesOfDeathByFallingIntoGap = dg();//number of death by falling into a gap
		gpm.timesOfDeathByRedTurtle = deaths[SpriteTemplate.RED_TURTLE];
		gpm.timesOfDeathByGreenTurtle = deaths[SpriteTemplate.GREEN_TURTLE];
		gpm.timesOfDeathByGoomba = deaths[SpriteTemplate.GOOMPA];
		gpm.timesOfDeathByArmoredTurtle = deaths[SpriteTemplate.ARMORED_TURTLE];
		gpm.timesOfDeathByJumpFlower = deaths[SpriteTemplate.JUMP_FLOWER];
		gpm.timesOfDeathByCannonBall = deaths[SpriteTemplate.CANNON_BALL];
		gpm.timesOfDeathByChompFlower = deaths[SpriteTemplate.CHOMP_FLOWER];

		gpm.RedTurtlesKilled = kills[SpriteTemplate.RED_TURTLE];
		gpm.GreenTurtlesKilled = kills[SpriteTemplate.GREEN_TURTLE];
		gpm.GoombasKilled = kills[SpriteTemplate.GOOMPA];
		gpm.ArmoredTurtlesKilled = kills[SpriteTemplate.ARMORED_TURTLE];
		gpm.JumpFlowersKilled = kills[SpriteTemplate.JUMP_FLOWER];
		gpm.CannonBallKilled = kills[SpriteTemplate.CANNON_BALL];
		gpm.ChompFlowersKilled = kills[SpriteTemplate.CHOMP_FLOWER];
		gpm.save();
	}

}
