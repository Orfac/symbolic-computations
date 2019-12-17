import expressions.core.CoreFunctions;
import expressions.math.MathFunction;
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
        Symbol exp = service.evaluate(new Expression(CoreFunctions.SingleExpress, new Symbol[]{
                new Expression(MathFunction.Sum, new Symbol[]{
                        new StringSymbol("X"),
                        new Constant(2)
                }),
                new Constant(5),
                new StringSymbol("X")
        }));
        Symbol exp2 = service.evaluateExpression((Expression)exp);
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
