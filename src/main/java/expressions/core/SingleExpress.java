package expressions.core;

import expressions.context.ContextFunctions;
import expressions.context.Get;
import expressions.logic.LogicFunctions;
import expressions.math.MathFunction;
import expressions.seq.SequenceFunctions;
import model.Constant;
import model.Expression;
import model.StringSymbol;
import model.Symbol;

import java.util.function.Function;

public class SingleExpress {

    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                return new Expression(SequenceFunctions.List, new Symbol[]{
                        new Expression(LogicFunctions.If, new Symbol[]{
                                new Expression(CoreFunctions.Contains, new Symbol[]{
                                        new Expression(ContextFunctions.Get, new Symbol[]{
                                                new Constant(0),
                                                symbols[0]
                                        }),
                                        symbols[2]
                                }),
                                new Expression(MathFunction.Equality, new Symbol[]{
                                        new Expression(ContextFunctions.Get, new Symbol[]{
                                                new Constant(0),
                                                symbols[0]
                                        }),
                                        new Expression(new Expression(CoreFunctions.GetOppositeOperation, new Symbol[]{
                                                new Expression(ContextFunctions.GetHead, new Symbol[]{
                                        symbols[0]
                                })
                                        }), new Symbol[]{
                                                symbols[1],
                                                new Expression(ContextFunctions.Get, new Symbol[]{
                                        new Constant(1),
                                        symbols[0]
                                })
                                        })
                                }),
                                new Expression(MathFunction.Equality, new Symbol[]{
                                        new Expression(ContextFunctions.Get, new Symbol[]{
                                        new Constant(1),
                                        symbols[0]
                                }),
                                        new Expression(LogicFunctions.If, new Symbol[]{
                                                new Expression(CoreFunctions.Transitive, new Symbol[]{
                                                        new Expression(ContextFunctions.GetHead, new Symbol[]{
                                        symbols[0]
                                })
                                                }),
                                                new Expression(new Expression(ContextFunctions.GetHead, new Symbol[]{
                                        symbols[0]
                                }), new Symbol[]{
                                                        new Expression(ContextFunctions.Get, new Symbol[]{
                                                                new Constant(0),
                                                                symbols[0]
                                                        }),
                                                        symbols[1]
                                                }),
                                                new Expression(new Expression(CoreFunctions.GetOppositeOperation, new Symbol[]{
                                                        new Expression(ContextFunctions.GetHead, new Symbol[]{
                                        symbols[0]
                                })
                                                }), new Symbol[]{
                                                        symbols[1],
                                                        new Expression(ContextFunctions.Get, new Symbol[]{
                                                                new Constant(0),
                                                                symbols[0]
                                                        })
                                                })
                                        })
                                })
                        })
                });
            };

    public static Function<Symbol[], Symbol> getExpression() {
        return expression;
    }
}
