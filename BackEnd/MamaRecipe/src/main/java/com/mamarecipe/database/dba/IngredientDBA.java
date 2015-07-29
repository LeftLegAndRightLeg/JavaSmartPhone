package com.mamarecipe.database.dba;

import com.mamarecipe.database.idba.IIngredientDBA;
import com.mamarecipe.model.IngredientPO;
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
public class IngredientDBA implements IIngredientDBA {
    public void add(IngredientPO ipo){
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_INGREDIENT)){
            stmt.setString(1, ipo.getIngreName());
            stmt.setInt(2, ipo.getIngreQt());
            stmt.setLong(3, ipo.getDishID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString()+" "+row+" rows inserted.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void update(IngredientPO ipo){
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_INGREDIENT)){
            stmt.setString(1, ipo.getIngreName());
            stmt.setInt(2, ipo.getIngreQt());
            stmt.setLong(3, ipo.getIngreID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString()+" "+row+" rows updated.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<IngredientPO> getByRecipeID(long recipeID){
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_INGREDIENTS_BY_DISHID)){
            stmt.setLong(1, recipeID);
            try(ResultSet rs = stmt.executeQuery()){
                List<IngredientPO> ipoList = new LinkedList<>();
                while(rs.next()){
                    IngredientPO ipo = new IngredientPO();
                    ipo.setIngreID(rs.getLong(1));
                    ipo.setIngreName(rs.getString(2));
                    ipo.setIngreQt(rs.getInt(3));
                    ipo.setDishID(rs.getLong(4));
                    ipoList.add(ipo);
                }
                ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString());
                return ipoList;
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
