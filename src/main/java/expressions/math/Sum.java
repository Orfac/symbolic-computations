package expressions.math;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class Sum {
    private Function<Symbol[], Symbol> expression =
            symbols -> {
                if (symbols[0] instanceof Constant && symbols[1] instanceof Constant){
                    double left = ((Constant) symbols[0]).getValue();
                    double right = ((Constant) symbols[1]).getValue();
                    return new Constant(left + right);
                }
                return new Expression(new StringSymbol("Sum"), symbols );
            };

    public Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
