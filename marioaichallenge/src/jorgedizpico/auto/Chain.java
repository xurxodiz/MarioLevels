package jorgedizpico.auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Chain implements Iterable<State> {
	
	protected ArrayList<State> states  = new ArrayList<State>();
	protected Double odds = 0.0;
	
	public Chain() {
		// ...
	}
	
	public void addState(State state) {
		states.add(state);
	}
	
	public boolean isEmpty() {
		return (0 == states.size());
	}
	
	public double getOdds() { return odds; }
	public void setOdds(double odds) { this.odds = odds; }	

	@Override
	public Iterator<State> iterator() {
        Iterator<State> i = Collections.unmodifiableList(states).iterator();
        return i; 
	}

}
