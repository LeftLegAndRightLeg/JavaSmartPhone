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
    public static String QUERY_RECIPE_BY_CATEGORYID;
    public static String QUERY_IMAGE_BY_DISHID;
    public static String QUERY_INGREDIENTS_BY_DISHID;
    public static String QUERY_DIRECTIONS_BY_DISHID;
    public static String UPDATE_USER;
    public static String UPDATE_RECIPE;
    public static String UPDATE_IMAGE;
    public static String UPDATE_INGREDIENT;
    public static String UPDATE_DIRECTION;
    public static String QUERY_USER_BY_USERID;
    public static String QUERY_USER_BY_USERNAME;
    public static String QUERY_RECIPE_BY_USERID;
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
        QUERY_RECIPE_BY_CATEGORYID = p.getProperty("QUERY_RECIPE_BY_CATEGORYID");
        UPDATE_USER = p.getProperty("UPDATE_USER");
        UPDATE_RECIPE = p.getProperty("UPDATE_RECIPE");
        UPDATE_IMAGE = p.getProperty("UPDATE_IMAGE");
        UPDATE_INGREDIENT = p.getProperty("UPDATE_INGREDIENT");
        UPDATE_DIRECTION = p.getProperty("UPDATE_DIRECTION");
        QUERY_USER_BY_USERID = p.getProperty("QUERY_USER_BY_USERID");
        QUERY_USER_BY_USERNAME = p.getProperty("QUERY_USER_BY_USERNAME");
        QUERY_RECIPE_BY_USERID = p.getProperty("QUERY_RECIPE_BY_USERID");
    }
}