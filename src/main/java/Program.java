import model.Constant;
import model.Expression;
import model.Symbol;
import model.StringSymbol;
import services.ConverterService;
import services.EvaluationService;
import services.InitializationService;
import services.FileWorkerService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Program {
    public static void main(final String[] args) {
//        InputDto inputDto = fileService.getInputDto(args[0]);
//
//        System.out.println(inputDto);

        FileWorkerService fileService = new FileWorkerService();

        Function<Symbol[], Symbol> function = symbols -> new Expression(new StringSymbol("Sum"), symbols);

        HashMap<StringSymbol,Symbol> symbolRules = new HashMap<>();
        symbolRules.put(
                new StringSymbol("a"),
                new Expression(new StringSymbol("Sum"), new Symbol[] {new StringSymbol("b"), new StringSymbol("c")}));

        HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules = InitializationService.generateExpressionMap();

        EvaluationService service = new EvaluationService( symbolRules,expressionRules);
        Symbol exp = service.evaluateExpression(new Expression(new StringSymbol("List"), new Symbol[]{
                new Expression(new StringSymbol("Set"),
                        new Symbol[]{
                                new StringSymbol("e"),
                                new Expression(new StringSymbol("Sum"),
                                        new Symbol[]{
                                                new StringSymbol("b"),
                                                new StringSymbol("c"),
                                                new Expression(new StringSymbol("Sum"),
                                                        new Symbol[]{new Constant(5),  new Constant(6)})}),
                        }),
                new StringSymbol("e")
        }));

        System.out.println(exp);

        HashMap convertDictionary  = new HashMap(){{
            put("Sum", "+");
        }};

        ConverterService converterService = new ConverterService(convertDictionary);

        System.out.println(converterService.convertSymbolsAsAsciiMath(exp));
        fileService.saveExpression(converterService.convertSymbolsAsAsciiMath(exp), args[1]);
    }
}
