package expressions.logic;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class And {
    private static Function<Symbol[], Symbol> expression =
            symbols -> new Expression(new StringSymbol("If"),
                    new Symbol[] {
                            symbols[0],
                            symbols[1],
                            LogicFunctions.False
                    });

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
