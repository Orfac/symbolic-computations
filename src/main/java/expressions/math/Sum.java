package expressions.math;

import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class Sum {
    private Function<Symbol[], Symbol> expression =
            symbols -> new Expression(new StringSymbol("Sum"), symbols );

    public Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
