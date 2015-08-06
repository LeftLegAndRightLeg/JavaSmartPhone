package app;

import android.app.Application;

import java.util.ArrayList;

import rest.RestClient;


/**
 * Author :    Chutaux Robin
 * Date :      10/2/2014
 */
public class App extends Application
{
    private static RestClient restClient;
    public static ArrayList<String> ingredients = new ArrayList<String>();
    public static ArrayList<String> ingredientsQty = new ArrayList<String>();
    public static ArrayList<String> directions = new ArrayList<String>();
    @Override
    public void onCreate()
    {
        super.onCreate();

        restClient = new RestClient();
    }

    public static RestClient getRestClient()
    {
        return restClient;
    }
}
