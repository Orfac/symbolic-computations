package model;

import visitors.ConverterVisitor;
import visitors.ModelVisitor;
import visitors.mathVisitors.SumVisitor;

import java.util.Objects;

public class Constant extends Symbol {
    private double value;

    public Constant(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Constant)) return false;

        Constant constant = (Constant) o;
        return Double.compare(constant.getValue(), getValue()) == 0;
    }

    @Override
    public boolean instanceOf(ModelVisitor visitor, Class className) { return visitor.instanceofConstant(this, className); }

    @Override
    public String convertToAsciiMath(ConverterVisitor visitor) { return visitor.convertConstant(this); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, Constant constant, Symbol[] symbols) { return visitor.sumConstants(this, constant); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, StringSymbol stringSymbol, Symbol[] symbols) { return visitor.sumByDefault(this, stringSymbol, symbols); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, Expression expression, Symbol[] symbols) { return visitor.sumByDefault(this, expression, symbols); }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }
}
