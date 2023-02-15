package rocks.basset.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name= "findAllPizza", query="select p from Pizza p")
})
public class Pizza implements Serializable {
    public static final String FIND_ALL = "findAllPizza";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    public void addIngredient(Ingredient ingredient){
        if(this.ingredients == null){
            this.ingredients = new HashSet<>();
        }
        this.ingredients.add(ingredient);
    }
}
