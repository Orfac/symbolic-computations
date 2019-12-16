package services;

import model.Expression;
import model.Symbol;

import java.util.HashMap;

public class ConverterService {
    private HashMap<String, String> convertDictionary;

    public ConverterService(HashMap<String, String> convertDictionary){
        this.convertDictionary = convertDictionary;
    }

    public String convertSymbolsAsAsciiMath(Symbol symbol){
        String result = "";

        if(symbol instanceof Expression) {
            Expression expression = (Expression)symbol;
            result = String.format("(%s", convertSymbolsAsAsciiMath(expression.getArguments()[0]));
            String operationName = convertDictionary.getOrDefault(expression.getHead().toString(), "");

            for(int i = 1; i < expression.getArguments().length; i++) {

                result = String.format("%s %s %s",
                        result,
                        operationName,
                        convertSymbolsAsAsciiMath(expression.getArguments()[i]));
            }

            result += ")";
        } else {
            result = symbol.toString();
        }

        return result;
    }
}
