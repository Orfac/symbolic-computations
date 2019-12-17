package expressions.core;

import expressions.logic.LogicFunctions;
import expressions.math.MathFunction;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.Impl.ModelVisitorImpl;

import java.util.HashMap;
import java.util.function.Function;

public class Transitive {

    private static Function<Symbol[], Symbol> expression =
            symbols -> new Expression(LogicFunctions.Or, new Symbol[]{
                    new Expression(LogicFunctions.Equal, new Symbol[]{
                            symbols[0],
                            MathFunction.Sub
                    }),
                    new Expression(LogicFunctions.Equal, new Symbol[]{
                            symbols[0],
                            MathFunction.Div
                    }),
            });

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
