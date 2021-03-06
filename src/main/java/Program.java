import model.Symbol;
import model.StringSymbol;
import services.ConverterService;
import services.EvaluationService;
import services.InitializationService;
import services.FileWorkerService;

import java.util.HashMap;
import java.util.function.Function;

public class Program {
    private static HashMap convertDictionary  = new HashMap(){{
        put("Sum", "+");
        put("Mul", "*");
        put("Sub", "-");
        put("Div", "/");
        put("Equality", "=");
    }};

    public static void main(final String[] args) {
        HashMap<StringSymbol,Symbol> symbolRules = new HashMap<>();
        HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules = InitializationService.generateExpressionMap();
        HashMap<StringSymbol, Boolean> holdRestRules = InitializationService.generateHoldRestMap();

        ConverterService converterService = new ConverterService(convertDictionary);
        FileWorkerService fileService = new FileWorkerService();
        EvaluationService service = new EvaluationService(symbolRules, expressionRules, holdRestRules);

        Symbol symbol = converterService.convertFromDtoToModel(fileService.getInputDto(args[0]));

        Symbol exp = service.evaluate(symbol);
        System.out.println(exp);

        if(exp != null) {
            System.out.println(converterService.convertSymbolsAsAsciiMath(exp));
            fileService.saveExpression(converterService.convertSymbolsAsAsciiMath(exp), args[1]);
        } else {
            System.out.println(converterService.convertSymbolsAsAsciiMath(symbol));
            fileService.saveExpression(converterService.convertSymbolsAsAsciiMath(symbol), args[1]);
        }
    }
}
