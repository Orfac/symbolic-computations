package model;

import lombok.Data;
import visitors.ConverterVisitor;
import visitors.ModelVisitor;
import visitors.mathVisitors.SumVisitor;

import java.util.Objects;

@Data
public class StringSymbol extends Symbol {
    private String value;

    public StringSymbol(String value){
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringSymbol)) return false;

        StringSymbol stringSymbol = (StringSymbol) o;
        return getValue().equals(stringSymbol.getValue());
    }

    @Override
    public boolean instanceOf(ModelVisitor visitor, Class className) { return visitor.instanceofStringSymbol(this, className); }

    @Override
    public String convertToAsciiMath(ConverterVisitor visitor) { return visitor.convertStringSymbol(this); }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString(){
        return this.value;
    }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, Constant constant, Symbol[] symbols) { return visitor.sumByDefault(this, constant, symbols); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, StringSymbol stringSymbol, Symbol[] symbols) { return visitor.sumStringSymbols(this, stringSymbol, symbols); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, Expression expression, Symbol[] symbols) { return visitor.sumByDefault(this, expression, symbols); }
}
