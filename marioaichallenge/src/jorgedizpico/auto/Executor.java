package jorgedizpico.auto;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;

public class Executor {
	
	protected static String filesAuto[] =
			new String[]{"sch/speeder.auto",
						 "sch/explorer.auto",
						 "sch/explorer.auto"};
	
	protected Automaton[] autos;
	protected Trace[] traces;

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
	
	public Trace generateTraceMix(int length, double[] odds) throws Exception {
		try {
			traces = new Trace[autos.length];
			
			for (int i = 0; i < autos.length; i++) {
				autos[i].init();
				traces[i] = new Trace();
				
				while (traces[i].size() < length) {
					Chunk g = autos[i].step();
					if (null != g) {
						traces[i].addChunk(g);
					}
				}
			}
			
			double[] _odds = proportion(odds);			
			Trace mix = new Trace();
			
			for (int i = 0; i < length; i++) {
				double roll = new Random().nextDouble();
				double accum = 0.0;
				
				for (int t = 0; t < traces.length; t++) {
					accum += _odds[t];
					if (roll < accum) {
						mix.addChunk(traces[t].getChunk(i));
						break;
					}
				}
			}
			
			return mix;
			
		} catch (Exception e) {
			System.out.println("Error while generating genotype.");
			throw e;
		}
	}
	
	protected double[] proportion(double[] odds) {
		double[] _odds = new double[odds.length];
		double accum = 0.0;
		
		for (double d : odds)
			accum += d;
		for (int i = 0; i < _odds.length; i++)
			_odds[i] = odds[i]/accum;
		
		return _odds;
	}



}
