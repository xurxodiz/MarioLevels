package jorgedizpico;

public interface LakituState {
	
	public abstract LakituState transition();
	
	public int genesis(LakituBuilder lkb, int x);
	
}
