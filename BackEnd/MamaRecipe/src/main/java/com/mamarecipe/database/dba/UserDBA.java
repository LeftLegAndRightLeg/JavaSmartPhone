package com.mamarecipe.database.dba;

import com.mamarecipe.database.idba.IUserDBA;
import com.mamarecipe.model.UserPO;
import com.mamarecipe.database.util.DBUtil;
import com.mamarecipe.database.util.SQL;
import com.mamarecipe.util.ServerTrace;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class UserDBA implements IUserDBA {
    public long add(UserPO userPO){
        try(Connection conn= DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        ){
            stmt.setString(1, userPO.getUserName());
            stmt.setString(2, userPO.getUserPass());
            stmt.setString(3, userPO.getIpAddress());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString()+ " " + row + "rows inserted");
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    public boolean update(UserPO userPO){
        try(Connection conn= DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_USER);
        ){
            stmt.setString(1, userPO.getUserName());
            stmt.setString(2, userPO.getUserPass());
            stmt.setString(3, userPO.getIpAddress());
            stmt.setLong(4, userPO.getUserID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString()+ " " + row + "rows updated");
            return row>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public UserPO findByName(String userName){
        try(Connection conn= DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_USER_BY_USERNAME);
        ){
            stmt.setString(1, userName);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    UserPO po = new UserPO();
                    po.setUserID(rs.getLong(1));
                    po.setUserName(rs.getString(2));
                    po.setUserPass(rs.getString(3));
                    po.setIpAddress(rs.getString(4));
                    ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString());
                    return po;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public UserPO findByUserID(long userID){
        try(Connection conn= DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_USER_BY_USERID);
        ){
            stmt.setLong(1, userID);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    UserPO po = new UserPO();
                    po.setUserID(rs.getLong(1));
                    po.setUserName(rs.getString(2));
                    po.setUserPass(rs.getString(3));
                    po.setIpAddress(rs.getString(4));
                    ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString());
                    return po;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
