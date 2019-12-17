package expressions.core;

import expressions.context.ContextFunctions;
import expressions.logic.LogicFunctions;
import expressions.seq.SequenceFunctions;
import model.Constant;
import model.Expression;
import model.Symbol;

import java.util.function.Function;

public class Contains {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if (symbols[0].toString().contains(symbols[1].toString())){
                    return LogicFunctions.True;
                } else {
                    return LogicFunctions.False;
                }
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
