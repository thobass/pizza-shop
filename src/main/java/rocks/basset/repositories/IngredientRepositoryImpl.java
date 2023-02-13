package rocks.basset.repositories;

import rocks.basset.domain.Ingredient;
import rocks.basset.domain.Pizza;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Named
public class IngredientRepositoryImpl implements IngredientRepository{

    @PersistenceContext(unitName = "pizza-domain")
    EntityManager entityManager;

    @Override
    public Ingredient findById(Long aLong) {
        return entityManager.find(Ingredient.class, aLong);
    }

    @Transactional
    @Override
    public Ingredient save(Ingredient object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public Set<Ingredient> findAll() {
        return new HashSet<>(entityManager.createNamedQuery(Ingredient.FIND_ALL, Ingredient.class).getResultList());
    }

    @Transactional
    @Override
    public void delete(Ingredient object) {
        entityManager.remove(object);
    }

    @Transactional
    @Override
    public void deleteById(Long aLong) {
        Ingredient ingredient = this.findById(aLong);
        this.delete(ingredient);
    }
}
