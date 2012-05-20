package jorgedizpico.auto;

import java.lang.reflect.Method;

import jorgedizpico.level.Builder;

public enum Gene implements State {
	
    FLAT ("createFlatLand"),
    
    COINS("createCoins"), // two coins

    PIPE ("createPipe"),
	PIPEPIRANHA("createPipePiranha"),
	
	CANNON("createCannon"),
    
    GAP ("createGap"),
    
    STAIRSUP ("createStairsUp"),
    STAIRSDOWN ("createStairsDown"),

    BLOCKPOWERUP ("createBlockPowerUp"), // two blocks
    BLOCKCOINS ("createBlockCoins"),     //     "
    BLOCKEMPTY ("createBlockEmpty"),     //     "
    
    GOOMBA ("createGoomba"),
    REDTURTLE ("createRedTurtle"),
    GREENTURTLE ("createGreenTurtle"),
    SPIKY ("createSpiky"),
    
    ;
	
	private String createMethod;
	
	private Gene(String s) {
		this.createMethod = s;
	}
	
	public boolean genesis(Builder lkb) {
		try {
			
			Class<Builder> cl = Builder.class;
			@SuppressWarnings("rawtypes")
			Class parTypes[] = {};
			Method meth = cl.getMethod(this.createMethod, parTypes);
			boolean b = (Boolean) meth.invoke(lkb, (Object[]) parTypes);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean execute(Automaton auto) {
		return auto.addGene(this);
	}


}