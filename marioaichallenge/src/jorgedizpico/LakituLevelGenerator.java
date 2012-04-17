package jorgedizpico;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;

public class LakituLevelGenerator implements LevelGenerator {
	
  	protected static Random rand = new Random();
  	protected LakituParameters lkp;
	
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
		lkp = new LakituParameters(playerMetrics);
		ground_pass(lvl);
		return lvl;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected int start_platform(LakituLevel lvl, LakituParameters lkp) {
		
		int initY = (int) (rand.nextDouble()*lvl.getHeight()/2) + (lvl.getHeight()/2);
		initY = lvl.constrain_height(initY);
		
		lvl.setGroundHeight(0, initY);
		lvl.addFlatLand(1, lkp.I_START_PLATFORM-1);
		
		return initY;
	}
	
	protected void ground_pass(LakituLevel lvl) {
				
		/*int length = 0;
		boolean inGap = false;*/
				
		start_platform(lvl, lkp);
		
		// loop optimization
		int middlestart = lkp.I_START_PLATFORM;
		int middleend = lvl.getWidth() - lkp.I_END_PLATFORM;
		
		int width = 2;
		
		
		for (int x = middlestart; x < middleend; x += width) {
			
			double roll = rand.nextDouble();
			
			if (roll < lkp.CHANCE_GAP) {
				roll = rand.nextDouble();
				
				if (roll < lkp.CHANCE_GAP_HILL);
				else if (roll < lkp.CHANCE_GAP_BOX);
				else if (roll < lkp.CHANCE_GAP_VANILLA) {
					width = gap_vanilla_length(middleend - x);
					lvl.addGap(x, gap_change(), width);
				}
				
			} else if (roll < lkp.CHANCE_VERT) {
				roll = rand.nextDouble();
				
				if (roll < lkp.CHANCE_VERT_PIPE);
				else if (roll < lkp.CHANCE_VERT_STAIRS);
				else if (roll < lkp.CHANCE_VERT_HILL) {
					width = vert_hill_length(middleend - x);
					lvl.addHillChange(x, hill_change(), width);
					
				}
				
			} else {
				width = 3;
				lvl.addFlatLand(x, width);
			}
		}
					
		// end_platform()
		lvl.addFlatLand(middleend, lkp.I_END_PLATFORM);
		
		lvl.setExit(lvl.getWidth() - lkp.I_EXIT_OFFSET);
		lvl.fixWalls();
		
	}
	
	public int hill_change() {
		return (int) (lkp.JUMP_OFFSET + lkp.JUMP_RANGE * rand.nextDouble());
	}
	
	public int gap_change() {
		int d = (int) (lkp.JUMP_OFFSET + lkp.JUMP_RANGE * rand.nextDouble());
		if (d > 0) d = -d;
		return d;
	}
	
	public int vert_hill_length(int max) {
		int candidate = (int) (lkp.VERT_HILL_MIN + (lkp.VERT_HILL_MAX - lkp.VERT_HILL_MIN) * rand.nextDouble());
		if (candidate > max) candidate = max;
		return candidate;
	}
	
	public int gap_vanilla_length(int max) {
		int candidate = (int) (lkp.GAP_VANILLA_MIN + (lkp.GAP_VANILLA_MAX - lkp.GAP_VANILLA_MIN)* rand.nextDouble());
		if (candidate > max) candidate = max;
		return candidate;
	}
	
}
