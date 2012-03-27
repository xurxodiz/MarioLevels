package jorgedizpico;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;

public class LakituLevelGenerator implements LevelGenerator {

	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		Level lvl = new LakituLevel(329,15)
		// int[] waveParams = get_wave(playerMetrics);
		// int[] challengeRates = get_rates(playerMetrics);

		// w = A·sen(b·a + a0) + c
		// A: amplitude, swing of the difficulty
		// c: average difficulty
		// sen/cos: trigonometric function (matter of choice)
		// b: speed of difficulty swings, rate of slope change
		// a0: starting phase
		int[] waveParams = {4, 2, 0, 0, 0};
		int[] challengeRates = {0.4, 0.15, 0.15, ;
				
		int[] mapWave = draw_wave(waveParams);
		ground_pass(lvl, mapWave, challengeRates[0]);
		
		// hill_pass(mapWave, paramArray);
		// pipe_pass(mapWave, paramArray);
		// enemy_pass(mapWave, paramArray);
		// block_pass(mapWave, paramArray);
		// coin_pass(mapWave, paramArray);
		
		int type = new Random().nextInt(3); //0: over, 1: under, 2: castle
		return new LakituLevel(320, 15, new Random().nextLong(), playerSkill, type, playerMetrics);
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int[] process_metrics(GamePlay playerMetrics) {
		// takes the metrics, produces an array of parameters we'll need
		// possible ANN?
		return null;
	}

	private int[] draw_wave(int[] waveParams) {
		// takes skill and builds a tension wave
		return null;
	}
	
	private void ground_pass(int[] mapWave, int[] paramArray) {
		// apply probabilities from the paramArray to fill the mapWave
	}
	
}
