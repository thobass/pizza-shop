package rocks.basset.api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import rocks.basset.api.mappers.PizzaMapper;
import rocks.basset.api.mappers.PizzaMapperImpl;
import rocks.basset.api.model.PizzaDto;
import rocks.basset.domain.Ingredient;
import rocks.basset.domain.Pizza;
import rocks.basset.repositories.PizzaRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PizzaServiceImplTest {

    @InjectMocks
    PizzaServiceImpl pizzaService;

    @Mock
    PizzaRepository pizzaRepository;

    @Mock
    PizzaMapperImpl pizzaMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById_shouldSucceed() {
        //GIVEN
        Pizza pizza = Pizza.builder().id(1L).build();
        given(pizzaRepository.findById(anyLong())).willReturn(pizza);
        given(pizzaMapper.pizzaToPizzaDto(any(Pizza.class))).willCallRealMethod();

        //WHEN
        PizzaDto findedPizza = pizzaService.findById(1L);

        //THEN
        assertNotNull(findedPizza);
        verify(pizzaRepository).findById(anyLong());
        verify(pizzaMapper).pizzaToPizzaDto(any(Pizza.class));
    }

    @Test
    void save() {
        //GIVEN
        PizzaDto pizzaToSave = PizzaDto.builder().name("Reine").build();
        Pizza savedPizza = Pizza.builder().id(1L).name("Reine").build();
        given(pizzaMapper.pizzaDtoToPizza(any(PizzaDto.class))).willCallRealMethod();
        given(pizzaMapper.pizzaToPizzaDto(any(Pizza.class))).willCallRealMethod();
        given(pizzaRepository.save(any(Pizza.class))).willReturn(savedPizza);

        //WHEN
        PizzaDto pizzaDtoSaved = pizzaService.save(pizzaToSave);

        //THEN
        assertNotNull(pizzaDtoSaved);
        verify(pizzaRepository).save(any(Pizza.class));
    }

    @Test
    void findAll() {
        //GIVEN
        Pizza pizza = Pizza.builder().id(1L).build();
        Pizza anotherPizza = Pizza.builder().id(2L).build();
        Set<Pizza> pizzas = new HashSet<>();
        pizzas.add(pizza);
        pizzas.add(anotherPizza);

        given(pizzaRepository.findAll()).willReturn(pizzas);
        given(pizzaMapper.pizzaToPizzaDto(any(Pizza.class))).willCallRealMethod();

        //WHEN
        Set<PizzaDto> findedPizzas = pizzaService.findAll();

        //THEN
        assertNotNull(pizzas);
        assertEquals(2, findedPizzas.size());
        verify(pizzaRepository).findAll();
        verify(pizzaMapper,times(2)).pizzaToPizzaDto(any(Pizza.class));
    }

    @Test
    void delete() {
        //GIVEN
        PizzaDto pizzaDtoToDelete = PizzaDto.builder().id(1L).name("Reine").build();
        given(pizzaMapper.pizzaDtoToPizza(any(PizzaDto.class))).willCallRealMethod();

        //WHEN
        pizzaService.delete(pizzaDtoToDelete);

        //THEN
        verify(pizzaRepository).delete(any(Pizza.class));
    }

    @Test
    void deleteById() {

        //WHEN
        pizzaService.deleteById(1L);

        //THEN
        verify(pizzaRepository).deleteById(anyLong());
    }
}