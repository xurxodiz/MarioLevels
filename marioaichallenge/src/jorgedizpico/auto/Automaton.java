package jorgedizpico.auto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Stack;

public class Automaton implements Serializable {
	
	private static final long serialVersionUID = 777L;
	
	protected HashMap<String,Dummy> dummies
		= new HashMap<String,Dummy>();
	
	protected Stack<State> stack;

	public Gene getGene(String s) {
		return Enum.valueOf(Gene.class, s);
	}
	
	public void init() {
		stack = new Stack<State>();
	}

	public Dummy getDummy(String s) {
		Dummy dummy = dummies.get(s);
		if (null != dummy) return dummy;
		dummy = new Dummy();
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
	
	public Gene step() {
		State st = stack.pop();
		return st.execute(stack);
	}
}
