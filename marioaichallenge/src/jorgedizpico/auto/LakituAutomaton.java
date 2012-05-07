package jorgedizpico.auto;

import jorgedizpico.LakituBuilder;

public class LakituAutomaton {
	
	protected LakituState state;

	public LakituAutomaton (){
	}
	
	public void execute(LakituBuilder lkb) {
		int err = 1;
		
		state = new StateInitial();
		
		while (err < lkb.getLevelWidth()) {
			state = state.transition();
			err = state.genesis(lkb);
		}
	}

}
