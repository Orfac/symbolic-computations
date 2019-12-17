package expressions.core;

import expressions.context.ContextFunctions;
import expressions.logic.LogicFunctions;
import model.Constant;
import model.Expression;
import model.Symbol;

import java.util.function.Function;

public class Express {
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                return new Expression(LogicFunctions.If, new Symbol[]{
                        new Expression(LogicFunctions.Equal, new Symbol[]{
                                new Expression(ContextFunctions.Get, new Symbol[]{
                                        new Constant(0),
                                        symbols[0]
                                }),
                                symbols[1]
                        }),
                        symbols[0],
                        new Expression(CoreFunctions.Express, new Symbol[]{
                                new Expression(CoreFunctions.SingleExpress, new Symbol[]{
                                        new Expression(ContextFunctions.Get, new Symbol[]{
                                                new Constant(0),
                                                symbols[0]
                                        }),
                                        new Expression(ContextFunctions.Get, new Symbol[]{
                                                new Constant(1),
                                                symbols[0]
                                        }),
                                        symbols[1]
                                }),
                                symbols[1]
                        })
                });
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
