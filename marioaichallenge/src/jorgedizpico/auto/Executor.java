package jorgedizpico.auto;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;

public class Executor {
	
	protected static final int EXPLORER = 0;
	protected static final int SPEEDER = 1;
	
	// set indexes above according to load order here
	
	protected static String filesAuto[]
						= new String[]{
								 "sch/explorer.auto",
								 "sch/speeder.auto"};
	
	protected Automaton[] autos;

	public Executor() throws Exception {
		try {
			FileInputStream fis = null;
			ObjectInputStream in = null;
			
			autos = new Automaton[filesAuto.length];
			
			for (int i = 0; i < filesAuto.length; i++) {
				fis = new FileInputStream(filesAuto[i]);
				in = new ObjectInputStream(fis);
				autos[i] = (Automaton)in.readObject();
			}
			
		} catch (Exception e) {
			System.out.println("Unable to read automata.");
			throw e;
		}
	}
	
	protected Trace generateTrace(int length, FSM fsm) throws Exception {
		fsm.init();
		Trace t = new Trace();
		while (t.size() < length) {
			Chunk g = fsm.step();
			if (null != g)
				t.addChunk(g);
		}
		return t;
	}
	
	public Trace generateTraceMix(int length, double[] odds) throws Exception {
		try {
			double[] _odds = proportion(odds);
			
			Trace[] traces = new Trace[autos.length];
			traces[0] = generateTrace(length, autos[0]);
			traces[1] = generateTrace(length, autos[1]);
			
			Trace mix = new Trace();
			
			for (int i = 0; i < length; i++) {
				int t = pick(_odds);
				mix.addChunk(traces[t].getChunk(i));
			}
			
			return mix;
			
		} catch (Exception e) {
			System.out.println("Error while generating genotype.");
			throw e;
		}
	}
	
	public Trace generateTracePhase(int length, double[] odds) throws Exception {
		try {
			
			FSM phase = new PhaseAutomaton(autos, odds);
			return generateTrace(length, phase);
			
		} catch (Exception e) {
			System.out.println("Error while generating genotype.");
			throw e;
		}
	}
	
	protected double[] proportion(double[] odds) {
		double[] _odds = new double[odds.length];
		double accum = 0.0, total = 0.0;
		
		for (double d : odds)
			total += d;
		for (int i = 0; i < _odds.length; i++) {
			accum += odds[i];
			_odds[i] = accum/total;
		}
			
		return _odds;
	}
	
	protected int pick(double[] odds) {
		double roll = new Random().nextDouble();
		for (int i = 0; i < odds.length; i++)
			if (roll < odds[i])
				return i;
		// should never happen, so let's play it safe
		return 0;
	}


}
