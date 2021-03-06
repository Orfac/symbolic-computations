package services;

import expressions.context.ContextFunctions;
import expressions.logic.LogicFunctions;
import expressions.seq.SequenceFunctions;
import model.Constant;
import model.Expression;
import model.Symbol;
import model.StringSymbol;

import java.util.HashMap;
import java.util.function.Function;

public class EvaluationService {
    private HashMap<StringSymbol, Symbol> stringSymbolRules;
    private HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules;
    private HashMap<StringSymbol, Boolean> holdRestRules;
    private int maxEvaluationCount = 10000;

    public EvaluationService(HashMap<StringSymbol, Symbol> stringSymbolRules,
                             HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules, HashMap<StringSymbol, Boolean> holdRestRules) {
        this.stringSymbolRules = stringSymbolRules;
        this.expressionRules = expressionRules;
        this.holdRestRules = holdRestRules;
        this.expressionRules.put(ContextFunctions.Set, this::setRule);
        this.expressionRules.put(SequenceFunctions.List, this::listRule);

    }

    public Symbol evaluate(Symbol symbol){
        Symbol evaluatedSymbol = evaluateSymbol(symbol);
        int evaluationCount = 0;
        boolean compareResult = true;
        do {
            Symbol newSymbol = evaluateSymbol(evaluatedSymbol);
            compareResult = SymbolComparer.Compare(evaluatedSymbol,newSymbol);
            evaluatedSymbol = newSymbol;
            evaluationCount++;
        } while (evaluationCount < maxEvaluationCount && !compareResult);
        return evaluatedSymbol;
    }

    public Symbol evaluateSymbol(Symbol symbol){
        // Checking if symbol should be evaluated as Expression
        if (symbol instanceof Expression){
             return evaluateExpression((Expression) symbol);
        }

        // Checking if symbol is constant, so it is already evaluated
        if (symbol instanceof Constant){
            return symbol;
        }

        // Checking if symbol shouldn't be evaluated
        StringSymbol stringSymbol = (StringSymbol) symbol;
        if (!stringSymbolRules.containsKey(stringSymbol)){
            return stringSymbol;
        }

        // Evaluating symbol until no changes applied
        Symbol newValue = stringSymbolRules.get(stringSymbol);
        int evaluationCount = 0;
        while (evaluationCount < maxEvaluationCount
                && newValue != evaluateSymbol(newValue)){
            evaluationCount++;
            newValue = evaluateSymbol(newValue);
        }
        return newValue;

    }

    public Symbol evaluateExpression(Expression baseExpression){

        Symbol head = evaluate(baseExpression.getHead());
        if (!expressionRules.containsKey(head)){
            return baseExpression;
        }

        Symbol[] arguments;
        if (holdRestRules.containsKey(head)){
            arguments = this.evaluateHoldRestExpression(baseExpression, head);
        } else {
            arguments  = new Symbol[baseExpression.getArguments().length];
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = this.evaluate(baseExpression.getArguments()[i]);
            }
        }

        return expressionRules.get(head).apply(arguments);

    }

    private Symbol[] evaluateHoldRestExpression(Expression baseExpression, Symbol head) {
        Symbol[] updatedArguments = baseExpression.getArguments().clone();
        updatedArguments[0] = this.evaluate(baseExpression.getArguments()[0]);
        return updatedArguments;
    }

    private Symbol setRule(Symbol[] symbols) {
        stringSymbolRules.put((StringSymbol) symbols[0], symbols[1]);

        return new StringSymbol("Set");
    }

    private Symbol listRule(Symbol[] symbols) {
        for (int i = 0; i < symbols.length - 1; i++) {
            evaluateSymbol(symbols[i]);
        }
        return evaluateSymbol(symbols[symbols.length-1]);
    }
}
