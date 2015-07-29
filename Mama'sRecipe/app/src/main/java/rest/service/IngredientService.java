package rest.service;

import java.util.List;

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
public interface IngredientService {
    @POST("/ingre/addone")
    void addRecipe(@Body IngredientPO ingrePO, Callback<String> cb);
    @POST("/ingre/addmore")
    void addRecipe(@Body List<IngredientPO> ingrePOList, Callback<String> cb);
    @GET("/ingre/dishID/{dishID}")
    List<IngredientPO> getIngreByDishID(@Path("dishID")String dishID);
}
