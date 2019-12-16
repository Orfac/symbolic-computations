package expressions.logic;

import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class Or {
    private static Function<Symbol[], Symbol> expression =
            symbols -> new Expression(LogicFunctions.If,
                    new Symbol[] {
                            symbols[0],
                            LogicFunctions.True,
                            symbols[1]
                    });

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
