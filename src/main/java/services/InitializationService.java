package services;

import expressions.context.ContextFunctions;
import expressions.context.Get;
import expressions.logic.*;
import expressions.math.Sum;
import expressions.seq.SequenceFunctions;
import expressions.seq.While;
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
        expressionMap.put(SequenceFunctions.While, While.getExpression());
        expressionMap.put(ContextFunctions.Get, Get.getExpression());
        return expressionMap;
    }
}
