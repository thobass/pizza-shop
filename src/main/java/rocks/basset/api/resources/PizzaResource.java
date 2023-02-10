package rocks.basset.api.resources;


import rocks.basset.api.model.PizzaDto;
import rocks.basset.api.services.PizzaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Set;

@Path("/pizza")
public class PizzaResource {

    @Inject
    PizzaService pizzaService;

    @GET
    public Set<PizzaDto> getAllPizzas(){
        return pizzaService.findAll();
    }
}
