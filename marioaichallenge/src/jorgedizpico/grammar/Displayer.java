/* -----------------------------------------------------------------------------
 * Displayer.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.1
 * Produced : Thu May 24 22:07:05 CEST 2012
 *
 * -----------------------------------------------------------------------------
 */

package jorgedizpico.grammar;

import java.util.ArrayList;

public class Displayer implements Visitor
{

  public Object visit(Rule$schematics rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$transitions rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$options rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$concatenation rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$element rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$nonterminal rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$terminal rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$weight rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$c_wsp rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$c_nl rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$comment rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$UPCASE rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$LOWCASE rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$DIGIT rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$VCHAR rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$CRLF rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule$WSP rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Terminal$StringValue value)
  {
    System.out.print(value.spelling);
    return null;
  }

  public Object visit(Terminal$NumericValue value)
  {
    System.out.print(value.spelling);
    return null;
  }

  private Object visitRules(ArrayList<Rule> rules)
  {
    for (Rule rule : rules)
      rule.accept(this);
    return null;
  }
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
