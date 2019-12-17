package visitors;

import model.Constant;
import model.Expression;
import model.StringSymbol;

public interface ModelVisitor {
    boolean instanceofConstant(Constant constant, Class className);

    boolean instanceofExpression(Expression expression, Class className);

    boolean instanceofStringSymbol(StringSymbol stringSymbol, Class className);
}
