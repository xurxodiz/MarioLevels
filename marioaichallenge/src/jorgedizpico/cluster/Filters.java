package jorgedizpico.cluster;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class Filters {
	
	protected static String[] skippedFields = new String[]{
		"ArmoredTurtlesKilled",
		"CannonBallKilled",
		"ChompFlowersKilled",
		"GreenTurtlesKilled",
		"RedTurtlesKilled",
		"enemyKillByKickingShell",
		"kickedShells",
		"timesOfDeathByArmoredTurtle",
		"timesOfDeathByCannonBall",
		"timesOfDeathByChompFlower",
		"timesOfDeathByFallingIntoGap",
		"timesOfDeathByGreenTurtle",
		"timesOfDeathByJumpFlower",
		"timesOfDeathByRedTurtle"
	};

    public static boolean isOutlierPoint(double x, double y, double z, double t) {
    	return (x < -200 || z < -175 || t < -500);
    }
    
    public static boolean isOutlierPoint(double x, double y, double z) {
    	return (x < -200 || z < -175);
    }
    
    public static boolean isOutlierPoint(double x, double y) {
    	return (x < -75 || y < -200);
    } 
	
	public static boolean isSkippedField(String s) {
		return (Arrays.asList(skippedFields).contains(s));
		
	}
	
	
	public static int numSkippedFields() {
		return skippedFields.length;
	}
	
	
	public class FieldComparator implements Comparator<Field> {
		   
	    public int compare(Field f1, Field f2){

	        String name1 = f1.getName();        
	        String name2 = f2.getName();
	       
	        return name1.compareTo(name2);
	    }
	   
	}
	
	public class FileComparator implements Comparator<File> {
		   
	    public int compare(File f1, File f2){

	        String name1 = f1.getName();        
	        String name2 = f2.getName();
	       
	        return name1.compareTo(name2);
	    }
	   
	}
	
}
