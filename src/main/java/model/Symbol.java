package model;

import visitors.ConverterVisitor;
import visitors.ModelVisitor;
import visitors.mathVisitors.SumVisitor;

public abstract class Symbol {
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public abstract boolean instanceOf(ModelVisitor visitor, Class className);

    public abstract String convertToAsciiMath(ConverterVisitor visitor);

    public abstract Symbol sumSymbols(SumVisitor visitor, Constant constant, Symbol[] symbols);

    public abstract Symbol sumSymbols(SumVisitor visitor, StringSymbol stringSymbol, Symbol[] symbols);

    public abstract Symbol sumSymbols(SumVisitor visitor, Expression expression, Symbol[] symbols);
}
