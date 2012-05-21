package jorgedizpico.auto;

import java.lang.reflect.Method;
import java.util.Stack;

import jorgedizpico.level.Builder;

public enum Gene implements State {
	
    FLAT ("createFlatLand", null),
    
    COINS("createCoins", null), // two coins

    PIPE ("createPipe", null),
	PIPEPIRANHA("createPipePiranha", null),
	
	CANNON("createCannon", null),
    
    GAP ("createGap", null),
    
    STAIRSUP ("createStairsUp", null),
    STAIRSDOWN ("createStairsDown", null),

    BLOCKPOWERUP ("createBlockPowerUp", null), // two blocks
    BLOCKCOINS ("createBlockCoins", null),     //     "
    BLOCKEMPTY ("createBlockEmpty", null),     //     "
    
    GOOMBA ("createGoomba", null),
    REDTURTLE ("createRedTurtle", null),
    GREENTURTLE ("createGreenTurtle", null),
    SPIKY ("createSpiky", null),
    
    ;
	
	private String createMethod;
	
	@SuppressWarnings("rawtypes")
	private Class[] parTypes;
	

	@SuppressWarnings("rawtypes")
	private Gene(String s, Class[] p) {
		this.createMethod = s;
		this.parTypes = p;
	}
	
	public boolean genesis(Builder lkb) {
		try {
			
			Class<Builder> cl = Builder.class;
			Method meth = cl.getMethod(this.createMethod, parTypes);
			boolean b = (Boolean) meth.invoke(lkb, (Object[]) this.parTypes);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean execute(Stack<State> stack, Trace trace) {
		return trace.addGene(this);
	}


}