package services;

import expressions.context.GetHead;
import expressions.core.*;
import expressions.context.ContextFunctions;
import expressions.context.Get;
import expressions.logic.*;
import expressions.math.*;
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

        expressionMap.put(MathFunction.Equality, Equality.getExpression());
        expressionMap.put(MathFunction.Sum, Sum.getExpression());
        expressionMap.put(MathFunction.Mul, Mul.getExpression());
        expressionMap.put(MathFunction.Sub, Sub.getExpression());
        expressionMap.put(MathFunction.Div, Div.getExpression());

        expressionMap.put(CoreFunctions.GetOppositeOperation, OppositeOperationMath.getExpression());
        expressionMap.put(CoreFunctions.SingleExpress, SingleExpress.getExpression());
        expressionMap.put(CoreFunctions.Express, Express.getExpression());
        expressionMap.put(CoreFunctions.Contains, Contains.getExpression());
        expressionMap.put(CoreFunctions.Transitive, Transitive.getExpression());

        expressionMap.put(SequenceFunctions.While, While.getExpression());
        expressionMap.put(ContextFunctions.Get, Get.getExpression());
        expressionMap.put(ContextFunctions.GetHead, GetHead.getExpression());
        return expressionMap;
    }
}
