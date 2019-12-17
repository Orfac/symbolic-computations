package services;

import dto.ArgumentDto;
import dto.ExpressionDto;
import dto.InputDto;
import dto.SymbolDto;
import expressions.core.CoreFunctions;
import expressions.core.Express;
import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.ConverterVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConverterService implements ConverterVisitor {
    private HashMap<String, String> convertDictionary;

    public ConverterService(HashMap<String, String> convertDictionary)
    {
        this.convertDictionary = convertDictionary;
    }

    public String convertSymbolsAsAsciiMath(Symbol symbol){
        return  symbol.convertToAsciiMath(this);
    }

    public Symbol convertFromDtoToModel(InputDto inputDto){
        return new Expression(CoreFunctions.Express, new Symbol[] {
            new StringSymbol(inputDto.getVariable()), convertExpressionDtoToSymbol(inputDto.getExpression())
        });
    }

    private Symbol convertExpressionDtoToSymbol(ExpressionDto expressionDto) {
        return new Expression(new StringSymbol(expressionDto.getHead().getSymbol()),
            convertArgumentsDtoToSymbols(expressionDto.getArguments()));
    }

    private Symbol[] convertArgumentsDtoToSymbols(List<ArgumentDto> argumentsDto) {
        List<Symbol> symbols = new ArrayList();

        for (ArgumentDto arg : argumentsDto){
            if(arg.getExpression() != null){
                symbols.add(convertExpressionDtoToSymbol(arg.getExpression()));
            } else {
                symbols.add(convertSymbolDtoToTerminalSymbol(arg.getSymbol()));
            }
        }

        return symbols.toArray(new Symbol[0]);
    }

    private Symbol convertSymbolDtoToTerminalSymbol(SymbolDto symbolDto){
        switch (symbolDto.getType()){
            case "constant" : {
                return new Constant(Double.parseDouble(symbolDto.getValue()));
            }
            case "symbol" : {
                return new StringSymbol(symbolDto.getValue());
            }
            default: {
                return new StringSymbol(symbolDto.getValue());
            }
        }
    }

    @Override
    public String convertExpression(Expression expression) {
        if(!expression.getHead().toString().equals("Equality")){
            String result = String.format("(%s", convertSymbolsAsAsciiMath(expression.getArguments()[0]));

            return String.format("%s)", convertArguments(result, expression.getArguments(), expression.getHead()));
        } else {
            return convertArguments(expression.getArguments()[0].toString(), expression.getArguments(), expression.getHead());
        }
    }

    private String convertArguments(String result, Symbol[] symbols, Symbol head){
        String operationName = convertDictionary.getOrDefault(head.toString(), "");
        for(int i = 1; i < symbols.length; i++) {

            result = String.format("%s %s %s",
                    result,
                    operationName,
                    convertSymbolsAsAsciiMath(symbols[i]));
        }

        return result;
    }

    @Override
    public String convertConstant(Constant constant) { return constant.toString(); }

    @Override
    public String convertStringSymbol(StringSymbol stringSymbol) { return stringSymbol.toString(); }
}
