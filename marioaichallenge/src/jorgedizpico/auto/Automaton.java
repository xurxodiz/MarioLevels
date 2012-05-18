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

		Rule schematics = Parser.parse("transitions", new File("automaton1.abnf"));

		Constructor cons = new Constructor();
		repo = (Repository) schematics.accept(cons);
	}
	
	
	void pushState(State st) {
		stack.push(st);
	}
	
	boolean addGene(Gene gene) {
		return genotype.add(gene);
	}
	
	private void generateGenotype(int length) {
		
		stack.clear();
		genotype.clear();
		
		stack.push(repo.getDummy("INITIAL"));
		
		while (genotype.size() < length) {
			State st = stack.pop();
			st.execute(this);
		}
		
		stack.clear();
	}

	public LakituLevel buildLevel() {
		LakituLevel lvl = new LakituLevel();
		Builder lkb = new Builder(lvl);
		generateGenotype(GENOTYPE_LENGTH);
		
		lkb.createStartPlug();
		for (Gene g : genotype)
			if (!g.genesis(lkb)) return null;
		
		return lvl;
	}

}
