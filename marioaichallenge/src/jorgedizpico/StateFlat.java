package jorgedizpico;

public class StateFlat extends LakituState {

	@Override
	public LakituState transition() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	protected int genesis(LakituBuilder lkb, int x) {
		int width = 3;
		lkb.createFlatLand(x, width);
		return width;
	}

}
