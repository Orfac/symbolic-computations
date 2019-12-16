package expressions.logic;

import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class Not {
    private static Function<Symbol[], Symbol> expression =
            symbols -> new Expression(new StringSymbol("If"),
                    new Symbol[] {
                            symbols[0],
                            LogicFunctions.False,
                            LogicFunctions.True
                    });

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
