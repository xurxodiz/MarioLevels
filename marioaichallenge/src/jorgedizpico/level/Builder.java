package jorgedizpico.level;

import static jorgedizpico.level.BuilderFlags.*;

import java.util.Random;

public class Builder {
	
	public LakituLevel lvl;
	
	protected int x = 0;
	
	public Builder(LakituLevel lvl) {
		this.lvl = lvl;
		this.x = 0;
	}
	
	public int[] maySwap(int[] array) {
		
		if (new Random().nextBoolean()) {
			int aux = array[0];
			array[0] = array[1];
			array[1] = aux;
		}
		return array;
	}
	
	
	protected boolean updateX(int nx) {
		return updateX(nx, I_LEN);
	}
	
	protected boolean updateX(int xx, int diff) {
		int oldx = x;
		x = xx;
		return (x-oldx) == diff;
	}
	
	public int getLevelWidth() {
		return lvl.getWidth();
	}
	
	public int getLevelWidthSoFar() {
		return x;
	}
	
	public boolean createFlatLand(int... flags) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		return updateX(xx);
	}
	
	public boolean createGap(int... flags) {
		int xx = lvl.addGap(x, I_LEN);
		return updateX(xx);
	}
	
	public boolean createPipe(int... piranha) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		if (xx-x != I_LEN) return false;
		
		xx = lvl.placePipe(x+1, y, I_PIPE_HEIGHT);
		
		if (piranha[0] == PIRANHA)
			lvl.placePiranha(x+1, y);
		
		return updateX(xx);
	}
	
	public boolean createCannon(int... flags) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		lvl.placeCannon(xx-1, y, I_CANNON_HEIGHT);
		return updateX(xx);
	}
	
	public boolean createEnemy(int... enemyType) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);	
		
		int[] enemy = maySwap(new int[]{enemyType[0], -1});

		for (int i = 1; x+i <= xx; i++)
			switch (enemy[i-1]){
				case ENEMY_GOOMBA:
					lvl.placeGoomba(x+i, y-1); 		
					break;
					
				case ENEMY_REDTURTLE:
					lvl.placeRedTurtle(x+i, y-1); 	
					break;
					
				case ENEMY_GREENTURTLE:
					lvl.placeGreenTurtle(x+i, y-1); 
					break;
					
				case ENEMY_SPIKY:
					lvl.placeSpiky(x+i, y-1);		
					break;
					
				case ENEMY_GOOMBA_WINGED:
					lvl.placeGoombaWinged(x+i, y-1); 		
					break;
					
				case ENEMY_REDTURTLE_WINGED:
					lvl.placeRedTurtleWinged(x+i, y-1); 	
					break;
					
				case ENEMY_GREENTURTLE_WINGED:
					lvl.placeGreenTurtleWinged(x+i, y-1); 
					break;
					
				case ENEMY_SPIKY_WINGED:
					lvl.placeSpikyWinged(x+i, y-1);		
					break;
			};
		
		return updateX(xx);
	}
	
	public boolean createBlock(int... blocks) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		blocks = maySwap(blocks);
		
		for (int i = 1; x+i <= xx; i++)
			switch (blocks[i-1]) {
				case BLOCK_POWERUP:
					lvl.placeBlockPowerUp(x+i, y-I_HOVER_HEIGHT);
					break;
				case BLOCK_EMPTY:
					lvl.placeBlockEmpty(x+i, y-I_HOVER_HEIGHT);
					break;
				case BLOCK_COIN:
					lvl.placeBlockCoin(x+i, y-I_HOVER_HEIGHT);
					break;
			}

		return updateX(xx);
	}
	
	public boolean createCoins(int... flags) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		for (int i = x+1; i <= xx; i++)
			lvl.placeCoin(i, y-I_HOVER_HEIGHT);
		
		return updateX(xx);
	}
	
	public boolean createStairs(int... direction) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		if (STAIRS_UP == direction[0])
			for (int i = 0; i < xx-x; i++)
				for (int h = 1; h <= i+1; h++)
					lvl.placeRock(x+i, y-h);
		else // STAIRS_DOWN
			for (int i = 0; i < xx-x; i++)
				for (int h = xx-x-i; h > 0; h--)
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
