package expressions.context;

import expressions.core.CoreFunctions;
import expressions.logic.LogicFunctions;
import model.Expression;
import model.Symbol;

import java.util.function.Function;

public class GetHead {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if (symbols[0] instanceof Expression){
                    return ((Expression)symbols[0]).getHead();
                } else {
                    return symbols[1];
                }
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
