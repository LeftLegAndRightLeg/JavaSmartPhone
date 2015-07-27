package com.mamarecipe.database.util;

import com.mamarecipe.util.FileIO;

import java.util.Properties;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class SQL {
    public static String INSERT_USER;
    public static String INSERT_RECIPE;
    public static String INSERT_IMAGE;
    public static String INSERT_INGREDIENT;
    public static String INSERT_DIRECTION;
    public static String QUERYALL_USERS;
    public static String QUERY_RECIPE_BY_DISHID;
    public static String QUERY_IMAGE_BY_DISHID;
    public static String QUERY_INGREDIENTS_BY_DISHID;
    public static String QUERY_DIRECTIONS_BY_DISHID;
    static{
        FileIO fileIO = new FileIO();
        Properties p = fileIO.readPropertiesFile("mamarecipe_op_sql.properties");
        INSERT_USER = p.getProperty("INSERT_USER");
        INSERT_RECIPE = p.getProperty("INSERT_RECIPE");
        INSERT_IMAGE = p.getProperty("INSERT_IMAGE");
        INSERT_INGREDIENT = p.getProperty("INSERT_INGREDIENT");
        INSERT_DIRECTION = p.getProperty("INSERT_DIRECTION");
        QUERYALL_USERS = p.getProperty("QUERYALL_USERS");
        QUERY_RECIPE_BY_DISHID = p.getProperty("QUERY_RECIPE_BY_DISHID");
        QUERY_IMAGE_BY_DISHID = p.getProperty("QUERY_IMAGE_BY_DISHID");
        QUERY_INGREDIENTS_BY_DISHID = p.getProperty("QUERY_INGREDIENTS_BY_DISHID");
        QUERY_DIRECTIONS_BY_DISHID = p.getProperty("QUERY_DIRECTIONS_BY_DISHID");
    }
}
