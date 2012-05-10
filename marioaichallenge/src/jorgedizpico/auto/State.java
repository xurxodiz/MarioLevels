package jorgedizpico.auto;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import jorgedizpico.Builder;

public enum State {
	
	/*
	 * automaton initial state
	 */
	INITIAL(1) {
		@Override public int genesis(Builder lkb) { 
            return lkb.currentX(); 
       }
	},
	/*
	 * builds staring platform
	 */
    START(10) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createStartPlug(); 
       }
	},
	/*
	 * from here you go to the different chunks
	 * you also return here after
	 */
	HUB(2) {
		@Override public int genesis(Builder lkb) { 
            return lkb.currentX(); 
       }
	},
	/*
	 * just flat empty ground
	 */
    FLAT(3) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createFlatLand(); 
       }
	},
	/*
	 * gaps to jump over
	 */
    GAP(4) {
		@Override public int genesis(Builder lkb) { 
            return lkb.currentX(); 
       }
	},
    GAPSTD(41) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createGap(); 
       }
	},
    GAPSTAIRS(42) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createGapStairs(); 
       }
	},
    GAPOUT(43) {
		@Override public int genesis(Builder lkb) { 
            return lkb.currentX(); 
       }
	},
	/*
	 * pipe, randomly (not used for any purpose)
	 */
    PIPE(5) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createPipe(); 
       }
	},
	// it might have a piranha though
	PIPEPIRANHA(51) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createPiranha(); 
       }
	},
	PIPEOUT(52) {
		@Override public int genesis(Builder lkb) { 
            return lkb.currentX(); 
       }
	},
	/*
	 * a strip of coins in an otherwise empty flatland
	 */
    COINS(6) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createCoins(); 
       }
	},
	/*
	 * a strip of blocks in an otherwise empty flatland
	 */
    BLOCKS(7) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createBlocks(); 
       }
	},
	/*
	 * any combination of enemies with some other thing
	 */
    ENEMIES(8) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createEnemies(); 
       }
	},
	/*
	 * cannons are quite an adversary so they go separate
	 */
	CANNON(9) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createCannon(); 
       }
	},
    ;
	
	// template method
	public abstract int genesis(Builder lkb);
	
	// from here on down is boilerplate
	// to get and set the unique codes

    private static final Map<Integer,State> lookup = new HashMap<Integer,State>();
    
    private int code;

    static {
    	for(State s : EnumSet.allOf(State.class))
    		lookup.put(s.getCode(), s);
    }

	private State(int code) {
		this.code = code;
	}
	
	public int getCode() { return code; }
	
	public static State get(int code) { 
		return lookup.get(code); 
	}


}