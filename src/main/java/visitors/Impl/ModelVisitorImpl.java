package visitors.Impl;

import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;
import visitors.ModelVisitor;

public class ModelVisitorImpl implements ModelVisitor {
    public boolean instanceOf(Symbol symbol, Class className) { return symbol.instanceOf(this, className); }

    @Override
    public boolean instanceofConstant(Constant constant, Class className) { return className.equals(constant.getClass()); }

    @Override
    public boolean instanceofExpression(Expression expression, Class className) { return className.equals(expression.getClass()); }

    @Override
    public boolean instanceofStringSymbol(StringSymbol stringSymbol, Class className) { return className.equals(stringSymbol.getClass()); }
}
