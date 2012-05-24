/* -----------------------------------------------------------------------------
 * Visitor.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.1
 * Produced : Thu May 24 22:07:05 CEST 2012
 *
 * -----------------------------------------------------------------------------
 */

package jorgedizpico.grammar;

public interface Visitor
{
  public Object visit(Rule$schematics rule);
  public Object visit(Rule$transitions rule);
  public Object visit(Rule$options rule);
  public Object visit(Rule$concatenation rule);
  public Object visit(Rule$element rule);
  public Object visit(Rule$nonterminal rule);
  public Object visit(Rule$terminal rule);
  public Object visit(Rule$weight rule);
  public Object visit(Rule$c_wsp rule);
  public Object visit(Rule$c_nl rule);
  public Object visit(Rule$comment rule);
  public Object visit(Rule$UPCASE rule);
  public Object visit(Rule$LOWCASE rule);
  public Object visit(Rule$DIGIT rule);
  public Object visit(Rule$VCHAR rule);
  public Object visit(Rule$CRLF rule);
  public Object visit(Rule$WSP rule);

  public Object visit(Terminal$StringValue value);
  public Object visit(Terminal$NumericValue value);
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
