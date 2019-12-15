import model.Expression;
import model.Symbol;
import model.StringSymbol;
import services.EvaluationService;

import java.util.HashMap;
import java.util.function.Function;

public class Program {
    public static void main(final String[] args) {
//        FileWorkerService fileService = new FileWorkerService();
//        InputDto inputDto = fileService.getInputDto(args[0]);
//
//        System.out.println(inputDto);
        Function<Symbol[], Symbol> function = symbols -> new Expression(new StringSymbol("sum"), symbols);
        HashMap<StringSymbol,Symbol> symbolRules = new HashMap<StringSymbol,Symbol>();
        symbolRules.put(
                new StringSymbol("a"),
                new Expression(new StringSymbol("+"), new Symbol[] {new StringSymbol("b"), new StringSymbol("c")}));

        HashMap<StringSymbol, Function<Symbol[], Symbol>> expressionRules = new HashMap<>();
        expressionRules.put(new StringSymbol("+"), function);

        EvaluationService service = new EvaluationService( symbolRules,expressionRules);
        Symbol exp = service.EvaluateExpression(new Expression(new StringSymbol("+"), new Symbol[]{new StringSymbol("a")}));
        int a =5;
        System.out.println(exp);
    }
}
