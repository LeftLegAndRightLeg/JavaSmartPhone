package com.mamarecipe.webapi;

import com.mamarecipe.database.dba.IngredientDBA;
import com.mamarecipe.database.dba.RecipeDBA;
import com.mamarecipe.database.idba.IIngredientDBA;
import com.mamarecipe.database.idba.IRecipeDBA;
import com.mamarecipe.model.IngredientPO;
import com.mamarecipe.model.RecipePO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/ingre")
public class IngredientService {

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
    public Response addIngredient(IngredientPO ingredient) {
        IIngredientDBA ingreDBA = new IngredientDBA();
        ingreDBA.add(ingredient);
        return Response.ok().build();
    }
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/addmore")
    public Response addIngredient(List<IngredientPO> ingreList) {
        IIngredientDBA ingreDBA = new IngredientDBA();
        ingreList.stream().forEach((i)->ingreDBA.add(i));
        return Response.ok().build();
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/dishID/{dishID}")
    public List<IngredientPO> getIngredientByDID(@PathParam("dishID")String dishID){
        IIngredientDBA ingreDBA = new IngredientDBA();
        List<IngredientPO> ipoList = ingreDBA.getByRecipeID((Long.parseLong(dishID)));
        return ipoList;
    }
}
