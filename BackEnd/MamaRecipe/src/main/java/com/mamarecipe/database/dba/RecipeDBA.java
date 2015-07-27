package com.mamarecipe.database.dba;

import com.mamarecipe.database.idba.IRecipeDBA;
import com.mamarecipe.database.po.RecipePO;
import com.mamarecipe.database.util.DBUtil;
import com.mamarecipe.database.util.SQL;
import com.mamarecipe.util.ServerTrace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class RecipeDBA implements IRecipeDBA {
    @Override
    public void add(RecipePO recipePO){
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_RECIPE)){
            stmt.setString(1, recipePO.getDishName());
            stmt.setLong(2, recipePO.getCategoryID());
            stmt.setLong(3, recipePO.getDishID());
            stmt.setString(4, recipePO.getCookingTime());
            stmt.setString(5, recipePO.getOther());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString()+" "+row+" rows inserted.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void update(RecipePO recipePO){
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_RECIPE)){
            stmt.setString(1, recipePO.getDishName());
            stmt.setLong(2, recipePO.getCategoryID());
            stmt.setString(3, recipePO.getCookingTime());
            stmt.setString(4, recipePO.getOther());
            stmt.setLong(5, recipePO.getDishID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString() + " " + row + " rows updated.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public RecipePO getByRecipeID(long recipeID) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_RECIPE_BY_DISHID)){
            stmt.setLong(1, recipeID);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    RecipePO rpo = new RecipePO();
                    rpo.setDishID(rs.getLong(1));
                    rpo.setDishName(rs.getString(2));
                    rpo.setCategoryID(rs.getLong(3));
                    rpo.setUserID(rs.getLong(4));
                    rpo.setCookingTime(rs.getString(5));
                    rpo.setOther(rs.getString(6));
                    ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString());
                    return rpo;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RecipePO> getByCategoryID(long categoryID) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_RECIPE_BY_CATEGORYID)){
            stmt.setLong(1, categoryID);
            try(ResultSet rs = stmt.executeQuery()){
                List<RecipePO> rpoList = new LinkedList<>();
                final int limit = 6;
                while(rs.next() && rpoList.size()<limit){
                    RecipePO rpo = new RecipePO();
                    rpo.setDishID(rs.getLong(1));
                    rpo.setDishName(rs.getString(2));
                    rpo.setCategoryID(rs.getLong(3));
                    rpo.setUserID(rs.getLong(4));
                    rpo.setCookingTime(rs.getString(5));
                    rpo.setOther(rs.getString(6));
                    rpoList.add(rpo);
                }
                ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString());
                return rpoList;
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RecipePO> getByUserID(long userID) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_RECIPE_BY_USERID)){
            stmt.setLong(1, userID);
            try(ResultSet rs = stmt.executeQuery()){
                List<RecipePO> rpoList = new LinkedList<>();
                final int limit = 6;
                while(rs.next() && rpoList.size()<limit){
                    RecipePO rpo = new RecipePO();
                    rpo.setDishID(rs.getLong(1));
                    rpo.setDishName(rs.getString(2));
                    rpo.setCategoryID(rs.getLong(3));
                    rpo.setUserID(rs.getLong(4));
                    rpo.setCookingTime(rs.getString(5));
                    rpo.setOther(rs.getString(6));
                    rpoList.add(rpo);
                }
                ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString());
                return rpoList;
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
