package jorgedizpico;

public class Builder {
	
	public LakituLevel lvl;
	
	protected int I_START_PLATFORM = 10;
	protected int I_END_PLATFORM = 10;
	protected int I_EXIT_OFFSET = 5; // offset always smaller than end platform
	
	protected int I_HEIGHT_MARGIN = 2;
	protected int I_HEIGHT_MIN = 2;
	
	protected int I_LEN = 2;
	
	protected int I_JUMP_OFFSET = -5;
	protected int I_JUMP_RANGE = 10;
	
	protected int I_BLOCK_HOVER_HEIGHT = 4;
	protected int I_PIPE_HEIGHT = 2;
	protected int I_CANNON_HEIGHT = 2;
	
	protected int x = 0;
	
	public Builder(LakituLevel lvl) {
		this.lvl = lvl;
		this.x = 0;
	}
	
	private boolean updateX(int nx) {
		return updateX(nx, I_LEN);
	}
	
	private boolean updateX(int nx, int diff) {
		int oldx = x;
		x = nx;
		return (x-oldx) == diff;
	}
	
	public int getLevelWidth() {
		return lvl.getWidth();
	}
	
	public int getLevelWidthSoFar() {
		return x;
	}
	
	public boolean createFlatLand() {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		return updateX(xx);
	}
	
	public boolean createGap() {
		int xx = lvl.addGap(x, I_LEN);
		return updateX(xx);
	}
	
	public boolean createGapStairs() {
		return (createStairsUp() && createGap() && createStairsDown());
	}
	
	public boolean createPipe() {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		if (xx-x != I_LEN) return false;
		
		lvl.placePipe(xx, y, I_PIPE_HEIGHT);
		return updateX(xx);
	}
	
	public boolean createPipePiranha() {
		if (!createPipe()) return false;
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.placePiranha(x, y);
		return (xx == x);
	}
	
	public boolean createEnemies() {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		for (int i = x; i < xx; i++)
			if (i % 2 == 0)
				lvl.placeGoomba(i, y-1);
		return updateX(xx);
	}
	
	public boolean createCannon() {
		if (!createFlatLand()) return false;
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.placeCannon(x-1, y, I_CANNON_HEIGHT);
		return (xx == x);
	}
	
	public boolean createBlocks() {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		for (int i = x; i < xx; i++)
			switch (x%3) {
				case 0:
					lvl.placeBlockPowerUp(i, y-I_BLOCK_HOVER_HEIGHT);
					break;
				case 1:
					lvl.placeBlockCoin(i, y-I_BLOCK_HOVER_HEIGHT);
					break;
				default:
					lvl.placeBlockEmpty(i, y-I_BLOCK_HOVER_HEIGHT);
					break;
			}
		return updateX(xx);
	}
	
	public boolean createCoins() {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		for (int i = x; i < xx; i++)
			lvl.placeCoin(i, y-I_BLOCK_HOVER_HEIGHT);
		
		return updateX(xx);
	}
	
	public boolean createStairsUp() {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x,  I_LEN, y);
		
		for (int i = 0; i < xx-x; i++)
			for (int h = 1; h <= i+1; h++)
				lvl.placeRock(x+i, y-h);
		
		return updateX(xx);
	}
	
	public boolean createStairsDown() {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x,  I_LEN, y);
		
		for (int i = 0; i < xx-x; i++)
			for (int h = 1; h <= xx-x+i; h++)
				lvl.placeRock(x+i, y-h);
		
		return updateX(xx);
	}
	
	public boolean createStartPlug() {
		int xx = lvl.addFlatLand(x, I_START_PLATFORM, lvl.getHeight()-I_HEIGHT_MIN);
		return updateX(xx, I_START_PLATFORM);
	}
	
	public boolean createEndPlug() {
		int xx = lvl.addFlatLand(x, I_END_PLATFORM, lvl.getHeight()-I_HEIGHT_MIN);
		lvl.cleanSprites(x, xx);
		lvl.setExit(xx-I_EXIT_OFFSET);
		return updateX(xx, I_END_PLATFORM);
	}
	
	public void fixLevel() {
		createEndPlug();
		lvl.fixWalls();
	}

}
