package es.diz.mario.engine;

import dk.itu.mario.MarioInterface.GamePlay;

public class PlayerGamePlay extends GamePlay {
	
	private static final long serialVersionUID = 1L;
	
	public String playerName;
	public long timeStamp;

	/*public static PlayerGamePlay load(String playerName, long timeStamp){
		FileInputStream fis = null;
	    ObjectInputStream in = null;
	    PlayerGamePlay gp =  null;
		String fileName = "players/" + playerName + "/" + timeStamp + ".gp";
		try {
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			gp = (PlayerGamePlay)in.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gp;
	}*/
	
	public void copyMetrics(PlayerDataRecorder data) {
		this.completionTime = data.getCompletionTime();
		this.totalTime = data.getTotalTime();////sums all the time, including from previous games if player died
		this.jumpsNumber = data.getTimesJumped();
		this.timeSpentDucking = data.getTotalDuckTime();
		this.duckNumber = data.getTimesDucked();
		this.timeSpentRunning = data.getTotalRunTime();
		this.timesPressedRun = data.getTimesRun();
		this.timeRunningRight = data.getTotalRightTime();
		this.timeRunningLeft =  data.getTotalLeftTime();
		this.coinsCollected =  data.getCoinsCollected();
		this.totalCoins = data.getTotalCoins();
		this.emptyBlocksDestroyed = data.getBlocksEmptyDestroyed();
		this.totalEmptyBlocks = data.getTotalEmptyBlocks();
		this.coinBlocksDestroyed = data.getBlocksCoinDestroyed();
		this.totalCoinBlocks = data.getTotalCoinBlocks();
		this.powerBlocksDestroyed = data.getBlocksPowerDestroyed();
		this.totalpowerBlocks = data.getTotalPowerBlocks();
		this.kickedShells =  data.getShellsUnleashed(); //kicked
		this.enemyKillByFire = data.getKillsFire();//Number of Kills by Shooting Enemy
		this.enemyKillByKickingShell = data.getKillsShell();//Number of Kills by Kicking Shell on Enemy
		this.totalEnemies = data.getTotalEnemies();

		this.totalTimeLittleMode = data.getTotalLittleTime();
		this.totalTimeLargeMode = data.getTotalLargeTime();//Time Spent Being Large Mario
		this.totalTimeFireMode = data.getTotalFireTime();//Time Spent Being Fire Mario
		this.timesSwichingPower = data.getSwitchedPower();//Number of Times Switched Between Little, Large or Fire Mario
		this.aimlessJumps = data.J();//aimless jumps
		this.percentageBlocksDestroyed = data.nb();//percentage of all blocks destroyed
		this.percentageCoinBlocksDestroyed = data.ncb();//percentage of coin blocks destroyed
		this.percentageEmptyBlockesDestroyed = data.neb();//percentage of empty blocks destroyed
		this.percentagePowerBlockDestroyed = data.np();//percentage of power blocks destroyed
		this.timesOfDeathByFallingIntoGap = data.dg();//number of death by falling into a gap
		this.timesOfDeathByRedTurtle = data.getDeathsByRedTurtle();
		this.timesOfDeathByGreenTurtle = data.getDeathsByGreenTurtle();
		this.timesOfDeathByGoomba = data.getDeathsByGoomba();
		this.timesOfDeathByArmoredTurtle = data.getDeathsByArmoredTurtle();
		this.timesOfDeathByJumpFlower = data.getDeathsByJumpFlower();
		this.timesOfDeathByCannonBall = data.getDeathsByCannonBall();
		this.timesOfDeathByChompFlower = data.getDeathsByChompFlower();

		this.RedTurtlesKilled = data.getRedTurtlesKilled();
		this.GreenTurtlesKilled = data.getRedTurtlesKilled();
		this.GoombasKilled = data.getRedTurtlesKilled();
		this.ArmoredTurtlesKilled = data.getRedTurtlesKilled();
		this.JumpFlowersKilled = data.getRedTurtlesKilled();
		this.CannonBallKilled = data.getRedTurtlesKilled();
		this.ChompFlowersKilled = data.getRedTurtlesKilled();
		
		this.playerName = data.getPlayerName();
		this.timeStamp = data.getTimeStamp();
	}
	
}
