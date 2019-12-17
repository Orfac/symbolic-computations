package expressions.core;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.Impl.ModelVisitorImpl;
import visitors.ModelVisitor;

import java.util.HashMap;
import java.util.function.Function;

public class OppositeOperationMath {
    private static HashMap<String, String>  oppositeOperationMathMap = new HashMap() {{
        put("Sum", "Sub");
        put("Sub", "Sum");
        put("Mul", "Div");
        put("Div", "Mul");
    }};

    private static ModelVisitorImpl modelVisitor = new ModelVisitorImpl();

    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if(symbols[0].instanceOf(modelVisitor, Expression.class)){
                    Expression expression = (Expression) symbols[0];

                    return new Expression(
                            new StringSymbol(
                                    oppositeOperationMathMap
                                            .getOrDefault(
                                                    expression.getHead().toString(),
                                                    expression.getHead().toString())), expression.getArguments());
                }

                return symbols[0];
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
