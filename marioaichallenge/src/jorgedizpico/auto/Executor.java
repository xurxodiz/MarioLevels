package jorgedizpico.auto;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Stack;

public class Executor {
	
	protected static String fileAutoSpeeder  = "sch/speeder.auto";
	protected static String fileAutoExplorer = "sch/explorer.auto";
	
	protected Automaton[] autos = new Automaton[2];
	protected Trace[] traces;

	public Executor (int type) throws Exception {
		try {
			FileInputStream fis = null;
			ObjectInputStream in = null;
			
			fis = new FileInputStream(fileAutoSpeeder);
			in = new ObjectInputStream(fis);
			
			autos[0] = (Automaton)in.readObject();
			
			fis = new FileInputStream(fileAutoExplorer);
			in = new ObjectInputStream(fis);
			
			autos[1] = (Automaton)in.readObject();
			
		} catch (Exception e) {
			System.out.println("Unable to read automata.");
			throw e;
		}
	}
	
	public Trace generateTraceMix(int length, double[] odds) throws Exception {
		try {
			traces = new Trace[2];
			
			for (int k = 0; k < 2; k++) {
				autos[k].init();
				while (traces[k].size() < length) {
					traces[k].addGene(autos[k].step());
				}
			}
			
			double[] _odds = new double[odds.length];
			double accum = 0.0;
			
			for (double d : odds)
				accum += d;
			for (int i = 0; i < _odds.length; i++)
				_odds[i] = odds[i]/accum;
			
			Trace mix = new Trace();
			
			for (int i = 0; i < length; i++) {
				double roll = 0.5;
				for (int t = 0; t < traces.length; t++)
					if (roll < _odds[t])
						mix.addGene(traces[t].getGene(i));
				
			}
			
			return mix;
			
		} catch (Exception e) {
			System.out.println("Error while generating genotype.");
			throw e;
		}
	}



}
