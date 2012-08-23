package jorgedizpico.auto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Stack;

public class Automaton implements FSM, Serializable {
	
	private static final long serialVersionUID = 777L;
	
	protected HashMap<String,Dummy> dummies
		= new HashMap<String,Dummy>();
	
	protected Stack<State> stack;
	
	public void init() {
		stack = new Stack<State>();
		stack.push(getDummy("initial"));
	}

	public Dummy getDummy(String s) {
		Dummy dummy = dummies.get(s);
		if (null != dummy) return dummy;
		dummy = new Dummy(s);
		dummies.put(s, dummy);
		return dummy;
	}
	
	public void validate() {
		// ponder the odds between the total odds
		for (Dummy dummy : dummies.values()) {
			double accum = 0.0;
			for (Chain ch : dummy)
				accum += ch.getOdds();
			for (Chain ch : dummy)
				ch.setOdds(ch.getOdds()/accum);
		}
	}
	
	public Chunk step() {
		State st = stack.pop();
		return st.execute(stack);
	}
}
