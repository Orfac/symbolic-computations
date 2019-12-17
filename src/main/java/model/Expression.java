package model;

import visitors.ConverterVisitor;
import visitors.ModelVisitor;
import visitors.mathVisitors.SumVisitor;

import java.util.Arrays;

public class Expression extends Symbol  {
    private StringSymbol head;
    private Symbol[] arguments;

    public Expression(StringSymbol head){
        this.head = head;
    }

    public Expression(StringSymbol head, Symbol[] arguments){
        this.head = head;
        this.arguments = arguments;
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
