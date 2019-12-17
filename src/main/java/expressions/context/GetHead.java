package expressions.context;

import expressions.core.CoreFunctions;
import expressions.logic.LogicFunctions;
import model.Expression;
import model.Symbol;
import visitors.Impl.ModelVisitorImpl;

import java.util.function.Function;

public class GetHead {
    private static ModelVisitorImpl modelVisitor = new ModelVisitorImpl();
    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if (symbols[0].instanceOf(modelVisitor, Expression.class)){
                    return ((Expression)symbols[0]).getHead();
                } else {
                    return symbols[0];
                }
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
