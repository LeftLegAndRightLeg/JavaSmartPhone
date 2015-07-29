package rest.service;

import java.util.List;

import model.po.RecipePO;
import model.po.UserPO;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Jeremiah on 7/27/15.
 */
public interface RecipeService {
    @POST("/recipe/add")
    void addRecipe(@Body RecipePO recipePO, Callback<String> cb);
    @GET("/recipe/dishid/{dishid}")
    RecipePO getRecipeByDishID(@Path("dishid")String dishID);
    @GET("/recipe/categoryid/{CategoryID}")
    List<RecipePO> getRecipesByCategoryID(@Path("CategoryID")String CategoryID);
    @GET("/recipe/userid/{userID}")
    List<RecipePO> getRecipesByUserID(@Path("userID")String userID);
}
