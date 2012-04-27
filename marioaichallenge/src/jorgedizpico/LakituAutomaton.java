package jorgedizpico;

public class LakituAutomaton {
	
	protected LakituBuilder lkb;
	protected LakituState state;

	public LakituAutomaton (LakituBuilder lkb){
		this.lkb = lkb;
	}
	
	public void execute(int end) {
		int x = 0;
		
		state = new StateStart();
		
		while (x < end) {
			state = state.transition();
			x = state.genesis(lkb, x);
		}
		
	}

}
