package jorgedizpico.auto;

import java.util.Stack;

public interface State {
	
	public Chunk execute(Stack<State> stack);

}
