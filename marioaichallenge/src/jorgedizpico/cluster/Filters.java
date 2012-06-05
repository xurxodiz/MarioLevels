package jorgedizpico.cluster;

import java.util.Arrays;

public class Filters {
	
	protected static String[] filteredFields = new String[]{
		"ArmoredTurtlesKilled",
		"CannonBallKilled",
		"ChompFlowersKilled",
		"GreenTurtlesKilled",
		"RedTurtlesKilled",
		"EnemyKillByKickingShell",
		"kickedShells",
		"timesOfDeathByArmoredTurtle",
		"timesOfDeathByCannonBall",
		"timesOfDeathByChompFlower",
		"timesOfDeathByFallingIntoGap",
		"timesOfDeathByGreenTurtle",
		"timesOfDeathByJumpFlower",
		"timesOfDeathByRedTurtle"
	};

    public static boolean isOutlierPoint(double x, double y, double z) {
    	return (x < -250 || y < -1000);
    }
    
	
	public static boolean isUsefulField(String s) {
		return !(Arrays.asList(filteredFields).contains(s));
		
	}
	
}
