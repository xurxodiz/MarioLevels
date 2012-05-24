package jorgedizpico.auto;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Stack;

public class Executor {
	
	protected static String speederAutoFile  = "sch/speeder.auto";
	protected static String explorerAutoFile = "sch/explorer.auto";
	
	protected Automaton auto;

	public Executor (int type) throws Exception {
		try {
			FileInputStream fis = null;
			ObjectInputStream in = null;
			
			fis = new FileInputStream(speederAutoFile);
			in = new ObjectInputStream(fis);
			
			auto = (Automaton)in.readObject();
			
		} catch (Exception e) {
			System.out.println("Unable to read automata.");
			throw e;
		}
	}
	
	public Trace generateTrace(int length) throws Exception {
		try {
			Trace trace = new Trace();
			Stack<State> stack = new Stack<State>();
			
			stack.push(auto.getDummy("initial"));
			
			while (trace.size() < length) {
				State st = stack.pop();
				st.execute(stack, trace);
			}
			
			return trace;
			
		} catch (Exception e) {
			System.out.println("Error while generating genotype.");
			throw e;
		}
	}



}
