package expressions.math;

import model.Expression;
import model.Symbol;

import java.util.function.Function;

public class Equality {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                return new Expression(MathFunction.Equality, symbols );
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
