/* -----------------------------------------------------------------------------
 * XmlDisplayer.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.1
 * Produced : Thu May 24 22:07:05 CEST 2012
 *
 * -----------------------------------------------------------------------------
 */

package jorgedizpico.grammar;

import java.util.ArrayList;

public class XmlDisplayer implements Visitor
{
  private boolean terminal = true;

  public Object visit(Rule$schematics rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<schematics>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</schematics>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$transitions rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<transitions>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</transitions>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$options rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<options>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</options>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$concatenation rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<concatenation>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</concatenation>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$element rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<element>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</element>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$nonterminal rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<nonterminal>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</nonterminal>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$terminal rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<terminal>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</terminal>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$weight rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<weight>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</weight>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$c_wsp rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<c-wsp>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</c-wsp>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$c_nl rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<c-nl>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</c-nl>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$comment rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<comment>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</comment>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$UPCASE rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<UPCASE>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</UPCASE>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$LOWCASE rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<LOWCASE>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</LOWCASE>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$DIGIT rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<DIGIT>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</DIGIT>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$VCHAR rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<VCHAR>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</VCHAR>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$CRLF rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<CRLF>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</CRLF>");
    terminal = false;
    return null;
  }

  public Object visit(Rule$WSP rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<WSP>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</WSP>");
    terminal = false;
    return null;
  }

  public Object visit(Terminal$StringValue value)
  {
    System.out.print(value.spelling);
    terminal = true;
    return null;
  }

  public Object visit(Terminal$NumericValue value)
  {
    System.out.print(value.spelling);
    terminal = true;
    return null;
  }

  private Boolean visitRules(ArrayList<Rule> rules)
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
