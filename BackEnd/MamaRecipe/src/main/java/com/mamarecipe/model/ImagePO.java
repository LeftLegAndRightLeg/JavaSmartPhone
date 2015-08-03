package com.mamarecipe.model;

import com.google.gson.Gson;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class ImagePO {
    private long imageID;
    private String imageURI;
    private long dishID;

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
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
