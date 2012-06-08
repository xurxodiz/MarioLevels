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

    public static boolean isOutlierPointXYZT(double x, double y, double z, double t) {
    	return isOutlierPointX(x)
    		|| isOutlierPointY(y)
    		|| isOutlierPointZ(z)
    		|| isOutlierPointT(t);
    }
    
    public static boolean isOutlierPointX(double x) { return (x < -250); }
    public static boolean isOutlierPointY(double y) { return (y < -225); }
    public static boolean isOutlierPointZ(double z) { return (z < -750); }
    public static boolean isOutlierPointT(double t) { return (t < -225); }
	
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
