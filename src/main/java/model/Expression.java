package model;

import visitors.ConverterVisitor;
import visitors.ModelVisitor;
import visitors.mathVisitors.SumVisitor;

import java.util.Arrays;
import java.util.Objects;

public class Expression extends Symbol  {
    private Symbol head;
    private Symbol[] arguments;

    public Expression(Symbol head){
        this.head = head;
    }

    public Expression(Symbol head, Symbol[] arguments){
        this.head = head;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expression)) return false;

        Expression that = (Expression) o;
        return Objects.equals(getHead(), that.getHead()) &&
                Arrays.equals(getArguments(), that.getArguments());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getHead());
        result = 31 * result + Arrays.hashCode(getArguments());
        return result;
    }

    public Symbol getHead() {
        return head;
    }

    public Symbol[] getArguments() {
        return arguments;
    }

    @Override
    public String toString(){
        return head.toString() + Arrays.toString(arguments);
    }

    @Override
    public boolean instanceOf(ModelVisitor visitor, Class className) { return visitor.instanceofExpression(this, className); }

    @Override
    public String convertToAsciiMath(ConverterVisitor visitor) { return visitor.convertExpression(this); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, Constant constant, Symbol[] symbols) { return visitor.sumByDefault(this, constant, symbols); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, StringSymbol stringSymbol, Symbol[] symbols) { return visitor.sumByDefault(this, stringSymbol, symbols); }

    @Override
    public Symbol sumSymbols(SumVisitor visitor, Expression expression, Symbol[] symbols) { return visitor.sumExpressions(this, expression, symbols); }
}
