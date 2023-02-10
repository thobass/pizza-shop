package rocks.basset.api.model;


import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PizzaDto {
    private Long id;
    private String name;
    private Set<IngredientDto> ingredients;
}
