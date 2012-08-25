package jorgedizpico.auto;

import java.util.Random;

import jorgedizpico.level.Chunk;
import jorgedizpico.level.Trace;

public class Executor {
	
	protected FSM fsmExplorer = null;
	protected FSM fsmSpeeder = null;
	protected FSM fsmPhase = null;
	
	protected double[] odds;

	public Executor(double[] odds) throws Exception {
		
		this.odds = proportion(odds);
		
		fsmExplorer = Automaton.getExplorer();
		if (null == fsmExplorer)
			throw new Exception("Unable to read Explorer automaton.");
		
		fsmSpeeder = Automaton.getSpeeder();
		if (null == fsmSpeeder)
			throw new Exception("Unable to read Speeder automaton.");
		
		fsmPhase = new PhaseAutomaton(new Automaton[]
											{(Automaton)fsmExplorer,
											 (Automaton)fsmSpeeder},
									  this.odds);

	}
	
	public Trace generateTraceExplorer(int length) {
		return generateTrace(length, fsmExplorer);
	}
	
	public Trace generateTraceSpeeder(int length) {
		return generateTrace(length, fsmSpeeder);
	}
	
	public Trace generateTracePhase(int length) {
		return generateTrace(length, fsmPhase);
	}
	
	protected Trace generateTrace(int length, FSM fsm) {
		fsm.init();
		Trace t = new Trace();
		while (t.size() < length) {
			Chunk g = fsm.step();
			if (null != g)
				t.addChunk(g);
		}
		return t;
	}
	
	public Trace generateTraceMix(int length) throws Exception {
		
		Trace[] traces = new Trace[2];
		traces[0] = generateTraceExplorer(length);
		traces[1] = generateTraceSpeeder(length);
		
		Trace mix = new Trace();
		
		for (int i = 0; i < length; i++) {
			int t = pick();
			mix.addChunk(traces[t].getChunk(i));
		}
		
		return mix;
	}
	
	protected double[] proportion(double[] odds) {
		
		// ok this merits some explanation
		// we'll receive numbers like -87 and -120
		// higher number means higher probability
		// so what we do is negate them
		// and swap them
		// then act like they are weights
		
		
		double[] _odds = new double[odds.length];
		double accum = 0.0, total = 0.0;
		
		_odds[0] = -odds[1];
		_odds[1] = -odds[0];
		
		for (double d : _odds)
			total += d;
		for (int i = 0; i < _odds.length; i++) {
			accum += _odds[i];
			_odds[i] = accum/total;
		}
		
		System.out.println("explorer: " + _odds[0]);
		System.out.println("speeder: " + _odds[1]);
		
		return _odds;
	}
	
	protected int pick() {
		double roll = new Random().nextDouble();
		for (int i = 0; i < odds.length; i++)
			if (roll < odds[i])
				return i;
		// should never happen, so let's play it safe
		return 0;
	}


}
