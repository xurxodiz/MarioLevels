package jorgedizpico;

import dk.itu.mario.engine.sprites.Enemy;
import dk.itu.mario.engine.sprites.SpriteTemplate;

public class Builder {
	
	public LakituLevel lvl;
	
	protected int I_START_PLATFORM = 5;
	protected int I_END_PLATFORM = 7;
	protected int I_EXIT_OFFSET = 5; // offset always smaller than end platform
	
	protected int I_HEIGHT_MARGIN = 2;
	
	protected int I_FLAT_MIN = 4;
	protected int I_HEIGHT_MIN = 2;
	protected int I_GAP_MIN = 2;
	
	protected int I_JUMP_OFFSET = -5;
	protected int I_JUMP_RANGE = 10;
	
	protected int I_BLOCK_HOVER_HEIGHT = 3;
	protected int I_PIPE_HEIGHT = 2;
	
	protected int x = 0;
	
	public Builder(LakituLevel lvl) {
		this.lvl = lvl;
		this.x = 0;
	}
	
	public int getLevelWidth() {
		return lvl.getWidth();
	}
	
	public int createFlatLand() {
		int y = lvl.getLastGroundHeight(x);
		x += lvl.addFlatLand(x, I_FLAT_MIN, y);
		return x;
	}
	
	public int createGap() {
		x += lvl.addGap(x, I_GAP_MIN);
		return x;
	}
	
	public int createPipe() {
		int y = lvl.getLastGroundHeight(x);
		lvl.addFlatLand(x, 2, y);
		lvl.placePipe(x, y, I_PIPE_HEIGHT);
		return ++x;
	}
	
	public int createEnemies() {
		boolean winged = false;
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  I_FLAT_MIN, y);
		int type = Enemy.ENEMY_GOOMBA;
		
		for (int i = 0; i< w; i++)
			lvl.setSpriteTemplate(x+i, y-1, new SpriteTemplate(type, winged));
		x += w;
		return x;
	}
	
	public int createBlocks() {
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  I_FLAT_MIN, y);
		
		for (int i = 0; i< w; i++)
			switch (i%3) {
				case 0:
					lvl.placeBlockPowerUp(x+i, y-I_BLOCK_HOVER_HEIGHT);
					break;
				case 1:
					lvl.placeBlockCoin(x+i, y-I_BLOCK_HOVER_HEIGHT);
					break;
				default:
					lvl.placeBlockEmpty(x+i, y-I_BLOCK_HOVER_HEIGHT);
					break;
			}
		x += w;
		return x;
	}
	
	public int createCoins() {
		int y = lvl.getLastGroundHeight(x);
		int w = lvl.addFlatLand(x,  I_FLAT_MIN, y);
		
		for (int i = 0; i < w; i++)
			lvl.placeCoin(x+i, y-I_BLOCK_HOVER_HEIGHT);
		
		x += w;
		return x;
	}
	
	public int createStartPlug() {
		x += lvl.addFlatLand(x, I_START_PLATFORM, lvl.getHeight()-I_HEIGHT_MIN);
		return x;
	}
	
	public int createEndPlug() {
		int w = lvl.addFlatLand(lvl.getWidth()-I_END_PLATFORM, I_END_PLATFORM, lvl.getHeight()-I_HEIGHT_MIN);
		lvl.setExit(lvl.getWidth()-I_EXIT_OFFSET);
		return w;
	}
	
	public void fixLevel() {
		createEndPlug();
		lvl.fixWalls();
	}

}
