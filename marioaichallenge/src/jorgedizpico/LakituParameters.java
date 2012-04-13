package jorgedizpico;

import dk.itu.mario.MarioInterface.GamePlay;

public class LakituParameters {
	
	public int I_START_PLATFORM = 5;
	public int I_END_PLATFORM = 7;
	public int I_EXIT_OFFSET = 5; // offset always smaller than end platform
	public int I_HEIGHT_MARGIN = 2;

  	public double CHANCE_GAP = 0.1;
	public double CHANCE_GAP_HILL = 0.0;
	public double CHANCE_GAP_BOX = 0.0;
	public double CHANCE_GAP_VANILLA = 1.0;
	
	public int GAP_MAXLENGTH = 7;
	
  	public double CHANCE_VERT = 0.3;	
	public double CHANCE_VERT_PIPE = 0.0;
	public double CHANCE_VERT_STAIRS = 0.0;
	public double CHANCE_VERT_HILL = 1.0;
	
	public double VERT_HILL_MIN = 3;
	public double VERT_HILL_MAXEXTRA = 5;
	
	public int VERT_HILL_OFFSET = -5;
	public int VERT_HILL_RANGE = 10;
	
	public LakituParameters(GamePlay playerMetrics) {
		// calculate stuff
	}
	
}
