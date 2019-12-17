package visitors;

import model.Constant;
import model.Expression;
import model.StringSymbol;

public interface ConverterVisitor {
    String convertExpression(Expression expression);

    String convertConstant(Constant constant);

    String convertStringSymbol(StringSymbol stringSymbol);
}
