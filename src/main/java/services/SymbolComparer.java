package services;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;

public class SymbolComparer {
    public static boolean Compare(Symbol symbol1, Symbol symbol2){
        if (symbol1 instanceof Expression){
            if (symbol2 instanceof Expression){
                Expression exp1 = (Expression) symbol1;
                Expression exp2 = (Expression) symbol2;
                return exp1.equals(exp2);
            } else {
                return false;
            }
        } else {
            if (symbol1 instanceof StringSymbol){
                if (symbol2 instanceof StringSymbol){
                    StringSymbol exp1 = (StringSymbol) symbol1;
                    StringSymbol exp2 = (StringSymbol) symbol2;
                    return exp1.equals(exp2);
                } else {
                    return false;
                }
            } else {
                if (symbol2 instanceof Constant){
                    Constant exp1 = (Constant) symbol1;
                    Constant exp2 = (Constant) symbol2;
                    return exp1.equals(exp2);
                } else {
                    return false;
                }
            }
        }
    }
}
