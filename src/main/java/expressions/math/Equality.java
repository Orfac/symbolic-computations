package expressions.math;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Equality {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                return new Expression(MathFunction.Equality, new Symbol[]{
                   symbols[0],
                   symbols[1]
                });
            };

    public static Function<Symbol[], Symbol> getExpression() { return expression; }
}
