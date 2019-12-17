package expressions.math;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.Impl.ModelVisitorImpl;

import java.util.function.Function;

public class Sub {
    private static ModelVisitorImpl modelVisitor = new ModelVisitorImpl();

    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if (modelVisitor.instanceOf(symbols[0], Constant.class) && modelVisitor.instanceOf(symbols[1], Constant.class)) {
                    return new Constant(((Constant) symbols[0]).getValue() - ((Constant) symbols[1]).getValue());
                }

                return new Expression(new StringSymbol("Sub"), symbols );
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
