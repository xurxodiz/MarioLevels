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
				
		System.out.println(state);
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
		
		// Start : Hub
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 1.0));
		transitions.put(State.START, tr);
		
		// Flat : Hub
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 0.05));
		tr.add(new Transition(State.HUB, 0.95));
		transitions.put(State.FLAT, tr);
		
		// Hub : Pipe, Enemies, Flat, Gap, Blocks, Coins, Cannon
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.PIPE, 0.12));
		tr.add(new Transition(State.ENEMIES, 0.20));
		tr.add(new Transition(State.GAP, 0.19));
		tr.add(new Transition(State.BLOCKS, 0.20));
		tr.add(new Transition(State.COINS, 0.17));
		tr.add(new Transition(State.CANNON, 0.08));
		transitions.put(State.HUB, tr);
		
		// Pipe : PipePiranha, PipeOut
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.PIPEPIRANHA, 0.5));
		tr.add(new Transition(State.PIPEOUT, 0.5));
		transitions.put(State.PIPE, tr);
		
		// PipePiranha : PipeOut
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.PIPEOUT, 1.0));
		transitions.put(State.PIPEPIRANHA, tr);
		
		// PipeOut : Flat
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 1.0));
		transitions.put(State.PIPE, tr);
		
		// Gap : GapStd, GapStairsUp
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.GAPSTD, 0.75));
		tr.add(new Transition(State.GAPSTAIRS, 0.25));
		transitions.put(State.GAP, tr);
		
		// GapStd : GapOut
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.GAPOUT, 1.0));
		transitions.put(State.GAPSTD, tr);
		
		// GapStairs : GapOut
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.GAPOUT, 1.0));
		transitions.put(State.GAPSTAIRS, tr);
		
		// GapOut : Flat
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 1.0));
		transitions.put(State.GAPOUT, tr);
		
		// Coins : Coins, Flat
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.COINS, 0.8));
		tr.add(new Transition(State.FLAT, 0.2));
		transitions.put(State.COINS, tr);
		
		// Cannon : Hub
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 1.0));
		transitions.put(State.CANNON, tr);
		
		// Block : Enemies, Flat, Blocks
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.FLAT, 0.2));
		tr.add(new Transition(State.BLOCKS, 0.8));
		transitions.put(State.BLOCKS, tr);
		
		// Enemies : Block, Flat, Enemies
		tr = new ArrayList<Transition>();
		tr.add(new Transition(State.ENEMIES, 0.8));
		tr.add(new Transition(State.FLAT, 0.2));
		transitions.put(State.ENEMIES, tr);
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
