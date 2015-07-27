package com.mamarecipe.database.dba;

import com.mamarecipe.database.idba.IDirectionDBA;
import com.mamarecipe.database.po.DirectionPO;
import com.mamarecipe.database.po.ImagePO;
import com.mamarecipe.database.util.DBUtil;
import com.mamarecipe.database.util.SQL;
import com.mamarecipe.util.ServerTrace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Jeremiah on 7/27/15.
 */
public class DirectionDBA implements IDirectionDBA {
    @Override
    public void add(DirectionPO directionPO) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_DIRECTION)){
            stmt.setString(1, directionPO.getDirectionName());
            stmt.setLong(2, directionPO.getDishID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString() + " " + row + " rows inserted.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(DirectionPO directionPO) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_DIRECTION)){
            stmt.setString(1, directionPO.getDirectionName());
            stmt.setLong(2, directionPO.getDirectionID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString() + " " + row + " rows updated.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public DirectionPO getByRecipeID(long recipeID) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_DIRECTIONS_BY_DISHID)){
            stmt.setLong(1, recipeID);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    DirectionPO dpo = new DirectionPO();
                    dpo.setDirectionID(rs.getLong(1));
                    dpo.setDirectionName(rs.getString(2));
                    dpo.setDishID(rs.getLong(3));
                    return dpo;
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
