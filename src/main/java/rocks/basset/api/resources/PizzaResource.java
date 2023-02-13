package rocks.basset.api.resources;


import rocks.basset.api.model.PizzaDto;
import rocks.basset.api.services.PizzaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pizza")
@Produces(APPLICATION_JSON)
public class PizzaResource {

    @Inject
    PizzaService pizzaService;

    @GET
    public Set<PizzaDto> getAllPizzas(){
        return pizzaService.findAll();
    }
    @GET
    @Path("/{id}")
    public PizzaDto getPizzaById(@PathParam("id") Long id){
        return pizzaService.findById(id);
    }
}
