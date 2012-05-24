/* -----------------------------------------------------------------------------
 * Rule$comment.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.1
 * Produced : Thu May 24 22:07:05 CEST 2012
 *
 * -----------------------------------------------------------------------------
 */

package jorgedizpico.grammar;

import java.util.ArrayList;

final public class Rule$comment extends Rule
{
  private Rule$comment(String spelling, ArrayList<Rule> rules)
  {
    super(spelling, rules);
  }

  public Object accept(Visitor visitor)
  {
    return visitor.visit(this);
  }

  public static Rule$comment parse(ParserContext context)
  {
    context.push("comment");

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
            rule = Terminal$StringValue.parse(context, "#");
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
        {
          /* repetition(0*0) - start */
          boolean f1 = true;
          int c1 = 0;
          /* required - none */
          /* optional */
          for (int i1 = 0; f1; i1++)
          {
            /* group - start */
            int g1 = context.index;
            /* alternation[2] - start */
            parsed = false;
            if (!parsed)
            {
              /* concatenation - start */
              {
                ArrayList<Rule> e2 = new ArrayList<Rule>();
                int s2 = context.index;
                parsed = true;
                if (parsed)
                {
                  /* repetition(1*1) - start */
                  boolean f2 = true;
                  int c2 = 0;
                  /* required */
                  for (int i2 = 0; i2 < 1 && f2; i2++)
                  {
                    rule = Rule$WSP.parse(context);
                    if ((f2 = rule != null))
                    {
                      e2.add(rule);
                      c2++;
                    }
                  }
                  /* optional - none */
                  parsed = c2 == 1;
                  /* repetition - end */
                }
                if (parsed)
                  e1.addAll(e2);
                else
                  context.index = s2;
              }
              /* concatenation - end */
            }
            if (!parsed)
            {
              /* concatenation - start */
              {
                ArrayList<Rule> e2 = new ArrayList<Rule>();
                int s2 = context.index;
                parsed = true;
                if (parsed)
                {
                  /* repetition(1*1) - start */
                  boolean f2 = true;
                  int c2 = 0;
                  /* required */
                  for (int i2 = 0; i2 < 1 && f2; i2++)
                  {
                    rule = Rule$VCHAR.parse(context);
                    if ((f2 = rule != null))
                    {
                      e2.add(rule);
                      c2++;
                    }
                  }
                  /* optional - none */
                  parsed = c2 == 1;
                  /* repetition - end */
                }
                if (parsed)
                  e1.addAll(e2);
                else
                  context.index = s2;
              }
              /* concatenation - end */
            }
            /* alternation[2] - end */
            if (context.index > g1) c1++;
            f1 = c1 > i1;
            /* group - end */
          }
          parsed = true;
          /* repetition - end */
        }
        if (parsed)
        {
          /* repetition(1*1) - start */
          boolean f1 = true;
          int c1 = 0;
          /* required */
          for (int i1 = 0; i1 < 1 && f1; i1++)
          {
            rule = Rule$CRLF.parse(context);
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
      rule = new Rule$comment(context.text.substring(s0, context.index), e0);
    else
      context.index = s0;

    context.pop("comment", parsed);

    return (Rule$comment)rule;
  }
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
