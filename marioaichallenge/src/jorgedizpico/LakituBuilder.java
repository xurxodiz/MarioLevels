package jorgedizpico;

public class LakituBuilder {
	
	protected LakituLevel lvl;
	
	public LakituBuilder(LakituLevel lvl) {
		this.lvl = lvl;
	}
	
	protected int[] createFlatLand(int x, int y) {
		int width = 2; //lkp.I_FLAT_MIN;
		lvl.addFlatLand(x, width, y);
		return new int[]{x+width, y};
	}

}
