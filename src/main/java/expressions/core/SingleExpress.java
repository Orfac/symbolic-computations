package expressions.core;

import expressions.context.Get;
import expressions.logic.LogicFunctions;
import model.Expression;
import model.Symbol;

import java.util.function.Function;

public class SingleExpress {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                return new Expression(LogicFunctions.If, new Symbol[]{
                        new Expression(CoreFunctions.IsTransitive, new Symbol[]{
                                symbols[0]
                        })
                });
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
