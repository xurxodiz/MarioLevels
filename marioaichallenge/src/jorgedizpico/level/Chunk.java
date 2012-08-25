package jorgedizpico.level;

import java.lang.reflect.Method;
import java.util.Stack;

import jorgedizpico.auto.State;
import static jorgedizpico.level.BuilderFlags.*;

public enum Chunk implements State {

	FLAT	("createFlatLand"),

	COINS	("createCoins"), // two coins

	PIPE 			("createPipe", NOPIRANHA),
	PIPE_PIRANHA	("createPipe", PIRANHA),

	CANNON	("createCannon"),

	GAP	("createGap"),
	
	// BuilderFlags.Foo is used where the flag
	// has the same name as the Chunk
	
	GROUND_UP	("createGround", BuilderFlags.GROUND_UP),
	GROUND_DOWN	("createGround", BuilderFlags.GROUND_DOWN),

	STAIRS_UP		("createStairs", BuilderFlags.STAIRS_UP),
	STAIRS_UPUP		("createStairs", BuilderFlags.STAIRS_UPUP),
	STAIRS_DOWN		("createStairs", BuilderFlags.STAIRS_DOWN),
	STAIRS_DOWNDOWN	("createStairs", BuilderFlags.STAIRS_DOWNDOWN),

	BLOCK_PP		("createBlock", BLOCK_POWERUP, 	BLOCK_POWERUP),
	BLOCK_CC		("createBlock", BLOCK_COIN, 	BLOCK_COIN),
	BLOCK_EE		("createBlock", BLOCK_EMPTY, 	BLOCK_EMPTY),
	
	BLOCK_PC		("createBlock", BLOCK_POWERUP, 	BLOCK_COIN),
	BLOCK_PE		("createBlock", BLOCK_POWERUP, 	BLOCK_EMPTY),
	BLOCK_CE		("createBlock", BLOCK_COIN, 	BLOCK_EMPTY),

	GOOMBA		("createEnemy", 	ENEMY_GOOMBA),
	REDTURTLE	("createEnemy", 	ENEMY_GOOMBA),
	GREENTURTLE	("createEnemy",		ENEMY_GREENTURTLE),
	SPIKY		("createEnemy", 	ENEMY_SPIKY),
	
	GOOMBA_WINGED			("createEnemy", 	ENEMY_GOOMBA_WINGED),
	REDTURTLE_WINGED		("createEnemy", 	ENEMY_GOOMBA_WINGED),
	GREENTURTLE_WINGED		("createEnemy",		ENEMY_GREENTURTLE_WINGED),
	SPIKY_WINGED			("createEnemy", 	ENEMY_SPIKY_WINGED),

	GOOMBA_BLOCK_PP	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_POWERUP, 	BLOCK_POWERUP),
	GOOMBA_BLOCK_CC	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_COIN, 		BLOCK_COIN),
	GOOMBA_BLOCK_EE	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_EMPTY, 	BLOCK_EMPTY),
	GOOMBA_BLOCK_PC	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_POWERUP, 	BLOCK_COIN),
	GOOMBA_BLOCK_PE	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_POWERUP, 	BLOCK_EMPTY),
	GOOMBA_BLOCK_CE	("createEnemyBlock", ENEMY_GOOMBA, BLOCK_COIN, 		BLOCK_EMPTY),
	
	GREENTURTLE_BLOCK_PP	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_POWERUP, 	BLOCK_POWERUP),
	GREENTURTLE_BLOCK_CC	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_COIN, 	BLOCK_COIN),
	GREENTURTLE_BLOCK_EE	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_EMPTY, 	BLOCK_EMPTY),
	GREENTURTLE_BLOCK_PC	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_POWERUP, 	BLOCK_COIN),
	GREENTURTLE_BLOCK_PE	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_POWERUP, 	BLOCK_EMPTY),
	GREENTURTLE_BLOCK_CE	("createEnemyBlock", ENEMY_GREENTURTLE, BLOCK_COIN, 	BLOCK_EMPTY),
	
	REDTURTLE_BLOCK_PP	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_POWERUP, 	BLOCK_POWERUP),
	REDTURTLE_BLOCK_CC	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_COIN, 		BLOCK_COIN),
	REDTURTLE_BLOCK_EE	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_EMPTY, 		BLOCK_EMPTY),
	REDTURTLE_BLOCK_PC	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_POWERUP, 	BLOCK_COIN),
	REDTURTLE_BLOCK_PE	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_POWERUP, 	BLOCK_EMPTY),
	REDTURTLE_BLOCK_CE	("createEnemyBlock", ENEMY_REDTURTLE, BLOCK_COIN, 		BLOCK_EMPTY),
	
	BLOCK_PP_GOOMBA	("createBlockEnemy", BLOCK_POWERUP, BLOCK_POWERUP, 	ENEMY_GOOMBA),
	BLOCK_CC_GOOMBA	("createBlockEnemy", BLOCK_COIN, 	BLOCK_COIN, 	ENEMY_GOOMBA),
	BLOCK_EE_GOOMBA	("createBlockEnemy", BLOCK_EMPTY, 	BLOCK_EMPTY, 	ENEMY_GOOMBA),
	BLOCK_PC_GOOMBA	("createBlockEnemy", BLOCK_POWERUP, BLOCK_COIN, 	ENEMY_GOOMBA),
	BLOCK_PE_GOOMBA	("createBlockEnemy", BLOCK_POWERUP, BLOCK_EMPTY, 	ENEMY_GOOMBA),
	BLOCK_CE_GOOMBA	("createBlockEnemy", BLOCK_COIN, 	BLOCK_EMPTY, 	ENEMY_GOOMBA),
	
	BLOCK_PP_GREENTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_POWERUP, 	ENEMY_GREENTURTLE),
	BLOCK_CC_GREENTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_COIN, 	ENEMY_GREENTURTLE),
	BLOCK_EE_GREENTURTLE	("createBlockEnemy", BLOCK_EMPTY, 	BLOCK_EMPTY, 	ENEMY_GREENTURTLE),
	BLOCK_PC_GREENTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_COIN, 	ENEMY_GREENTURTLE),
	BLOCK_PE_GREENTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_EMPTY, 	ENEMY_GREENTURTLE),
	BLOCK_CE_GREENTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_EMPTY, 	ENEMY_GREENTURTLE),
	
	BLOCK_PP_REDTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_POWERUP, 	ENEMY_REDTURTLE),
	BLOCK_CC_REDTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_COIN, 	ENEMY_REDTURTLE),
	BLOCK_EE_REDTURTLE	("createBlockEnemy", BLOCK_EMPTY, 	BLOCK_EMPTY, 	ENEMY_REDTURTLE),
	BLOCK_PC_REDTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_COIN, 	ENEMY_REDTURTLE),
	BLOCK_PE_REDTURTLE	("createBlockEnemy", BLOCK_POWERUP, BLOCK_EMPTY, 	ENEMY_REDTURTLE),
	BLOCK_CE_REDTURTLE	("createBlockEnemy", BLOCK_COIN, 	BLOCK_EMPTY, 	ENEMY_REDTURTLE),
	
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