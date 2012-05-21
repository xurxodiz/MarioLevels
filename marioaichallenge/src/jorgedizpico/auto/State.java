package jorgedizpico.auto;

import java.util.Stack;

public interface State {
	
	public boolean execute(Stack<State> stack, Trace trace);

}
