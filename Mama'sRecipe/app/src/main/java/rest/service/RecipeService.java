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
    void getRecipeByDishID(@Path("dishid")String dishID, RecipePO cb);
    @GET("/recipe/categoryid/{CategoryID}")
    void getRecipesByCategoryID(@Path("CategoryID")String CategoryID, Callback<List<RecipePO>> cb);
    @GET("/recipe/userid/{userID}")
    void getRecipesByUserID(@Path("userID")String userID, Callback<List<RecipePO>> cb);
}
