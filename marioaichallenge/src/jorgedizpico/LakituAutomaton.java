package jorgedizpico;

public class LakituAutomaton {
	
	protected LakituState state;

	public LakituAutomaton (){
	}
	
	public void execute(LakituBuilder lkb) {
		int x = 0;
		int end = lkb.lvl.getWidth();
		
		state = new StateInitial();
		
		while (x < end) {
			state = state.transition();
			x = state.genesis(lkb, x);
		}
	}

}
