package dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ArgumentDto {
    private ExpressionDto expression;

    private SymbolDto symbol;
}
