package dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SymbolDto {
    private String value;

    private String type;
}
