package jorgedizpico;

import java.util.ArrayList;
import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;

public class LakituLevelGenerator implements LevelGenerator {
	
  	protected static Random rand = new Random();
  	protected LakituLevel lvl;
  	protected LakituParameters lkp;
	
	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {

		lvl = new LakituLevel();
		lkp = new LakituParameters(playerMetrics);
		
		lvl.setType(rand.nextInt(3));
		
		passGround();
		passBoxes();
		
		return lvl;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void passGround() {
		
		// loop optimization
		int stageend = lvl.getWidth() - lkp.I_END_PLATFORM;
						
		int x,y;
		int pos[] = createStart();
		
		for (x = pos[0], y = pos[1]; x < stageend;	x = pos[0], y = pos[1]) {
			
			double roll = rand.nextDouble();
			
			if (roll < lkp.CHANCE_GAP) {
				roll = rand.nextDouble();
				
				if (roll < lkp.CHANCE_GAP_HILL)
					;
				else if (roll < lkp.CHANCE_GAP_BOX)
					;
				else if (roll < lkp.CHANCE_GAP_VANILLA)
					pos = createGapVanilla(x, y);
				
			} else if (roll < lkp.CHANCE_VERT) {
				roll = rand.nextDouble();
				
				if (roll < lkp.CHANCE_VERT_PIPE)
					;
				else if (roll < lkp.CHANCE_VERT_STAIRS)
					;
				else if (roll < lkp.CHANCE_VERT_HILL)
					pos = createVertHill(x, y);					
				
			} else
				pos = createFlatLand(x, y);
		}
					
		// end_platform()
		createEnd(x, y);
		lvl.fixWalls();
	}
	
	protected void passBoxes() {
		ArrayList<int[]> flats = lvl.getFlatlandsFiltered();
		/*
		 
		 for each flat
		 	if roll for boxes
		 	   place
		 	   if roll higher
		 	   	place double row
		 	   	
		  keep a log of empty flats and another of boxes
		  for each of them
		  	if roll for coins on top
		  		place
		  keep same log of boxes and the still empty
		  for each of them
		  		if roll for enemies
		  			roll for type
		  				place
		 
		 */
		
		
	}
	
	
	protected int[] createStart() {
		
		int x = lkp.I_START_PLATFORM;
		int y = (int) (rand.nextDouble()*lvl.getHeight()/2) + (lvl.getHeight()/2);
		y = constrainHeight(y);
		
		lvl.addFlatLand(0, x, y);
		
		return new int[]{x,y};
	}
	
	protected void createEnd(int x, int y) {
		int stageend = lvl.getWidth() - lkp.I_END_PLATFORM;
		lvl.addFlatLand(stageend, lkp.I_END_PLATFORM, y);
		lvl.setExit(lvl.getWidth() - lkp.I_EXIT_OFFSET);
	}
	
	protected int[] createGapVanilla(int x, int y) {
		int stageend = lvl.getWidth() - lkp.I_END_PLATFORM;
		
		int width = lengthGapVanilla(stageend - x);
		lvl.addGap(x, width);

		double roll = rand.nextDouble();
		if (roll < lkp.CHANCE_GAP_VANILLA_CHANGE)
			y = changeGap(y);
		
		lvl.addFlatLand(x+width, lkp.I_FLAT_MIN, y);
		width += lkp.I_FLAT_MIN;
		
		return new int[]{x+width, y};
	}
	
	protected int[] createVertHill(int x, int y) {
		y = changeHill(y);
		return createFlatLand(x,y);
	}
	
	protected int[] createFlatLand(int x, int y) {
		int width = lkp.I_FLAT_MIN;
		lvl.addFlatLand(x, width, y);
		return new int[]{x+width, y};
	}
	
	protected int constrainHeight(int y) {
		return Math.max(lkp.I_FLAT_MIN, Math.min(y, lvl.getHeight()-lkp.I_FLAT_MIN));
	}
	
	protected int changeHill(int y) {
		int yy = y + (int) (lkp.I_JUMP_OFFSET + lkp.I_JUMP_RANGE * rand.nextDouble());
		yy = constrainHeight(yy);
		if (y < lvl.getHeight()/2 && yy < y)
			yy = constrainHeight(y + (y-yy));
		return yy;
	}
	
	protected int changeGap(int y) {
		// it's just better to consider you can't jump "up"
		// so only "down" changes are allowed
		int yy = changeHill(y);
		if (yy < y) yy = y;
		return yy;
	}
	
	protected int lengthGapVanilla(int max) {
		int candidate = (int) (lkp.GAP_VANILLA_MIN + (lkp.GAP_VANILLA_MAX - lkp.GAP_VANILLA_MIN)* rand.nextDouble());
		if (candidate > max) candidate = max;
		return candidate;
	}
	
}
