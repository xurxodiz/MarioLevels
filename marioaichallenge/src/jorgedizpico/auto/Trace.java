package jorgedizpico.auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import jorgedizpico.level.Builder;
import jorgedizpico.level.LakituLevel;

public class Trace implements Iterable<Gene> {
	
	protected ArrayList<Gene> trace;
	
	public Trace() {
		trace = new ArrayList<Gene>();
	}
	
	public boolean addGene(Gene g) {
		return trace.add(g);
	}
	
	public int size() {
		return trace.size();
	}
	
	@Override
	public Iterator<Gene> iterator() {
        Iterator<Gene> i = Collections.unmodifiableList(trace).iterator();
        return i; 
	}
	
	public LakituLevel buildLevel() {
		try { 
			LakituLevel lvl = new LakituLevel();
			Builder lkb = new Builder(lvl);			
			
			lkb.createStartPlug();
			
			for (Gene g : trace)
				if (!g.genesis(lkb)) {
					System.out.println(g);
					return null;
				}
	
			lkb.createEndPlug();
			
			lkb.fixLevel();
			
			return lvl;
			
		} catch (Exception e) {
			return null;
		}
	}

}
