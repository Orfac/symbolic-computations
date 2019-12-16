package expressions.logic;

import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class Equal {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if (symbols[0].equals(symbols[1]))
                    return LogicFunctions.True;
                return LogicFunctions.False;
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
