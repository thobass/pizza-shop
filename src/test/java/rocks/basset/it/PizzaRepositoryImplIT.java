package rocks.basset.it;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import javax.inject.Inject;

@ExtendWith(ArquillianExtension.class)
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
                // Enable CDI (Optional since Java EE 7.0)
                .addAsResource("META-INF/test-persistence.xml","META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    PizzaRepository pizzaRepository;

    @Inject
    PizzaService pizzaService;

    @Test
    public void test(){
        pizzaRepository.findAll();
        System.out.println("TADAAAAAA - J'ai été testé");
    }
}