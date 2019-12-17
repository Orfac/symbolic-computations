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
    public static StringSymbol LeftValue = new StringSymbol("LeftValue");
    public static StringSymbol RightValue = new StringSymbol("RightValue");
    public static StringSymbol CurrentOperation = new StringSymbol("CurrentOperation");


    private static Function<Symbol[], Symbol> expression =
            symbols -> {
                return new Expression(SequenceFunctions.List, new Symbol[]{
                        new Expression(ContextFunctions.Set, new Symbol[]{
                                LeftValue,
                                new Expression(ContextFunctions.Get, new Symbol[]{
                                        new Constant(0),
                                        symbols[0]
                                })
                        }),
                        new Expression(ContextFunctions.Set, new Symbol[]{
                                RightValue,
                                new Expression(ContextFunctions.Get, new Symbol[]{
                                        new Constant(1),
                                        symbols[0]
                                })
                        }),
                        new Expression(ContextFunctions.Set, new Symbol[]{
                                CurrentOperation,
                                new Expression(ContextFunctions.GetHead, new Symbol[]{
                                        symbols[0]
                                })
                        }),
                        new Expression(LogicFunctions.If, new Symbol[]{
                                new Expression(CoreFunctions.Contains, new Symbol[]{
                                        LeftValue,
                                        symbols[2]
                                }),
                                new Expression(MathFunction.Equality, new Symbol[]{
                                        LeftValue,
                                        new Expression(new Expression(CoreFunctions.GetOppositeOperation, new Symbol[]{
                                                CurrentOperation
                                        }), new Symbol[]{
                                                symbols[1],
                                                RightValue
                                        })
                                }),
                                new Expression(MathFunction.Equality, new Symbol[]{
                                        RightValue,
                                        new Expression(LogicFunctions.If, new Symbol[]{
                                                new Expression(CoreFunctions.Transitive, new Symbol[]{
                                                        CurrentOperation
                                                }),
                                                new Expression(CurrentOperation, new Symbol[]{
                                                        LeftValue,
                                                        symbols[1]
                                                }),
                                                new Expression(new Expression(CoreFunctions.GetOppositeOperation, new Symbol[]{
                                                        CurrentOperation
                                                }), new Symbol[]{
                                                        symbols[1],
                                                        LeftValue
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