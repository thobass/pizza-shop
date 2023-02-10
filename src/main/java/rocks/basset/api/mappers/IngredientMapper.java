package rocks.basset.api.mappers;

import org.mapstruct.Mapper;
import rocks.basset.api.model.IngredientDto;
import rocks.basset.domain.Ingredient;

@Mapper
public interface IngredientMapper {
    Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto);
    IngredientDto ingredientToIngredientDto(Ingredient ingredient);
}
