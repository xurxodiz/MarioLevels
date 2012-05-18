package jorgedizpico.auto;

import java.util.ArrayList;
import java.util.Random;


public class Dummy implements State {
	
	private ArrayList<Chain> transitions = new ArrayList<Chain>();
	
	public boolean execute(Automaton auto) {
		double roll = new Random().nextDouble();
		double accum = 0.0;
				
		for (Chain ch : transitions) {
			accum += ch.getOdds();
			if (accum > roll) {
				for (State st : ch)
					auto.pushState(st);
				return true;
			}
		}
		return false;
	}
	
	public boolean addChain(Chain ch) {
		return transitions.add(ch);
	}
	
	public boolean validateChains() {
		// do magic to make all odds add up to 1
		// make sure there is at least one chain!
		return true;
	}

}
