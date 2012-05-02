package jorgedizpico;

import java.util.Random;

public class StateEnemies implements LakituState {
	
	protected double CHANCE_TO_STATEPIPE = 0.2;
	protected double CHANCE_TO_STATEENEMIES = 0.4;
	protected double CHANCE_TO_STATEFLAT = 0.9;
	protected double CHANCE_TO_STATEGAP = 1.0;

	protected Random rand = new Random();
	
	@Override
	public LakituState transition() {
		double roll = rand.nextDouble();
		if (roll < CHANCE_TO_STATEPIPE)
			return new StatePipe();
		else if (roll < CHANCE_TO_STATEENEMIES)
			return new StateEnemies();
		else if (roll < CHANCE_TO_STATEFLAT)
			return new StateFlat();
		else if (roll < CHANCE_TO_STATEGAP);
			return new StateGap();
	}

	@Override
	public int genesis(LakituBuilder lkb) {
		return lkb.createEnemies();
	}

}
