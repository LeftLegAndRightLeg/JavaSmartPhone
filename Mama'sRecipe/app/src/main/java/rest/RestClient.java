package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    }

    public UserService getUserService()
    {
        return userService;
    }
}
