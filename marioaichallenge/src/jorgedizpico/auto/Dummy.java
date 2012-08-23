package jorgedizpico.auto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class Dummy implements State, Iterable<Chain>, Serializable {
	
	private static final long serialVersionUID = 39L;
	
	protected ArrayList<Chain> transitions = new ArrayList<Chain>();
	
	protected String name;
	
	public Dummy(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean addChain(Chain ch) {
		return transitions.add(ch);
	}
	
	@Override
	public Iterator<Chain> iterator() {
        Iterator<Chain> i = Collections.unmodifiableList(transitions).iterator();
        return i; 
	}

	@Override
	public Chunk execute(Stack<State> stack) {
		double roll = new Random().nextDouble();
		double accum = 0.0;
		
		/*
		 * ACHTUNG
		 * the chain will be derived by the leftmost nonterminal first
		 * so the chain has to be pushed to the automaton stack
		 * IN REVERSE ORDER
		 * (right tokens first)
		 * so they are popped correctly
		 */
		
		for (Chain ch : transitions) {
			accum += ch.getOdds();
			if (accum > roll) {
				for (State st : ch.flippedCopy())
					stack.push(st);
				break;
			}
		}
		return null;
	}

}
