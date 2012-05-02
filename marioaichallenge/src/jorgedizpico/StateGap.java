package jorgedizpico;

import java.util.Random;

public class StateGap implements LakituState {
	
	protected double CHANCE_TO_STATEFLAT = 0.95;
	protected double CHANCE_TO_STATEPIPE = 1.0;

	protected Random rand = new Random();
	
	@Override
	public LakituState transition() {
		double roll = rand.nextDouble();
		if (roll < CHANCE_TO_STATEFLAT)
			return new StateFlat();
		else if (roll < CHANCE_TO_STATEPIPE);
			return new StatePipe();
	}

	@Override
	public int genesis(LakituBuilder lkb) {
		return lkb.createGap();
	}

}