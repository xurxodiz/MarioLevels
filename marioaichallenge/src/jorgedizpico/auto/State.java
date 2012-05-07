package jorgedizpico.auto;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import jorgedizpico.Builder;

public enum State {
	
	INITIAL(0) {
		@Override public int genesis(Builder lkb) { 
            return 1; 
       }
	},
    START(1) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createStartPlug(); 
       }
	},
    FLAT(2) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createFlatLand(); 
       }
	},
    GAP(3) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createGap(); 
       }
	},
    PIPE(4) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createPipe(); 
       }
	},
    ENEMIES(5) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createEnemies(); 
       }
	},
    BLOCKS(6) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createBlocks(); 
       }
	},
    COINS(6) {
		@Override public int genesis(Builder lkb) { 
            return lkb.createCoins(); 
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