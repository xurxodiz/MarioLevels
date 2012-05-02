package jorgedizpico;

import java.util.Random;

public class StatePipe implements LakituState {
	
	protected double CHANCE_TO_ENEMIES = 0.3;
	protected double CHANCE_TO_STATEFLAT = 0.7;
	protected double CHANCE_TO_STATEGAP = 1.0;

	protected Random rand = new Random();
	
	@Override
	public LakituState transition() {
		double roll = rand.nextDouble();
		if (roll < CHANCE_TO_ENEMIES)
			return new StateEnemies();
		else if (roll < CHANCE_TO_STATEFLAT)
			return new StateFlat();
		else if (roll < CHANCE_TO_STATEGAP);
			return new StateGap();
	}

	@Override
	public int genesis(LakituBuilder lkb) {
		return lkb.createPipe();
	}

}
