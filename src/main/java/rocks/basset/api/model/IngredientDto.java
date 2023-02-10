package rocks.basset.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDto {
    private Long id;
    private String name;
}
