package rocks.basset.it;

import org.dbunit.dataset.DataSetException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import rocks.basset.api.mappers.IngredientMapper;
import rocks.basset.api.mappers.IngredientMapperImpl;
import rocks.basset.api.mappers.PizzaMapper;
import rocks.basset.api.mappers.PizzaMapperImpl;
import rocks.basset.api.model.IngredientDto;
import rocks.basset.api.model.PizzaDto;
import rocks.basset.api.resources.PizzaResource;
import rocks.basset.api.services.CrudService;
import rocks.basset.api.services.PizzaService;
import rocks.basset.api.services.PizzaServiceImpl;
import rocks.basset.bootstrap.Bootstrap;
import rocks.basset.domain.Ingredient;
import rocks.basset.domain.Pizza;
import rocks.basset.repositories.*;
import rocks.basset.utils.BootstrapData;

import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class PizzaRepositoryImplIT {
//
    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"pizza.war")
                .addClass(Ingredient.class)
                .addClass(Pizza.class)
                .addClass(CrudRepository.class)
                .addClass(IngredientRepository.class)
                .addClass(PizzaRepository.class)
                .addClass(IngredientRepositoryImpl.class)
                .addClass(PizzaRepositoryImpl.class)
                .addClass(Bootstrap.class)
                .addClass(IngredientMapper.class)
                .addClass(IngredientMapperImpl.class)
                .addClass(PizzaMapper.class)
                .addClass(PizzaMapperImpl.class)
                .addClass(IngredientDto.class)
                .addClass(PizzaDto.class)
                .addClass(CrudService.class)
                .addClass(PizzaService.class)
                .addClass(PizzaServiceImpl.class)
                .addClass(PizzaResource.class)
                .addClass(BootstrapData.class)
                .addClass(DataSetException.class)
                // Enable CDI (Optional since Java EE 7.0)
                .addAsResource("META-INF/test-persistence.xml","META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    PizzaRepository pizzaRepository;

    @Inject
    Bootstrap bootstrap;

    @Inject
    PizzaService pizzaService;

    @Inject
    BootstrapData bootstrapData;


//    @BeforeEach
//    void setUp() {
//        bootstrap.contextInitialized(null);
//    }


    @Test
    public void name() throws MalformedURLException, DataSetException {
        bootstrapData.initDBData();
    }

    @Test
    @UsingDataSet("dataset.xml")
    public void findAll() {
        Set<Pizza> pizzaSet = pizzaRepository.findAll();
        assertEquals(2, pizzaSet.size());
    }

    @Test
    @UsingDataSet("dataset.xml")
    public void findById(){
        Pizza pizza = pizzaRepository.findById(1L);
        assertEquals("3 fromages", pizza.getName());
    }

    @Test
    @UsingDataSet("dataset.xml")
    public void deleteById(){
        int nbInitial = pizzaRepository.findAll().size();

        pizzaRepository.deleteById(1L);
        Pizza pizza = pizzaRepository.findById(1L);

        int nbFinal = pizzaRepository.findAll().size();

        assertEquals(nbInitial-1, nbFinal);
        assertNull(pizza);
    }

    @Test
    @UsingDataSet("dataset.xml")
    public void delete(){
        int nbInitial = pizzaRepository.findAll().size();

        pizzaRepository.deleteById(1L);
        Pizza pizza = pizzaRepository.findById(1L);

        int nbFinal = pizzaRepository.findAll().size();

        assertEquals(nbInitial-1, nbFinal);
        assertNull(pizza);
    }
}