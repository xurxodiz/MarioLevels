package jorgedizpico.auto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import jorgedizpico.Builder;

public class Automaton {
	
	protected State state;
	protected HashMap<State, ArrayList<Transition>> transitions;

	public Automaton (int type){
		transitions = new HashMap<State, ArrayList<Transition>>();
		// load transitions from file
		// meanwhile...
		addTransitions();
	}
	
	public void execute(Builder lkb) {
		int err = 1;
		
		state = State.INITIAL;
		
		while (err < lkb.getLevelWidth()) {
			state = transition(state);
			err = state.genesis(lkb);
		}
	}


	protected State transition(State state) {
		double roll = new Random().nextDouble();
		double accum = 0.0;
				
		for (Transition t : transitions.get(state)) {
			accum += t.getOdds();
			if (accum > roll)
				return t.getState();
		}
		
		// fallback
		return State.FLAT;
	}
	
	protected void addTransitions() {
		ArrayList<Transition> tr;
		
		// Initial : Start
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.START, 1.0));
		transitions.put(State.INITIAL, tr);
		
		// Start : Flat
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 1.0));
		transitions.put(State.START, tr);
		
		// Flat : Pipe, Enemies, Flat, Gap
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.PIPE, 0.05));
		tr.add(new Transition(State.ENEMIES, 0.05));
		tr.add(new Transition(State.FLAT, 0.5));
		tr.add(new Transition(State.GAP, 0.1));
		tr.add(new Transition(State.BLOCKS, 0.1));
		tr.add(new Transition(State.COINS, 0.1));
		transitions.put(State.FLAT, tr);
		
		// Pipe : Enemies, Flat, Gap
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.ENEMIES, 0.25));
		tr.add(new Transition(State.FLAT, 0.65));
		tr.add(new Transition(State.GAP, 0.1));
		transitions.put(State.PIPE, tr);
		
		// Enemies : Pipe, Enemies, Flat, Gap
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.PIPE, 0.05));
		tr.add(new Transition(State.ENEMIES, 0.05));
		tr.add(new Transition(State.FLAT, 0.4));
		tr.add(new Transition(State.BLOCKS, 0.15));
		tr.add(new Transition(State.COINS, 0.35));
		transitions.put(State.ENEMIES, tr);
		
		// Gap : Flat, Pipe
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 0.85));
		tr.add(new Transition(State.PIPE, 0.05));
		tr.add(new Transition(State.COINS, 0.1));
		transitions.put(State.GAP, tr);
		
		// Block : Flat, Gap, Enemies
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 0.55));
		tr.add(new Transition(State.GAP, 0.25));
		tr.add(new Transition(State.ENEMIES, 0.2));
		transitions.put(State.BLOCKS, tr);
		
		// Coins : Flat, Gap, Pipe
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 0.55));
		tr.add(new Transition(State.GAP, 0.3));
		tr.add(new Transition(State.PIPE, 0.15));
		transitions.put(State.COINS, tr);
	}

}

class Transition {
	protected State state;
	protected Double odds;
	
	public Transition(State state, double odds) {
		this.state = state;
		this.odds = odds;
	}
	
	public State getState() { return state; }
	public double getOdds() { return odds; }
}
