package rocks.basset.api.services;

import rocks.basset.api.mappers.PizzaMapper;
import rocks.basset.api.model.PizzaDto;
import rocks.basset.domain.Pizza;
import rocks.basset.repositories.PizzaRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class PizzaServiceImpl implements PizzaService {

    @Inject
    PizzaRepository pizzaRepository;
    @Inject
    PizzaMapper mapper;

    @Override
    public PizzaDto findById(Long aLong) {
        return mapper.pizzaToPizzaDto(pizzaRepository.findById(aLong));
    }

    @Override
    public PizzaDto save(PizzaDto object) {
        Pizza pizza = mapper.pizzaDtoToPizza(object);
        return mapper.pizzaToPizzaDto(pizzaRepository.save(pizza));
    }

    @Override
    public Set<PizzaDto> findAll() {
        Set<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map(p -> mapper.pizzaToPizzaDto(p)).collect(Collectors.toSet());
    }

    @Override
    public void delete(PizzaDto object) {
        Pizza pizzaToDelete = mapper.pizzaDtoToPizza(object);
        pizzaRepository.delete(pizzaToDelete);
    }

    @Override
    public void deleteById(Long aLong) {
        pizzaRepository.deleteById(aLong);
    }
}
