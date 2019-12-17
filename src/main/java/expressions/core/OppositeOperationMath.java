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
                if(symbols[0].instanceOf(modelVisitor, StringSymbol.class)){
                    StringSymbol stringSymbol = (StringSymbol) symbols[0];
                    return new StringSymbol(
                            oppositeOperationMathMap.getOrDefault(
                                    stringSymbol.getValue(),
                                    stringSymbol.getValue()
                            )
                    );

                }

                return symbols[0];
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
