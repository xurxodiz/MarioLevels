package jorgedizpico.auto;

import java.util.Stack;

public interface State {
	
	public Gene execute(Stack<State> stack);

}
