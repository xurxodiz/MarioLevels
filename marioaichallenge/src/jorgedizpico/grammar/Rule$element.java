/* -----------------------------------------------------------------------------
 * Rule$element.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.1
 * Produced : Thu May 24 22:07:05 CEST 2012
 *
 * -----------------------------------------------------------------------------
 */

package jorgedizpico.grammar;

import java.util.ArrayList;

final public class Rule$element extends Rule
{
  private Rule$element(String spelling, ArrayList<Rule> rules)
  {
    super(spelling, rules);
  }

  public Object accept(Visitor visitor)
  {
    return visitor.visit(this);
  }

  public static Rule$element parse(ParserContext context)
  {
    context.push("element");

    boolean parsed = true;
    int s0 = context.index;
    ArrayList<Rule> e0 = new ArrayList<Rule>();
    Rule rule;

    /* alternation[1] - start */
    parsed = false;
    if (!parsed)
    {
      /* concatenation - start */
      {
        ArrayList<Rule> e1 = new ArrayList<Rule>();
        int s1 = context.index;
        parsed = true;
        if (parsed)
        {
          /* repetition(1*1) - start */
          boolean f1 = true;
          int c1 = 0;
          /* required */
          for (int i1 = 0; i1 < 1 && f1; i1++)
          {
            rule = Rule$nonterminal.parse(context);
            if ((f1 = rule != null))
            {
              e1.add(rule);
              c1++;
            }
          }
          /* optional - none */
          parsed = c1 == 1;
          /* repetition - end */
        }
        if (parsed)
          e0.addAll(e1);
        else
          context.index = s1;
      }
      /* concatenation - end */
    }
    if (!parsed)
    {
      /* concatenation - start */
      {
        ArrayList<Rule> e1 = new ArrayList<Rule>();
        int s1 = context.index;
        parsed = true;
        if (parsed)
        {
          /* repetition(1*1) - start */
          boolean f1 = true;
          int c1 = 0;
          /* required */
          for (int i1 = 0; i1 < 1 && f1; i1++)
          {
            rule = Rule$terminal.parse(context);
            if ((f1 = rule != null))
            {
              e1.add(rule);
              c1++;
            }
          }
          /* optional - none */
          parsed = c1 == 1;
          /* repetition - end */
        }
        if (parsed)
          e0.addAll(e1);
        else
          context.index = s1;
      }
      /* concatenation - end */
    }
    /* alternation[1] - end */

    rule = null;
    if (parsed)
      rule = new Rule$element(context.text.substring(s0, context.index), e0);
    else
      context.index = s0;

    context.pop("element", parsed);

    return (Rule$element)rule;
  }
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
