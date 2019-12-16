package services;

import model.Expression;
import model.Symbol;
import model.StringSymbol;

import java.util.HashMap;
import java.util.function.Function;

public class EvaluationService {
    private HashMap<StringSymbol, Symbol> stringSymbolRules;
    private HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules;

    public EvaluationService(HashMap<StringSymbol, Symbol> stringSymbolRules,
                             HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules) {
        this.stringSymbolRules = stringSymbolRules;
        this.expressionRules = expressionRules;
        this.expressionRules.put(new StringSymbol("Set"), this::setRule);
        this.expressionRules.put(new StringSymbol("List"), this::listRule);
    }

    public Symbol EvaluateSymbol(Symbol symbol){
        if (symbol instanceof Expression){
            return EvaluateExpression((Expression) symbol);
        } else {
            StringSymbol stringSymbol = (StringSymbol) symbol;

            if (stringSymbolRules.containsKey(stringSymbol)){
                Symbol newValue = stringSymbolRules.get(stringSymbol);
                while (newValue != EvaluateSymbol(newValue)){
                    newValue = EvaluateSymbol(newValue);
                }
                return newValue;
            } else {
                return stringSymbol;
            }

        }
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
