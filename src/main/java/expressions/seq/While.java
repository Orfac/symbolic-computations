package expressions.seq;

import expressions.logic.LogicFunctions;
import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class While {
    private static Function<Symbol[], Symbol> expression =
            symbols -> new Expression(LogicFunctions.If, new Symbol[] {
                    symbols[0],
                    new Expression(SequenceFunctions.List, new Symbol[]{
                            symbols[1],
                            new Expression(SequenceFunctions.While, new Symbol[]{
                                    symbols[0],
                                    symbols[1]
                            })
                    }),
                    new StringSymbol("Null")
            });

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
