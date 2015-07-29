package com.mamarecipe.database.dba;

import com.mamarecipe.database.idba.IImageDBA;
import com.mamarecipe.model.ImagePO;
import com.mamarecipe.database.util.DBUtil;
import com.mamarecipe.database.util.SQL;
import com.mamarecipe.util.ServerTrace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Jeremiah on 7/27/15.
 */
public class ImageDBA implements IImageDBA{
    @Override
    public void add(ImagePO imagePO) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_IMAGE)){
            stmt.setString(1, imagePO.getImageURI());
            stmt.setLong(2, imagePO.getDishID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString() + " " + row + " rows inserted.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(ImagePO imagePO) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_IMAGE)){
            stmt.setString(1, imagePO.getImageURI());
            stmt.setLong(2, imagePO.getImageID());
            int row = stmt.executeUpdate();
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString() + " " + row + " rows updated.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ImagePO getByRecipeID(long recipeID) {
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL.QUERY_IMAGE_BY_DISHID)){
            stmt.setLong(1, recipeID);
            ServerTrace.log(this.getClass().toString(), "SQL", stmt.toString());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    ImagePO ipo = new ImagePO();
                    ipo.setImageID(rs.getLong(1));
                    ipo.setImageURI(rs.getString(2));
                    ipo.setDishID(rs.getLong(3));
                    return ipo;
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
