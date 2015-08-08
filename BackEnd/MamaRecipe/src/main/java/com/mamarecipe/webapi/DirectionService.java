package com.mamarecipe.webapi;

import com.mamarecipe.database.dba.DirectionDBA;
import com.mamarecipe.database.dba.IngredientDBA;
import com.mamarecipe.database.idba.IDirectionDBA;
import com.mamarecipe.database.idba.IIngredientDBA;
import com.mamarecipe.model.DirectionPO;
import com.mamarecipe.model.IngredientPO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/direction")
public class DirectionService {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/addone")
    public Response addDirection(DirectionPO direction) {
        IDirectionDBA directDBA = new DirectionDBA();
        directDBA.add(direction);
        return Response.ok().build();
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/dishID/{dishID}")
    public List<DirectionPO> getDirectByDID(@PathParam("dishID")String dishID){
        IDirectionDBA directDBA = new DirectionDBA();
        List<DirectionPO> direction = directDBA.getByRecipeID(Long.parseLong(dishID));
        return direction;
    }
}
