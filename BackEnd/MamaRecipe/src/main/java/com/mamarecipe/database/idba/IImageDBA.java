package com.mamarecipe.database.idba;

import com.mamarecipe.database.po.ImagePO;

import java.util.List;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IImageDBA {
    void add(ImagePO imagePO);
    void update(ImagePO imagePO);
    List<ImagePO> getByRecipeID(long recipeID);
    ImagePO getByImageID(long imageID);
}
