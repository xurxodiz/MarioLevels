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
	
	public boolean createGround(int... direction) {
		int y = lvl.getLastGroundHeight(x);
		if (GROUND_UP == direction[0])
			y += I_GROUND;
		else // GROUND_DOWN
			y -= I_GROUND;
		
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
		
		lvl.placePipe(x, y, I_PIPE_HEIGHT);
		
		if (piranha[0] == PIRANHA)
			lvl.placePiranha(x, y);
		
		return updateX(xx);
	}
	
	public boolean createCannon(int... flags) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		lvl.placeCannon(x, y, I_CANNON_HEIGHT);
		return updateX(xx);
	}
	
	public boolean createEnemy(int... enemyType) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);	
		
		int[] rnd = maySwap(new int[]{enemyType[0], -1});

		for (int i = 0; x+i < xx; i++)
			switch (rnd[i]){
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
		
		int[] rnd = maySwap(blocks);
		
		for (int i = 0; x+i < xx; i++)
			switch (rnd[i]) {
				case BLOCK_POWERUP:
					lvl.placeBlockPowerUp(x+i, y-I_HOVER_BLOCKS);
					break;
				case BLOCK_EMPTY:
					lvl.placeBlockEmpty(x+i, y-I_HOVER_BLOCKS);
					break;
				case BLOCK_COIN:
					lvl.placeBlockCoin(x+i, y-I_HOVER_BLOCKS);
					break;
			}

		return updateX(xx);
	}
	
	public boolean createEnemyBlock(int... elems) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		int[] rndEnemy = maySwap(new int[]{elems[0], -1});
		int[] rndBlock = maySwap(new int[]{elems[1], elems[2]});

		for (int i = 0; x+i < xx; i++) {
			switch (rndEnemy[i]){
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
			
			switch (rndBlock[i]) {
				case BLOCK_POWERUP:
					lvl.placeBlockPowerUp(x+i, y-I_HOVER_BLOCKS);
					break;
				case BLOCK_EMPTY:
					lvl.placeBlockEmpty(x+i, y-I_HOVER_BLOCKS);
					break;
				case BLOCK_COIN:
					lvl.placeBlockCoin(x+i, y-I_HOVER_BLOCKS);
					break;
			}
			
		}
		
		return updateX(xx);
	}
	
	public boolean createBlockEnemy(int... elems) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		int[] rndEnemy = maySwap(new int[]{elems[2], -1});
		int[] rndBlock = maySwap(new int[]{elems[0], elems[1]});

		for (int i = 0; x+i < xx; i++) {
			
			switch (rndBlock[i]) {
				case BLOCK_POWERUP:
					lvl.placeBlockPowerUp(x+i, y-I_HOVER_BLOCKS);
					break;
				case BLOCK_EMPTY:
					lvl.placeBlockEmpty(x+i, y-I_HOVER_BLOCKS);
					break;
				case BLOCK_COIN:
					lvl.placeBlockCoin(x+i, y-I_HOVER_BLOCKS);
					break;
			}
			
			switch (rndEnemy[i]){
				case ENEMY_GOOMBA:
					lvl.placeGoomba(x+i, y-I_HOVER_BLOCKS-1); 		
					break;
					
				case ENEMY_REDTURTLE:
					lvl.placeRedTurtle(x+i, y-I_HOVER_BLOCKS-1); 	
					break;
					
				case ENEMY_GREENTURTLE:
					lvl.placeGreenTurtle(x+i, y-I_HOVER_BLOCKS-1); 
					break;
					
				case ENEMY_SPIKY:
					lvl.placeSpiky(x+i, y-I_HOVER_BLOCKS-1);		
					break;
					
				case ENEMY_GOOMBA_WINGED:
					lvl.placeGoombaWinged(x+i, y-I_HOVER_BLOCKS-1); 		
					break;
					
				case ENEMY_REDTURTLE_WINGED:
					lvl.placeRedTurtleWinged(x+i, y-I_HOVER_BLOCKS-1); 	
					break;
					
				case ENEMY_GREENTURTLE_WINGED:
					lvl.placeGreenTurtleWinged(x+i, y-I_HOVER_BLOCKS-1); 
					break;
					
				case ENEMY_SPIKY_WINGED:
					lvl.placeSpikyWinged(x+i, y-I_HOVER_BLOCKS-1);		
					break;
			};
			
		}
		
		return updateX(xx);
	}
	
	public boolean createCoins(int... flags) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		for (int i = 0; x+i < xx; i++)
			lvl.placeCoin(x+i, y-I_HOVER_COINS);
		
		return updateX(xx);
	}
	
	public boolean createStairs(int... direction) {
		int y = lvl.getLastGroundHeight(x);
		int xx = lvl.addFlatLand(x, I_LEN, y);
		
		if (STAIRS_UP == direction[0])
			for (int i = 0; x+i < xx; i++)
				for (int h = 1; h < xx-x+i; h++)
					lvl.placeRock(x+i, y-h);
		
		else if (STAIRS_DOWN == direction[0])
			for (int i = 0; x+i < xx; i++)
				for (int h = xx-x-i; h > 0; h--)
					lvl.placeRock(x+i, y-h);
		
		else if (STAIRS_UPUP == direction[0])
			for (int i = 0; x+i < xx; i++)
				for (int h = 1; h < xx-x+i-I_LEN; h++)
					lvl.placeRock(x+i, y-h);
		
		else if (STAIRS_DOWNDOWN == direction[0])
			for (int i = 0; x+i < xx; i++)
				for (int h = xx-x-i+I_LEN; h > 0; h--)
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
		lvl.fixWalls();
	}

}
