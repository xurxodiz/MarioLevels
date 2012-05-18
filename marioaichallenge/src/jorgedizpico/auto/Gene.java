package jorgedizpico.auto;

import java.lang.reflect.Method;

import jorgedizpico.Builder;

public enum Gene implements State {
	
    FLAT ("createFlatLand"), // flat empty ground
    
    GAPSTD("createGap"), // regular gap
    GAPSTAIRS("createGapStairs"), // gap surrounded by stairs

    PIPE("createPipe"), // pipe
	PIPEPIRANHA("createPiranha"), // pipe with piranha

    COINS("createCoins"), // one coin
    BLOCKS("createBlocks"), // one block
    ENEMIES("createEnemies"), // one enemy

	CANNON("createCannon"), // a cannon
    ;
	
	private String createMethod;
	
	private Gene(String s) {
		this.createMethod = s;
	}
	
	public boolean genesis(Builder lkb) {
		try {
			
			Class<Builder> cl = Builder.class;
			@SuppressWarnings("rawtypes")
			Class parTypes[] = {Builder.class};
			Method meth = cl.getMethod(this.createMethod, parTypes);
			return (Boolean) meth.invoke(lkb);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean execute(Automaton auto) {
		return auto.addGene(this);
	}


}