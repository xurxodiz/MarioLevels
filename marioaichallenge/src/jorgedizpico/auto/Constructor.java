package jorgedizpico.auto;

import java.util.ArrayList;

import jorgedizpico.grammar.*;

public class Constructor implements Visitor {
	
	@Override
	public Object visit(Rule$transitions rule) {
		visitRules(rule.rules);
		return null;
	}
	
	@Override
	public Object visit(Rule$transition rule) {
		// array = visitRules
		// array[0] -> state-dummy
		// array[0] -> [[chain,weight]]
		// automata.add(state, chain)
		return null;
	}
	
	@Override
	public Object visit(Rule$alternation rule) {
		// visitRules returns a list of [chain,weight]
		// it's probably ok to return it as is
		return null;
	}
	
	@Override
	public Object visit(Rule$concatenation rule) {
		// visitRules returns a list of elements and a weight at the end
		// we should return [chain, weight]
		return null;
	}
	
	@Override
	public Object visit(Rule$element rule) {
		// return an element, as per definition (state/token)
		return null;
	}
	
	@Override
	public Object visit(Rule$nonterminal rule) {
		return new String(rule.spelling); // dummystate
	}
	
	@Override
	public Object visit(Rule$terminal rule) {
		return new String(rule.spelling); // workerstate
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