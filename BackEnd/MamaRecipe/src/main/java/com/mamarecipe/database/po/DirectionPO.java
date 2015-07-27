package com.mamarecipe.database.po;

import com.google.gson.Gson;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class DirectionPO {
    private long directionID;
    private String directionName;
    private long dishID;

    public long getDirectionID() {
        return directionID;
    }

    public void setDirectionID(long directionID) {
        this.directionID = directionID;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
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
