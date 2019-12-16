package services;

import expressions.logic.*;
import expressions.math.Sum;
import model.StringSymbol;
import model.Symbol;

import java.util.HashMap;
import java.util.function.Function;

public class InitializationService {
    public static HashMap<StringSymbol, Function<Symbol[], Symbol>> generateExpressionMap(){
        HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionMap = new HashMap<>();
        expressionMap.put(new StringSymbol("Sum"), Sum.getExpression());
        expressionMap.put(LogicFunctions.If, If.getExpression());
        expressionMap.put(LogicFunctions.And, And.getExpression());
        expressionMap.put(LogicFunctions.Or, Or.getExpression());
        expressionMap.put(LogicFunctions.Not, Not.getExpression());
        expressionMap.put(LogicFunctions.Equal, Equal.getExpression());
        return expressionMap;
    }
}
