package jorgedizpico;

import java.util.Random;

public class StateGap implements LakituState {
	
	protected double CHANCE_TO_STATEFLAT = 0.5;
	protected double CHANCE_TO_STATEGAP = 1.0;

	protected Random rand = new Random();
	
	@Override
	public LakituState transition() {
		double roll = rand.nextDouble();
		if (roll < CHANCE_TO_STATEFLAT)
			return new StateFlat();
		else if (roll < CHANCE_TO_STATEGAP);
			return new StateGap();
	}

	@Override
	public int genesis(LakituBuilder lkb, int x) {
		int width = lkb.I_GAP_MIN;
		lkb.createGap(x, width);
		return x+width;
	}

}