package jorgedizpico;

public class LakituBuilder {
	
	public LakituLevel lvl;
	
	protected int I_START_PLATFORM = 5;
	protected int I_END_PLATFORM = 7;
	protected int I_EXIT_OFFSET = 5; // offset always smaller than end platform
	
	protected int I_HEIGHT_MARGIN = 2;
	
	protected int I_FLAT_MIN = 2;
	protected int I_GAP_MIN = 2;
	
	protected int I_JUMP_OFFSET = -5;
	protected int I_JUMP_RANGE = 10;
	
	protected int I_BLOCK_HOVER_HEIGHT = 3;
	
	public LakituBuilder(LakituLevel lvl) {
		this.lvl = lvl;
	}
	
	public void createFlatLand(int x, int width, int y) {
		lvl.addFlatLand(x, width, y);
	}
	
	public void createFlatLand(int x, int width) {
		int y = lvl.getLastGroundHeight(x);
		createFlatLand(x, width, y);
	}
	
	public void createGap(int x, int width) {
		lvl.addGap(x, width);
	}
	
	public int createStartPlug() {
		createFlatLand(0, I_START_PLATFORM, lvl.getHeight()-I_FLAT_MIN);
		return I_START_PLATFORM;
	}
	
	public void createEndPlug() {
		createFlatLand(lvl.getWidth()-I_END_PLATFORM, I_END_PLATFORM, I_FLAT_MIN);
		lvl.setExit(lvl.getWidth()-I_EXIT_OFFSET);
	}
	
	public void fixLevel() {
		createEndPlug();
		lvl.fixWalls();
	}

}
