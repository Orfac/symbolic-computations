import model.Constant;
import model.Expression;
import model.Symbol;
import model.StringSymbol;
import services.ConverterService;
import services.EvaluationService;
import services.InitializationService;
import services.FileWorkerService;
import visitors.Impl.ModelVisitorImpl;

import java.util.HashMap;
import java.util.function.Function;

public class Program {
    public static void main(final String[] args) {
//        InputDto inputDto = fileService.getInputDto(args[0]);
//
//        System.out.println(inputDto);

        ModelVisitorImpl modelVisitor = new ModelVisitorImpl();

        System.out.println(modelVisitor.instanceOf(new StringSymbol("list"), Constant.class));

        FileWorkerService fileService = new FileWorkerService();

        HashMap<StringSymbol,Symbol> symbolRules = new HashMap<>();
        HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules = InitializationService.generateExpressionMap();

        EvaluationService service = new EvaluationService( symbolRules,expressionRules);
        Symbol exp = service.evaluateExpression(new Expression(new StringSymbol("List"), new Symbol[]{
                new Expression(new StringSymbol("Set"),
                        new Symbol[]{
                                new StringSymbol("e"),
                                new Expression(new StringSymbol("Sum"),
                                        new Symbol[]{
                                                new StringSymbol("b"),
                                                new StringSymbol("b"),

                                                new Expression(new StringSymbol("Oppos"),
                                                        new Symbol[]{
                                                                new Expression(new StringSymbol("Mul"),
                                                                        new Symbol[]{
                                                                                new Expression(new StringSymbol("Div"),
                                                                                        new Symbol[]{new Constant(10), new Constant(5)}),
                                                                                new Constant(5),  new StringSymbol("a")})})}),
                        }),
                new StringSymbol("e")
        }));

        System.out.println(exp);

        HashMap convertDictionary  = new HashMap(){{
            put("Sum", "+");
            put("Mul", "*");
            put("Sub", "-");
            put("Div", "/");
        }};

        ConverterService converterService = new ConverterService(convertDictionary);

        System.out.println(converterService.convertSymbolsAsAsciiMath(exp));
        fileService.saveExpression(converterService.convertSymbolsAsAsciiMath(exp), args[1]);
    }
}
