package jorgedizpico;

import java.util.Random;

import jorgedizpico.auto.LakituAutomaton;

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
		// lkp = new LakituParameters(playerMetrics);
		
		lvl.setType(rand.nextInt(3));
		
		//passGround();
		//passBoxes();
		
		LakituAutomaton aut = new LakituAutomaton();
		LakituBuilder lkb = new LakituBuilder(lvl);
		
		aut.execute(lkb);
		lkb.fixLevel();
		
		return lvl;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	
	protected void passGround() {
		
		// loop optimization
		int stageend = lvl.getWidth() - lkp.I_END_PLATFORM;
						
		double roll;
		
		int x,y;
		int pos[] = createStart();
		
		for (x = pos[0], y = pos[1]; x < stageend;	x = pos[0], y = pos[1]) {
			
			roll = rand.nextDouble();
			
			if (roll < lkp.CHANCE_GAP) {
				roll = rand.nextDouble();
				
				if (roll < lkp.CHANCE_GAP_HILL)
					;
				else if (roll < lkp.CHANCE_GAP_BOX)
					;
				else //if (roll < lkp.CHANCE_GAP_VANILLA)
					pos = createGapVanilla(x, y);
				
			} else if (roll < lkp.CHANCE_VERT) {
				roll = rand.nextDouble();
				
				if (roll < lkp.CHANCE_VERT_PIPE)
					;
				else if (roll < lkp.CHANCE_VERT_STAIRS)
					;
				else //if (roll < lkp.CHANCE_VERT_HILL)
					pos = createVertHill(x, y);					
				
			} else
				pos = createFlatLand(x, y);
		}
					
		// end_platform()
		createEnd(x, y);
		lvl.fixWalls();
	}
	
	protected void passBoxes() {
		double roll;
		
		ArrayList<int[]> flats = lvl.getFlatlands();
		flats = splitFilterFlats(flats);
		
		for (int[] flat : flats) {
			roll = rand.nextDouble();
			if (roll < lkp.CHANCE_BLOCK_ROW)
				createBlocks(flat[0], flat[1], flat[2]);
				// double row

		}
		
		for (int[] flat: flats) {
			roll = rand.nextDouble();
			if (roll < lkp.CHANCE_ENEMY_ROW)
				createEnemies(flat[0], flat[1], flat[2]);
				
		}
		
		
		 	   	
		  keep a log of empty flats and another of boxes
		  for each of them
		  	if roll for coins on top
		  		place
		  keep same log of boxes and the still empty
		  for each of them
		  		if roll for enemies
		  			roll for type
		  				place
		 
		 
		
		
	}
	
	protected ArrayList<int[]> splitFilterFlats(ArrayList<int[]> flats) {
		// for loop skips start and end platform
		ArrayList<int[]> newflats = new ArrayList<int[]>();
		for (int i = 1; i < flats.size(); i++) {
			int[] flat = flats.get(i);
			if (flat[1] < lkp.BLOCK_ROW_MINLENGTH)
				continue;
			else if (flat[2] - lkp.I_BLOCK_HOVER_HEIGHT < 0)
				continue;
			//else if (flat[1] > lkp.BLOCK_ROW_MAXLENGTH)
				// split it
			else
				newflats.add(flat);
		}
			

		return newflats;
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
	
	protected void createBlocks(int x, int length, int y) {
		int yy = y - lkp.I_BLOCK_HOVER_HEIGHT;
		double roll;
		for (int i = lkp.BLOCK_ROW_START; i <= length-lkp.BLOCK_ROW_END; i++) {
			roll = rand.nextDouble();
			if (roll < lkp.CHANCE_BLOCK_POWERUP)
				lvl.placeBlockPowerup(x+i, yy);
			else if (roll < lkp.CHANCE_BLOCK_COIN)
				lvl.placeBlockCoin(x+i, yy);
			else //if (roll < lkp.CHANCE_BLOCK_EMPTY)
				lvl.placeBlockEmpty(x+i, yy);
		}
	}

	public void createEnemies(int x, int length, int y) {
		double roll;
		int type;
		boolean winged;
		
		for (int i = 0; i < length; i++) {
			roll = rand.nextDouble();

			if (roll < lkp.CHANCE_ENEMY_SINGLE) {
				
				roll = rand.nextDouble();
				if (roll < lkp.CHANCE_ENEMY_REDKOOPA)
					type = Enemy.ENEMY_RED_KOOPA;
				else if (roll < lkp.CHANCE_ENEMY_GREENKOOPA)
					type = Enemy.ENEMY_GREEN_KOOPA;
				else if (roll < lkp.CHANCE_ENEMY_SPIKY)
					type = Enemy.ENEMY_SPIKY;
				else //if (roll < lkp.CHANCE_ENEMY_GOOMBA)
					type = Enemy.ENEMY_GOOMBA;
				
				winged = rand.nextDouble() < lkp.CHANCE_ENEMY_WINGED;
				
				lvl.setSpriteTemplate(x+i, y-1, new SpriteTemplate(type, winged));
				System.out.println("enemy");
			}
		}
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
	
	*/

}
