package services;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.Impl.ModelVisitorImpl;
import visitors.ModelVisitor;

public class SymbolComparer {
    private static ModelVisitorImpl modelVisitor = new ModelVisitorImpl();

    public static boolean Compare(Symbol symbol1, Symbol symbol2){
        if (symbol1.instanceOf(modelVisitor,Expression.class)){
            if (symbol2.instanceOf(modelVisitor,Expression.class)){
                Expression exp1 = (Expression) symbol1;
                Expression exp2 = (Expression) symbol2;
                return exp1.equals(exp2);
            }
            return false;
        }

        if (symbol1.instanceOf(modelVisitor,StringSymbol.class)){
            if (symbol1.instanceOf(modelVisitor,StringSymbol.class)){
                StringSymbol stringSymbol1 = (StringSymbol) symbol1;
                StringSymbol stringSymbol2 = (StringSymbol) symbol2;
                return stringSymbol1.equals(stringSymbol2);
            }
            return false;
        }

        if (symbol1.instanceOf(modelVisitor,Constant.class)){
            Constant constant1 = (Constant) symbol1;
            Constant constant2 = (Constant) symbol2;
            return constant1.equals(constant2);
        }
        return false;


    }
}
