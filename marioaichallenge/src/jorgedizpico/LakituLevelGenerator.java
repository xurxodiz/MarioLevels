package jorgedizpico;

import java.util.ArrayList;
import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;

public class LakituLevelGenerator implements LevelGenerator {
	
  	protected static Random rand = new Random();
  	
  	 int I_START_PLATFORM = 5;
  	 int I_END_PLATFORM = 7;
  	 int I_EXIT_OFFSET = 5; // offset always smaller than end platform
  	 int I_HEIGHT_MARGIN = 2;
	
	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		/*
		// w(x) = A·sen(b·x + a0) + c
		// A: amplitude, swing of the difficulty
		// b: speed of difficulty swings, rate of slope change
		// a0: starting phase
		// c: average difficulty
		float[] waveParams = get_wave(playerMetrics);
		float[] rates = get_rates(playerMetrics); // decor, gaps, climbs, enemies
			
		int[] mapWave = draw_wave(waveParams);
		// for (int i = 0; i < 320; i++)
		//	wave[i] = (int) Math.round(wp[0] * Math.sin(wp[1] * i + wp[2]) + wp[3]);
		mapWave = gaps_pass(lvl, mapWave, rates[1]); // vanilla, box, hill
		mapWave = climbs_pass(lvl, mapWave, rates[2]); // stairs, slope, pipes
		mapWave = enemies_pass(lvl, mapWave, rates[3]); // koopa, greenturtle, redturtle, cannon, piranha
		
		decor_pass(lvl, rates[0]); // hills, blocks, coins
		
		int type = new Random().nextInt(3); //0: over, 1: under, 2: castle
		return new LakituLevel(320, 15, new Random().nextLong(), 3, type, playerMetrics);
		*/
		LakituLevel lvl = new LakituLevel();
		gaps_pass(lvl, new int[2], 0.3f);
		return lvl;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected int start_platform(LakituLevel lvl, int length) {
		
		int initY = (int) (I_HEIGHT_MARGIN + rand.nextDouble()*(lvl.getHeight() - I_HEIGHT_MARGIN));
		
		for (int x = 0; x < length; x++) // initial platform
			lvl.setGroundHeight(x, initY);
		
		return initY;
	}
	
	protected int constrain_height(LakituLevel lvl, int y) {
		return Math.max(I_HEIGHT_MARGIN, Math.min(lvl.getHeight() - I_HEIGHT_MARGIN, y));
	}
	
	protected int[] gaps_pass(LakituLevel lvl, int[] mapWave, float gapRate) {

   	 double CHANCE_GAP = 0.1;
   	 double CHANCE_HILL_CHANGE = 0.1;
   	 
		 int GAP_LENGTH = 7;
		 double GAP_OFFSET = -5;
		 double GAP_RANGE = 10;
				
		boolean justChanged = false;
		int length = 0;
		int landHeight = lvl.getHeight() - 1;
				
		int lastY, y, nextY;
		lastY = y = nextY = start_platform(lvl, I_START_PLATFORM);
		
		for (int x = I_START_PLATFORM; x < lvl.getWidth() - I_END_PLATFORM; x++) {
			
			// need more ground (current gap is too large)
			if (length > GAP_LENGTH && y >= lvl.getHeight()) {
				nextY = landHeight;
				justChanged = true;
				length = 1;
			}
			// adjust ground level
			else if (rand.nextDouble() < CHANCE_HILL_CHANGE && !justChanged) {
				nextY += (int)(GAP_OFFSET + GAP_RANGE*rand.nextDouble());
				nextY = constrain_height(lvl, nextY);
				justChanged = true;
				length = 1;
			}
			// add a gap
			// checks that the gap constraint is not violated
			else if (y < lvl.getHeight() &&  rand.nextDouble() < CHANCE_GAP && !justChanged) {
				landHeight = Math.min(lvl.getHeight() - I_HEIGHT_MARGIN, lastY);
				nextY = lvl.getHeight();
				justChanged = true;
				length = 1;
			}
			// continue placing flat ground
			else {
				length++;
				justChanged = false;
			}
			
			lvl.setGroundHeight(x, y);
			lastY = y;			
			y = nextY;
		}
		
		nextY = (y >= lvl.getHeight())? landHeight: y;
		
		for (int x = lvl.getWidth() - I_END_PLATFORM; x < lvl.getWidth(); x++) { // final platform
			lvl.setGroundHeight(x, y);
			lastY = y;
			y = nextY;
		}
		
		lvl.setExit(lvl.getWidth() - I_EXIT_OFFSET);
		lvl.fixWalls();
		
		return mapWave; // once decreased
	}
	
}
