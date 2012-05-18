package jorgedizpico.auto;

import java.util.ArrayList;
import java.util.Collection;

import jorgedizpico.grammar.*;

public class Constructor implements Visitor {
	
	private Repository repo;
	
	@Override
	public Object visit(Rule$schematics rule) {
		repo = new Repository();
		visitRules(rule.rules);
		return repo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Rule$transition rule) {
		ArrayList<Object> transitions = visitRules(rule.rules);
		Dummy st = (Dummy) transitions.get(0);
		ArrayList<Object> chains = (ArrayList<Object>) transitions.get(1);
		for (Object o : chains)
			st.addChain((Chain)o);
		return null;
	}
	
	@Override
	public Object visit(Rule$alternation rule) {
		return visitRules(rule.rules);
	}
	
	@Override
	public Object visit(Rule$concatenation rule) {
		Chain ch = new Chain();
		
		Collection<Object> children = visitRules(rule.rules);
		
		Object o = children.remove(children.size());
		ch.setOdds((Double)o);
		
		for (Object st : children)
			ch.addState((State)st);
		return ch;
	}
	
	@Override
	public Object visit(Rule$element rule) {
		return rule.rules.get(0).accept(this);
	}
	
	@Override
	public Object visit(Rule$nonterminal rule) {
		return repo.getDummy(rule.spelling);
	}
	
	@Override
	public Object visit(Rule$terminal rule) {
		return repo.getGene(rule.spelling);
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
	public Object visit(Rule$UPCASE rule) { /* won't be visited */ return rule.spelling; }
	
	@Override
	public Object visit(Rule$LOWCASE rule) { /* won't be visited */ return rule.spelling; }
	
	@Override
	public Object visit(Rule$DIGIT rule) { /* won't be visited */ return rule.spelling; }
	
	@Override
	public Object visit(Rule$VCHAR rule) { /* won't be visited */ return rule.spelling; }
	
	@Override
	public Object visit(Terminal$StringValue rule) { /* won't be visited */ return rule.spelling; }
	
	@Override
	public Object visit(Terminal$NumericValue rule) { /* won't be visited */ return rule.spelling; }
	
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