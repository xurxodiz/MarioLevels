package jorgedizpico;

import java.util.Random;

public abstract class LakituState {

	protected Random rand;
	
	public LakituState() {
	}
	
	public abstract LakituState transition();
	
	protected abstract int genesis(LakituBuilder lkb, int x);
	
}
