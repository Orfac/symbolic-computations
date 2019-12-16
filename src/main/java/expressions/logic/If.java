package expressions.logic;

import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class If {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if (symbols[0].equals(LogicFunctions.True)){
                    return symbols[1];
                } else {
                    if (symbols[0].equals(LogicFunctions.False)){
                        return symbols[2];
                    } else {
                        return new Expression(new StringSymbol("If"), symbols);
                    }
                }
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
