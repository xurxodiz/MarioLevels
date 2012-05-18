package jorgedizpico.auto;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import jorgedizpico.Builder;
import jorgedizpico.LakituLevel;
import jorgedizpico.grammar.Parser;
import jorgedizpico.grammar.Rule;

public class Automaton {
	
	private static int GENOTYPE_LENGTH = 150;
	
	protected Repository repo;
	protected Stack<State> stack = new Stack<State>();
	protected ArrayList<Gene> genotype  = new ArrayList<Gene>();

	public Automaton (int type) throws Exception {

		Rule schematics = Parser.parse("schematics", new File("gr/intermediate.abnf"));

		Constructor cons = new Constructor();
		repo = (Repository) schematics.accept(cons);
	}
	
	
	void pushState(State st) {
		stack.push(st);
	}
	
	boolean addGene(Gene gene) {
		return genotype.add(gene);
	}
	
	private void generateGenotype(int length) throws Exception {
		try {
			stack.clear();
			genotype.clear();
			
			stack.push(repo.getDummy("initial"));
			
			while (genotype.size() < length) {
				State st = stack.pop();
				st.execute(this);
			}
			
			stack.clear();
			
		} catch (Exception e) {
			System.out.println("Error while generating genotype.");
			throw e;
		}
	}

	public LakituLevel buildLevel() {
		try { 
			LakituLevel lvl = new LakituLevel();
			Builder lkb = new Builder(lvl);
			generateGenotype(GENOTYPE_LENGTH);
			
			
			lkb.createStartPlug();
			
			for (Gene g : genotype)
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
