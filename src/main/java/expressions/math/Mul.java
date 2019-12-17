package expressions.math;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.Impl.ModelVisitorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Mul {
    private static ModelVisitorImpl modelVisitor = new ModelVisitorImpl();

    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                if (modelVisitor.instanceOf(symbols[0], Constant.class) && modelVisitor.instanceOf(symbols[1], Constant.class)){
                    double left = ((Constant) symbols[0]).getValue();
                    double right = ((Constant) symbols[1]).getValue();

                    if(symbols.length > 2){
                    List<Symbol> symbolList = new ArrayList(){{
                        add(new Constant(left * right));
                        addAll(Arrays.asList(symbols).subList(2, symbols.length));
                    }};

                        return new Expression(new StringSymbol("Mul"), symbolList.toArray(new Symbol[0]));
                    }

                    return new Constant(left * right);
                }

                return new Expression(new StringSymbol("Mul"), symbols );
            };

    public static Function<Symbol[], Symbol> getExpression() { return expression; }
}
