package jorgedizpico;

import java.util.Random;

import jorgedizpico.auto.Automaton;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.level.Level;

public class LakituLevelGenerator implements LevelGenerator {
	
  	protected static Random rand = new Random();
  	protected LakituLevel lvl;
	
	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {

		lvl = new LakituLevel();

		// classify playerMetrics
		// pass type to automaton on creation
		int cluster = Level.TYPE_OVERGROUND;
		
		lvl.setType(cluster);
		
		Automaton aut = new Automaton(cluster);
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
