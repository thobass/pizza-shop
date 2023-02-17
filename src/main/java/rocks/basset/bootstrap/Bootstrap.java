package rocks.basset.bootstrap;

import rocks.basset.domain.Ingredient;
import rocks.basset.domain.Pizza;
import rocks.basset.repositories.IngredientRepository;
import rocks.basset.repositories.PizzaRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Named
public class Bootstrap implements ServletContextListener {

    @Inject
    PizzaRepository pizzaRepository;

    @Inject
    IngredientRepository ingredientRepository;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        int count = pizzaRepository.findAll().size();

        if (count == 0){
            loadData();
        }
    }

    private void loadData(){
        Ingredient emmental = Ingredient.builder().name("Emmental").build();
        Ingredient chevre = Ingredient.builder().name("Chevre").build();
        Ingredient mozza = Ingredient.builder().name("Mozzarella").build();
        Ingredient jambon = Ingredient.builder().name("Jambon").build();
        Ingredient sauceTomate = Ingredient.builder().name("Sauce tomate").build();
        Ingredient champignon = Ingredient.builder().name("Champignons").build();
        Ingredient creme = Ingredient.builder().name("Creme").build();
        Ingredient origan = Ingredient.builder().name("Origan").build();
        Ingredient pate = Ingredient.builder().name("Pate a pizza").build();
        ingredientRepository.save(emmental);
        ingredientRepository.save(chevre);
        ingredientRepository.save(mozza);
        ingredientRepository.save(jambon);
        ingredientRepository.save(sauceTomate);
        ingredientRepository.save(champignon);
        ingredientRepository.save(creme);
        ingredientRepository.save(origan);
        ingredientRepository.save(pate);

        Pizza troisFromage = Pizza.builder().name("3 fromages").build();
        troisFromage.addIngredient(pate);
        troisFromage.addIngredient(creme);
        troisFromage.addIngredient(emmental);
        troisFromage.addIngredient(mozza);
        troisFromage.addIngredient(chevre);

        Pizza reine = Pizza.builder().name("Reine").build();
        reine.addIngredient(pate);
        reine.addIngredient(sauceTomate);
        reine.addIngredient(jambon);
        reine.addIngredient(champignon);
        reine.addIngredient(origan);

        pizzaRepository.save(troisFromage);
        pizzaRepository.save(reine);
    }
}
