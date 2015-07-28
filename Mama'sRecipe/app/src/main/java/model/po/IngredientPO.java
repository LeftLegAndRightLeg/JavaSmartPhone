package model.po;

import com.google.gson.Gson;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class IngredientPO {
    private long ingreID;
    private String ingreName;
    private int ingreQt;
    private long dishID;

    public long getIngreID() {
        return ingreID;
    }

    public void setIngreID(long ingreID) {
        this.ingreID = ingreID;
    }

    public String getIngreName() {
        return ingreName;
    }

    public void setIngreName(String ingreName) {
        this.ingreName = ingreName;
    }

    public int getIngreQt() {
        return ingreQt;
    }

    public void setIngreQt(int ingreQt) {
        this.ingreQt = ingreQt;
    }

    public long getDishID() {
        return dishID;
    }

    public void setDishID(long dishID) {
        this.dishID = dishID;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
