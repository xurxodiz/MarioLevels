package jorgedizpico.auto;

import jorgedizpico.level.Chunk;

public interface FSM {
	
	public void init();
	
	public Chunk step();

}
