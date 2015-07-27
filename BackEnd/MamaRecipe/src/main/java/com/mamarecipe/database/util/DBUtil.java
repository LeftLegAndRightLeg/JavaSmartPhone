package com.mamarecipe.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class DBUtil {
    private DBUtil() {}
    public static final Connection getConnection() throws SQLException{
        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://jdbcjavasp.cq6tvjnzkrnl.us-west-1.rds.amazonaws.com:3306";
        String username = "BobIsAwesome";
        String password = "YesHeIsAwesome";
        String db = "mamarecipe";
        return DriverManager.getConnection(url + "/" + db, username, password);
    }
}
