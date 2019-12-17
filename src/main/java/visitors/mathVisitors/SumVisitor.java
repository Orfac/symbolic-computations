package visitors.mathVisitors;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;

public interface SumVisitor {
    Symbol sumConstants(Constant constant1, Constant constant2);

    Symbol sumStringSymbols(StringSymbol stringSymbol1, StringSymbol stringSymbol2, Symbol[] symbols);

    Symbol sumExpressions(Expression expression1, Expression expression2, Symbol[] symbols);

    Symbol sumByDefault(Symbol symbol1, Symbol symbol2, Symbol[] symbols);
}
