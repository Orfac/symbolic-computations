package model;

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

}
