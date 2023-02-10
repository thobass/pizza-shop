package rocks.basset.api.mappers;

import org.mapstruct.Mapper;
import rocks.basset.api.model.PizzaDto;
import rocks.basset.domain.Pizza;

@Mapper
public interface PizzaMapper {
    Pizza pizzaDtoToPizza(PizzaDto pizzaDto);
    PizzaDto pizzaToPizzaDto(Pizza pizza);
}
