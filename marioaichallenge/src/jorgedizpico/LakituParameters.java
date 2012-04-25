package jorgedizpico;

import dk.itu.mario.MarioInterface.GamePlay;

public class LakituParameters {
	
	public int I_START_PLATFORM = 5;
	public int I_END_PLATFORM = 7;
	public int I_EXIT_OFFSET = 5; // offset always smaller than end platform
	public int I_HEIGHT_MARGIN = 2;
	public int I_FLAT_MIN = 2;
	public int I_JUMP_OFFSET = -5;
	public int I_JUMP_RANGE = 10;
	public int I_BLOCK_HOVER_HEIGHT = 3;

  	public double CHANCE_GAP = 0.10;
  	
	public double CHANCE_GAP_HILL = 0.0;
	public double CHANCE_GAP_BOX = 0.0;
	public double CHANCE_GAP_VANILLA = 1.0;
	public double CHANCE_GAP_VANILLA_CHANGE = 0.7;
	
	public int GAP_VANILLA_MIN = 2;
	public int GAP_VANILLA_MAX = 7;
	
	public int GAP_MAXLENGTH = 7;
	
  	public double CHANCE_VERT = 0.25;	
  	
	public double CHANCE_VERT_PIPE = 0.0;
	public double CHANCE_VERT_STAIRS = 0.0;
	public double CHANCE_VERT_HILL = 1.0;
	
	public double CHANCE_BLOCK_ROW = 0.45;
	
	public double CHANCE_BLOCK_POWERUP = 0.05;
	public double CHANCE_BLOCK_COIN = 0.2;
	public double CHANCE_BLOCK_EMPTY = 1.0;
	
	// start and end acts as buffers on the flatlands, so:
	// minlength - start - end -> actual number of blocks placed
	public int BLOCK_ROW_START = 2;
	public int BLOCK_ROW_END = 2;
	public int BLOCK_ROW_MINLENGTH = 8;
	
	public double CHANCE_ENEMY_ROW = 0.7;
	
	public double CHANCE_ENEMY_SINGLE = 0.15;
	public double CHANCE_ENEMY_REDKOOPA = 0.2;
	public double CHANCE_ENEMY_GREENKOOPA = 0.4;
	public double CHANCE_ENEMY_SPIKY = 0.5;
	
	public double CHANCE_ENEMY_WINGED = 0.2;

	
	public LakituParameters(GamePlay playerMetrics) {
		// calculate stuff
	}
	
}
