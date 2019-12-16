package services;

import expressions.math.Sum;
import model.StringSymbol;
import model.Symbol;

import java.util.HashMap;
import java.util.function.Function;

public class InitializationService {
    public static HashMap<StringSymbol, Function<Symbol[], Symbol>> generateExpressionMap(){
        HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionMap = new HashMap<>();
        expressionMap.put(new StringSymbol("Sum"), Sum.getExpression());
        return expressionMap;
    }
}
