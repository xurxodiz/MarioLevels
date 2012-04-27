package jorgedizpico;

public class StateStart extends LakituState {

	public StateStart() {
		super();
		// nothing needed here
	}

	@Override
	public LakituState transition() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	protected int genesis(LakituBuilder lkb, int x) {
		// start state creates nothing
		return 0;
	}

}
