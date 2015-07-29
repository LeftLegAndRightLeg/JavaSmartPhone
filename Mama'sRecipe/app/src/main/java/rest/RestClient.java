package rest;

import android.graphics.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rest.service.DirectionService;
import rest.service.IngredientService;
import rest.service.PhotoService;
import rest.service.RecipeService;
import rest.service.UserService;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * courtesy of http://blog.robinchutaux.com/blog/a-smart-way-to-use-retrofit/
 */
public class RestClient
{
    private static final String BASE_URL = "http://52.8.250.104:8080/MamaRecipe/webapi/";
    private UserService userService;
    private PhotoService photoService;
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private DirectionService directionService;

    public RestClient()
    {
        Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
            .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        userService = restAdapter.create(UserService.class);
        photoService = restAdapter.create(PhotoService.class);
        recipeService = restAdapter.create(RecipeService.class);
        ingredientService = restAdapter.create(IngredientService.class);
        directionService = restAdapter.create(DirectionService.class);
    }

    public UserService getUserService(){
        return userService;
    }
    public PhotoService getPhotoService(){
        return photoService;
    }
    public RecipeService getRecipeService(){
        return recipeService;
    }
    public IngredientService getIngredientService(){
        return ingredientService;
    }
    public DirectionService getDirectionService(){
        return directionService;
    }
}
