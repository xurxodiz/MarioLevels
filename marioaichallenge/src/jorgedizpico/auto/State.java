package jorgedizpico.auto;

import java.util.Stack;

import jorgedizpico.level.Chunk;

public interface State {
	
	public Chunk execute(Stack<State> stack);

}
