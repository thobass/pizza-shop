package rocks.basset.repositories;

import rocks.basset.domain.Pizza;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class PizzaRepositoryImpl implements PizzaRepository {

    @PersistenceContext(unitName = "pizza-domain")
    EntityManager entityManager;

    public Pizza findById(Long aLong) {
        return entityManager.find(Pizza.class, aLong);
    }

    @Transactional
    public Pizza save(Pizza object) {
        entityManager.persist(object);
        return object;
    }

    public Set<Pizza> findAll() {
        return new HashSet<>(entityManager.createNamedQuery(Pizza.FIND_ALL, Pizza.class).getResultList());
    }

    @Transactional
    public void delete(Pizza object) {
        entityManager.remove(object);
    }

    @Transactional
    public void deleteById(Long aLong) {
        Pizza pizzaToDelete = this.findById(aLong);
        this.delete(pizzaToDelete);
    }
}
