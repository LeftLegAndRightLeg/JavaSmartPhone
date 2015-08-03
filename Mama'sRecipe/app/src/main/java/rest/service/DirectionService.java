package rest.service;

import java.util.List;

import model.po.DirectionPO;
import model.po.IngredientPO;
import model.po.RecipePO;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Jeremiah on 7/27/15.
 */
public interface DirectionService {
    @POST("/direction/addone")
    void addRecipe(@Body DirectionPO directPO, Callback<String> cb);
    @GET("/direction/dishID/{dishID}")
    void getDirectByDishID(@Path("dishID") String dishID, Callback<List<DirectionPO>> cb);
}
