package jorgedizpico.auto;

import java.lang.reflect.Method;
import java.util.Stack;

import jorgedizpico.level.Builder;
import static jorgedizpico.level.BuilderFlags.*;

public enum Chunk implements State {

	FLAT	("createFlatLand"),

	COINS	("createCoins"), // two coins

	PIPE 		("createPipe", NOPIRANHA),
	PIPEPIRANHA	("createPipe", PIRANHA),

	CANNON	("createCannon"),

	GAP	("createGap"),
	
	GROUNDUP	("createGround", GROUND_UP),
	GROUNDDOWN	("createGround", GROUND_DOWN),

	STAIRSUP		("createStairs", STAIRS_UP),
	STAIRSUPUP		("createStairs", STAIRS_UPUP),
	STAIRSDOWN		("createStairs", STAIRS_DOWN),
	STAIRSDOWNDOWN	("createStairs", STAIRS_DOWNDOWN),

	BLOCKPP		("createBlock", BLOCK_POWERUP, 	BLOCK_POWERUP),
	BLOCKCC		("createBlock", BLOCK_COIN, 	BLOCK_COIN),
	BLOCKEE		("createBlock", BLOCK_EMPTY, 	BLOCK_EMPTY),
	
	BLOCKPC		("createBlock", BLOCK_POWERUP, 	BLOCK_COIN),
	BLOCKPE		("createBlock", BLOCK_POWERUP, 	BLOCK_EMPTY),
	BLOCKCE		("createBlock", BLOCK_COIN, 	BLOCK_EMPTY),

	GOOMBA		("createEnemy", 	ENEMY_GOOMBA),
	REDTURTLE	("createEnemy", 	ENEMY_GOOMBA),
	GREENTURTLE	("createEnemy",		ENEMY_GREENTURTLE),
	SPIKY		("createEnemy", 	ENEMY_SPIKY),
	
	GOOMBAWINGED		("createEnemy", 	ENEMY_GOOMBA_WINGED),
	REDTURTLEWINGED		("createEnemy", 	ENEMY_GOOMBA_WINGED),
	GREENTURTLEWINGED	("createEnemy",		ENEMY_GREENTURTLE_WINGED),
	SPIKYWINGED			("createEnemy", 	ENEMY_SPIKY_WINGED),

	GOOMBABLOCKPP	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_POWERUP, 	BLOCK_POWERUP),
	GOOMBABLOCKCC	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_COIN, 		BLOCK_COIN),
	GOOMBABLOCKEE	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_EMPTY, 	BLOCK_EMPTY),
	GOOMBABLOCKPC	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_POWERUP, 	BLOCK_COIN),
	GOOMBABLOCKPE	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_POWERUP, 	BLOCK_EMPTY),
	GOOMBABLOCKCE	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_COIN, 		BLOCK_EMPTY),
	
	GREENTURTLEBLOCKPP	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_POWERUP, 	BLOCK_POWERUP),
	GREENTURTLEBLOCKCC	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_COIN, 	BLOCK_COIN),
	GREENTURTLEBLOCKEE	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_EMPTY, 	BLOCK_EMPTY),
	GREENTURTLEBLOCKPC	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_POWERUP, 	BLOCK_COIN),
	GREENTURTLEBLOCKPE	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_POWERUP, 	BLOCK_EMPTY),
	GREENTURTLEBLOCKCE	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_COIN, 	BLOCK_EMPTY),
	
	REDTURTLEBLOCKPP	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_POWERUP, 	BLOCK_POWERUP),
	REDTURTLEBLOCKCC	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_COIN, 		BLOCK_COIN),
	REDTURTLEBLOCKEE	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_EMPTY, 		BLOCK_EMPTY),
	REDTURTLEBLOCKPC	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_POWERUP, 	BLOCK_COIN),
	REDTURTLEBLOCKPE	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_POWERUP, 	BLOCK_EMPTY),
	REDTURTLEBLOCKCE	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_COIN, 		BLOCK_EMPTY),
	
	BLOCKPPGOOMBA	("createBlockEnemy", BLOCK_POWERUP, BLOCK_POWERUP, 	ENEMY_GOOMBA),
	BLOCKCCGOOMBA	("createBlockEnemy", BLOCK_COIN, 	BLOCK_COIN, 	ENEMY_GOOMBA),
	BLOCKEEGOOMBA	("createBlockEnemy", BLOCK_EMPTY, 	BLOCK_EMPTY, 	ENEMY_GOOMBA),
	BLOCKPCGOOMBA	("createBlockEnemy", BLOCK_POWERUP, BLOCK_COIN, 	ENEMY_GOOMBA),
	BLOCKPEGOOMBA	("createBlockEnemy", BLOCK_POWERUP, BLOCK_EMPTY, 	ENEMY_GOOMBA),
	BLOCKCEGOOMBA	("createBlockEnemy", BLOCK_COIN, 	BLOCK_EMPTY, 	ENEMY_GOOMBA),
	
	BLOCKPPGREENTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_POWERUP, 	ENEMY_GREENTURTLE),
	BLOCKCCGREENTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_COIN, 	ENEMY_GREENTURTLE),
	BLOCKEEGREENTURTLE	("createBlockEnemy", BLOCK_EMPTY, 	BLOCK_EMPTY, 	ENEMY_GREENTURTLE),
	BLOCKPCGREENTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_COIN, 	ENEMY_GREENTURTLE),
	BLOCKPEGREENTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_EMPTY, 	ENEMY_GREENTURTLE),
	BLOCKCEGREENTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_EMPTY, 	ENEMY_GREENTURTLE),
	
	BLOCKPPREDTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_POWERUP, 	ENEMY_REDTURTLE),
	BLOCKCCREDTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_COIN, 	ENEMY_REDTURTLE),
	BLOCKEEREDTURTLE	("createBlockEnemy", BLOCK_EMPTY, 	BLOCK_EMPTY, 	ENEMY_REDTURTLE),
	BLOCKPCREDTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_COIN, 	ENEMY_REDTURTLE),
	BLOCKPEREDTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_EMPTY, 	ENEMY_REDTURTLE),
	BLOCKCEREDTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_EMPTY, 	ENEMY_REDTURTLE),
	
	;

	private String createMethod;

	private int[] parameters;
	
	public static Chunk getChunk(String s) {
		return Enum.valueOf(Chunk.class, s);
	}

	private Chunk(String s, int... p) {
		this.createMethod = s;
		this.parameters = p;
	}

	public boolean genesis(Builder lkb) {
		try {

			Class<Builder> cl = Builder.class;
			Method meth = cl.getMethod(this.createMethod, new Class[] { int[].class });
			boolean b = (Boolean) meth.invoke(lkb, this.parameters);
			return b;

		} catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Chunk execute(Stack<State> stack) {
		return this;
	}

}