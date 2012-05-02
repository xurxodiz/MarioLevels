package jorgedizpico;

public class StateInitial implements LakituState {

	public StateInitial() {
		super();
		// nothing needed here
	}

	@Override
	public LakituState transition() {
		// TODO Auto-generated method stub
		return new StateStart();
	}

	@Override
	public int genesis(LakituBuilder lkb) {
		// start state creates nothing
		return 1;
	}

}
