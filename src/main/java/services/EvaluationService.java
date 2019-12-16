package services;

import model.Expression;
import model.Symbol;
import model.StringSymbol;

import java.util.HashMap;
import java.util.function.Function;

public class EvaluationService {
    private HashMap<StringSymbol, Symbol> stringSymbolRules;
    private HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules;
    private int maxEvaluationCount = 10000;

    public EvaluationService(HashMap<StringSymbol, Symbol> stringSymbolRules,
                             HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules) {
        this.stringSymbolRules = stringSymbolRules;
        this.expressionRules = expressionRules;
        this.expressionRules.put(new StringSymbol("Set"), this::setRule);
        this.expressionRules.put(new StringSymbol("List"), this::listRule);
    }

    public Symbol EvaluateSymbol(Symbol symbol){
        // Checking if symbol should be evaluated as Expression
        if (symbol instanceof Expression){
            return EvaluateExpression((Expression) symbol);
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
                && newValue != EvaluateSymbol(newValue)){
            evaluationCount++;
            newValue = EvaluateSymbol(newValue);
        }
        return newValue;



    }

    public Symbol EvaluateExpression(Expression baseExpression){
        if (!expressionRules.containsKey(baseExpression.getHead())){
            return baseExpression;
        }

        Symbol[] evaluatedSymbols = new Symbol[baseExpression.getArguments().length];
        for (int i = 0; i < evaluatedSymbols.length; i++) {
            evaluatedSymbols[i] = this.EvaluateSymbol(baseExpression.getArguments()[i]);
        }

        return expressionRules.get(baseExpression.getHead()).apply(evaluatedSymbols);

    }

    private Symbol setRule(Symbol[] symbols) {
        stringSymbolRules.put((StringSymbol) symbols[0], symbols[1]);

        return new StringSymbol("set");
    }

    private Symbol listRule(Symbol[] symbols) {
        for (int i = 0; i < symbols.length - 1; i++) {
            EvaluateSymbol(symbols[i]);
        }
        return EvaluateSymbol(symbols[symbols.length-1]);
    }
}
