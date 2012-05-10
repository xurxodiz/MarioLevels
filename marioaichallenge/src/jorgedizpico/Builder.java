package jorgedizpico;

public class Builder {
	
	public LakituLevel lvl;
	
	protected int I_START_PLATFORM = 5;
	protected int I_END_PLATFORM = 7;
	protected int I_EXIT_OFFSET = 5; // offset always smaller than end platform
	
	protected int I_HEIGHT_MARGIN = 2;
	protected int I_HEIGHT_MIN = 2;
	
	protected int I_LEN_FLAT = 2;
	protected int I_LEN_BLOCKS = 4;
	protected int I_LEN_ENEMIES = 4;
	protected int I_LEN_COINS = 4;
	protected int I_LEN_GAP = 2;
	protected int I_LEN_STAIRS = 4;
	
	protected int I_JUMP_OFFSET = -5;
	protected int I_JUMP_RANGE = 10;
	
	protected int I_BLOCK_HOVER_HEIGHT = 4;
	protected int I_PIPE_HEIGHT = 2;
	protected int I_CANNON_HEIGHT = 1;
	
	protected int x = 0;
	
	public Builder(LakituLevel lvl) {
		this.lvl = lvl;
		this.x = 0;
	}
	
	public int currentX() {
		return x;
	}
	
	public int getLevelWidth() {
		return lvl.getWidth();
	}
	
	public int createFlatLand() {
		int y = lvl.getLastGroundHeight(x);
		x += lvl.addFlatLand(x, I_LEN_FLAT, y);
		return x;
	}
	
	public int createGap() {
		x += lvl.addGap(x, I_LEN_GAP);
		return x;
	}
	
	public int createGapStairs() {
		createStairsUp();
		createGap();
		createStairsDown();
		return currentX();
	}
	
	public int createPipe() {
		int y = lvl.getLastGroundHeight(x);
		lvl.addFlatLand(x, 2, y);
		x = lvl.placePipe(x, y, I_PIPE_HEIGHT);
		return ++x;
	}
	
	public int createPiranha() {
		int y = lvl.getLastGroundHeight(x);
		lvl.placePiranha(x-2, y);
		return x;
	}
	
	public int createEnemies() {
		//boolean winged = false;
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  1, y);
		//int type = Enemy.ENEMY_GOOMBA;
		
		//for (int i = 0; i< w; i++)
			if (x % 2 == 0)
				lvl.placeGoomba(x, y-1);
		x += w;
		return x;
	}
	
	public int createCannon() {
		createFlatLand();
		int y = lvl.getLastGroundHeight(x);
		lvl.placeCannon(x-1, y, I_CANNON_HEIGHT);
		return currentX();
	}
	
	public int createBlocks() {
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  1, y);
		
		//for (int i = 0; i< w; i++)
			switch (x%3) {
				case 0:
					lvl.placeBlockPowerUp(x, y-I_BLOCK_HOVER_HEIGHT);
					break;
				case 1:
					lvl.placeBlockCoin(x, y-I_BLOCK_HOVER_HEIGHT);
					break;
				default:
					lvl.placeBlockEmpty(x, y-I_BLOCK_HOVER_HEIGHT);
					break;
			}
		x += w;
		return x;
	}
	
	public int createCoins() {
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  1, y);
		
		//for (int i = 0; i < w; i++)
			lvl.placeCoin(x, y-I_BLOCK_HOVER_HEIGHT);
		
		x += w;
		return x;
	}
	
	public int createStairsUp() {
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  I_LEN_STAIRS, y);
		
		for (int i = 0; i < w; i++)
			for (int h = 1; h <= i+1; h++)
				lvl.placeRock(x+i, y-h);
		
		x += w;
		return x;
	}
	
	public int createStairsDown() {
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  I_LEN_STAIRS, y);
		
		for (int i = 0; i < w; i++)
			for (int h = 1; h <= w-i; h++)
				lvl.placeRock(x+i, y-h);
		
		x += w;
		return x;
	}
	
	public int createStartPlug() {
		x += lvl.addFlatLand(x, I_START_PLATFORM, lvl.getHeight()-I_HEIGHT_MIN);
		return x;
	}
	
	public int createEndPlug() {
		int w = lvl.addFlatLand(lvl.getWidth()-I_END_PLATFORM, I_END_PLATFORM, lvl.getHeight()-I_HEIGHT_MIN);
		lvl.cleanSprites(lvl.getWidth()-I_EXIT_OFFSET, lvl.getWidth());
		lvl.setExit(lvl.getWidth()-I_EXIT_OFFSET);
		return w;
	}
	
	public void fixLevel() {
		createEndPlug();
		lvl.fixWalls();
	}

}
