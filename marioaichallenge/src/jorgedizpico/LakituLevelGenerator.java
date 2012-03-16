package jorgedizpico;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;

public class LakituLevelGenerator implements LevelGenerator {

	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		int difficulty = 5;
		int type = 3;
		return new LakituLevel(320, 15, new Random().nextLong(), difficulty, type, playerMetrics);
		//LevelInterface level = new CustomizedLevel(320,15,new Random().nextLong(),1,1,playerMetrics);
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
