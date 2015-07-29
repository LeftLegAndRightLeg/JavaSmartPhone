package com.mamarecipe.webapi;

import com.mamarecipe.database.dba.RecipeDBA;
import com.mamarecipe.database.idba.IRecipeDBA;
import com.mamarecipe.model.RecipePO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/recipe")
public class RecipeService {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/add")
    public Response addRecipe(RecipePO recipe) {
        IRecipeDBA recipeDBA = new RecipeDBA();
        long dishID = recipeDBA.add(recipe);
        recipe.setDishID(dishID);
        if(dishID>=0)
            return Response.ok(recipe).build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/dishid/{dishID}")
    public Response getRecipeByID(@PathParam("dishID") String dishID) {
        IRecipeDBA recipeDBA = new RecipeDBA();
        RecipePO recipePO = recipeDBA.getByRecipeID(Long.parseLong(dishID));
        if(recipePO!=null){
            return Response.ok(recipePO).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/categoryid/{CategoryID}")
    public List<RecipePO> getRecipeByCID(@PathParam("CategoryID")String CategoryID){
        IRecipeDBA recipeDBA = new RecipeDBA();
        List<RecipePO> rpoList = recipeDBA.getByCategoryID(Long.parseLong(CategoryID));
        return rpoList;
/*
        if(rpoList!=null){
            return Response.ok(rpoList).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
*/
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/userid/{userID}")
    public List<RecipePO> getRecipeByUID(@PathParam("userID")String userID){
        IRecipeDBA recipeDBA = new RecipeDBA();
        List<RecipePO> rpoList = recipeDBA.getByUserID(Long.parseLong(userID));
        return rpoList;
    }
}
