package jorgedizpico.auto;

import jorgedizpico.LakituBuilder;

public interface LakituState {
	
	public abstract LakituState transition();
	
	public int genesis(LakituBuilder lkb);
	
}
