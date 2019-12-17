package services;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.ConverterVisitor;

import java.util.HashMap;

public class ConverterService implements ConverterVisitor {
    private HashMap<String, String> convertDictionary;

    public ConverterService(HashMap<String, String> convertDictionary)
    {
        this.convertDictionary = convertDictionary;
    }

    public String convertSymbolsAsAsciiMath(Symbol symbol){
        return  symbol.convertToAsciiMath(this);
    }

    @Override
    public String convertExpression(Expression expression) {
        String result = String.format("(%s", convertSymbolsAsAsciiMath(expression.getArguments()[0]));
        String operationName = convertDictionary.getOrDefault(expression.getHead().toString(), "");

        for(int i = 1; i < expression.getArguments().length; i++) {

            result = String.format("%s %s %s",
                    result,
                    operationName,
                    convertSymbolsAsAsciiMath(expression.getArguments()[i]));
        }

        result += ")";

        return result;
    }

    @Override
    public String convertConstant(Constant constant) { return constant.toString(); }

    @Override
    public String convertStringSymbol(StringSymbol stringSymbol) { return stringSymbol.toString(); }
}
