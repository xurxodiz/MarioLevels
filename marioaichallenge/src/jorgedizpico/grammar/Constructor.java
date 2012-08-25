package jorgedizpico.grammar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import jorgedizpico.auto.Automaton;
import jorgedizpico.auto.Chain;
import jorgedizpico.auto.Dummy;
import jorgedizpico.auto.State;
import jorgedizpico.grammar.*;
import jorgedizpico.level.Chunk;

public class Constructor implements Visitor {
	
	protected Automaton auto;
	
	public static void main(final String args[]) {
		
		if (args.length < 1) {
			System.out.println("Usage: Constructor [<files>]");
			return;
		}
		
		try {
						
			for (String fileName : args) {
				
				Rule schematic = Parser.parse("schematics", new File(fileName));

				Constructor cons = new Constructor();
				Automaton auto = (Automaton) schematic.accept(cons);
							
				FileOutputStream fos = new FileOutputStream(fileName + ".auto");
				ObjectOutputStream out =  new ObjectOutputStream(fos);
				out.writeObject(auto);
				
				out.close();
				fos.close();
			}
		} catch (Exception e) {
			System.out.println("Unable to read schematics or write automata.");
			e.printStackTrace();
		}
	}
	
	@Override
	public Object visit(Rule$schematics rule) {
		auto = new Automaton();
		visitRules(rule.rules);
		auto.validate();
		return auto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Rule$transitions rule) {
		ArrayList<Object> derivations = visitRules(rule.rules);
		
		Dummy dm = null;
		ArrayList<Object> chains = null;
		
		for (Object o : derivations)
			if (o instanceof Dummy) dm = (Dummy) o;
			else if (o instanceof ArrayList<?>) chains = (ArrayList<Object>) o;
		
		for (Object o : chains)
			if (o instanceof Chain) dm.addChain((Chain)o);
		
		return null;
	}
	
	@Override
	public Object visit(Rule$options rule) {
		return visitRules(rule.rules);
	}
	
	@Override
	public Object visit(Rule$concatenation rule) {
		Chain ch = new Chain();
		
		ArrayList<Object> children = visitRules(rule.rules);
		
		// weight. it's optional, thus we check for it.
		if (children.get(children.size()-1) instanceof Double) {
			Object o = children.remove(children.size()-1);
			ch.setOdds((Double)o);
		} else
			ch.setOdds(1.0);
		
		for (Object st : children)
			if (st instanceof State) ch.addState((State)st);
		return ch;
	}
	
	@Override
	public Object visit(Rule$element rule) {
		return rule.rules.get(0).accept(this);
	}
	
	@Override
	public Object visit(Rule$nonterminal rule) {
		return auto.getDummy(rule.spelling);
	}
	
	@Override
	public Object visit(Rule$terminal rule) {
		return Chunk.getChunk(rule.spelling);
	}
	
	@Override
	public Object visit(Rule$weight rule) {
		return Double.parseDouble(rule.spelling);
	}
	
	private ArrayList<Object> visitRules(ArrayList<Rule> rules) {
		ArrayList<Object> array = new ArrayList<Object>();
		Object o;
		for (Rule rule : rules) {
			 o = rule.accept(this);
			 if (null != o) array.add(o);
		}
		return array;
	}
	
	@Override
	public Object visit(Terminal$StringValue rule) { /* won't be visited */ return rule.spelling; }
	
	@Override
	public Object visit(Terminal$NumericValue rule) { /* won't be visited */ return rule.spelling; }
	
	@Override
	public Object visit(Rule$UPCASE rule) { /* won't be visited */ return null; }
	
	@Override
	public Object visit(Rule$LOWCASE rule) { /* won't be visited */ return null; }
	
	@Override
	public Object visit(Rule$DIGIT rule) { /* won't be visited */ return null; }
	
	@Override
	public Object visit(Rule$VCHAR rule) { /* won't be visited */ return null; }
	
	@Override
	public Object visit(Rule$c_wsp rule) { /* ignored */ return null; }
	
	@Override
	public Object visit(Rule$c_nl rule) { /* ignored */ return null; }
	
	@Override
	public Object visit(Rule$comment rule) { /* ignored */ return null; }
	
	@Override
	public Object visit(Rule$CRLF rule) { /* ignored */ return null; }
	
	@Override
	public Object visit(Rule$WSP rule) { /* ignored */ return null; }
	
}