package expressions.context;

import model.Constant;
import model.Expression;
import model.Symbol;

import java.util.function.Function;

public class Get {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                Expression expression = (Expression) symbols[0];

                Symbol[] arguments = expression.getArguments();

                int index = (int) ((Constant)symbols[1]).getValue();
                return arguments[index];
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
