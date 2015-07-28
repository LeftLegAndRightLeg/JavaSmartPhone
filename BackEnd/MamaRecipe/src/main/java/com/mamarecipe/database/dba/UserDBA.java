package com.mamarecipe.database.dba;

import com.mamarecipe.database.idba.IUserDBA;
import com.mamarecipe.database.po.UserPO;
import com.mamarecipe.database.util.DBUtil;
import com.mamarecipe.database.util.SQL;
import com.mamarecipe.util.ServerTrace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class UserDBA implements IUserDBA {
    public void add(UserPO userPO){
        try(Connection conn= DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_USER);
        ){
            stmt.setString(1, userPO.getUserName());
            stmt.setString(2, userPO.getUserPass());
            stmt.setString(3, userPO.getIpAddress());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString()+ " " + row + "rows inserted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void update(UserPO userPO){
        try(Connection conn= DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_USER);
        ){
            stmt.setString(1, userPO.getUserName());
            stmt.setString(2, userPO.getUserPass());
            stmt.setString(3, userPO.getIpAddress());
            stmt.setLong(4, userPO.getUserID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString()+ " " + row + "rows updated");
        }catch(Exception e){
            e.printStackTrace();
        }
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
