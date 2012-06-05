package jorgedizpico.auto;

import java.lang.reflect.Method;
import java.util.Stack;

import jorgedizpico.level.Builder;
import static jorgedizpico.level.BuilderFlags.*;

public enum Gene implements State {

	FLAT("createFlatLand"),

	COINS("createCoins"), // two coins

	PIPE 		("createPipe", NOPIRANHA),
	PIPEPIRANHA	("createPipe", PIRANHA),

	CANNON("createCannon"),

	GAP("createGap"),

	STAIRSUP		("createStairs", STAIRS_UP),
	STAIRSDOWN		("createStairs", STAIRS_DOWN),

	BLOCKPP		("createBlock", BLOCK_POWERUP, 	BLOCK_POWERUP),
	BLOCKCC		("createBlock", BLOCK_COIN, 	BLOCK_COIN),
	BLOCKEE		("createBlock", BLOCK_EMPTY, 	BLOCK_EMPTY),
	
	BLOCKPC		("createBlock", BLOCK_POWERUP, 	BLOCK_COIN),
	BLOCKPE		("createBlock", BLOCK_POWERUP, 	BLOCK_EMPTY),
	BLOCKCE		("createBlock", BLOCK_COIN, 	BLOCK_EMPTY),

	GOOMBA		("createEnemy", 	ENEMY_GOOMBA),
	REDTURTLE	("createEnemy", 	ENEMY_REDTURTLE),
	GREENTURTLE	("createEnemy",		ENEMY_GREENTURTLE),
	SPIKY		("createEnemy", 	ENEMY_SPIKY),
	
	GOOMBAWINGED		("createEnemy", 	ENEMY_GOOMBA_WINGED),
	REDTURTLEWINGED		("createEnemy", 	ENEMY_REDTURTLE_WINGED),
	GREENTURTLEWINGED	("createEnemy",		ENEMY_GREENTURTLE_WINGED),
	SPIKYWINGED			("createEnemy", 	ENEMY_SPIKY_WINGED),

	;

	private String createMethod;

	private int[] parameters;

	private Gene(String s, int... p) {
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
	public Gene execute(Stack<State> stack) {
		return this;
	}

}