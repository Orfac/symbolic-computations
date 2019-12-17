package expressions.core;

import model.Symbol;

import java.util.function.Function;

public class Express {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                return null;
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
