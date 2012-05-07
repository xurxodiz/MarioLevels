package jorgedizpico;

import java.util.Random;

import jorgedizpico.auto.Automaton;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;

public class LakituLevelGenerator implements LevelGenerator {
	
  	protected static Random rand = new Random();
  	protected LakituLevel lvl;
	
	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {

		lvl = new LakituLevel();
		
		lvl.setType(rand.nextInt(3));

		// classify playerMetrics
		// pass type to automaton on creation
		
		Automaton aut = new Automaton(2);
		Builder lkb = new Builder(lvl);
		
		aut.execute(lkb);
		lkb.fixLevel();
		
		return lvl;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
