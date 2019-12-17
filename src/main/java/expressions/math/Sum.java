package expressions.math;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.Impl.ModelVisitorImpl;
import visitors.mathVisitors.SumVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Sum implements SumVisitor {
    private static ModelVisitorImpl modelVisitor = new ModelVisitorImpl();

    private static Function<Symbol[], Symbol> expression =
            symbols -> {

                if (modelVisitor.instanceOf(symbols[0], Constant.class) && modelVisitor.instanceOf(symbols[1], Constant.class)){
                    double left = ((Constant) symbols[0]).getValue();
                    double right = ((Constant) symbols[1]).getValue();

                    return new Constant(left + right);
                } else if(modelVisitor.instanceOf(symbols[0], StringSymbol.class) && modelVisitor.instanceOf(symbols[1], StringSymbol.class) && symbols[0].equals(symbols[1])) {
                    List<Symbol> symbolList = new ArrayList<>();
                    symbolList.add(new Expression(new StringSymbol("Mul"), new Symbol[] { new Constant(2), symbols[0] }));
                    symbolList.addAll(Arrays.asList(symbols).subList(2, symbols.length));

                    return new Expression(new StringSymbol("Sum"), symbolList.toArray(new Symbol[0])) ;
                }
                return new Expression(new StringSymbol("Sum"), symbols );
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }

    @Override
    public Symbol sumConstants(Constant constant1, Constant constant2) {
        return new Constant(constant1.getValue() + constant2.getValue());
    }

    @Override
    public Symbol sumStringSymbols(StringSymbol stringSymbol1, StringSymbol stringSymbol2, Symbol[] symbols) {
        return sumEqualsSymbols(stringSymbol1, stringSymbol2, symbols);
    }

    @Override
    public Symbol sumExpressions(Expression expression1, Expression expression2, Symbol[] symbols) {
        return sumEqualsSymbols(expression1, expression2, symbols);
    }

    @Override
    public Symbol sumByDefault(Symbol symbol1, Symbol symbol2, Symbol[] symbols) {
        return new Expression(new StringSymbol("Sum"), symbols );
    }

    private Symbol sumEqualsSymbols(Symbol symbol1, Symbol symbol2, Symbol[] symbols){
        if(symbol1.equals(symbol2)) {
            List<Symbol> symbolList = new ArrayList<>();
            symbolList.add(new Expression(new StringSymbol("Mul"), new Symbol[] { new Constant(2), symbols[0] }));
            symbolList.addAll(Arrays.asList(symbols).subList(2, symbols.length));

            return new Expression(new StringSymbol("Sum"), symbolList.toArray(new Symbol[0]));
        }

        return sumByDefault(symbol1, symbol2, symbols);
    }
}
