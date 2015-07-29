package com.mamarecipe.database.idba;

import com.mamarecipe.model.ImagePO;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IImageDBA {
    void add(ImagePO imagePO);
    void update(ImagePO imagePO);
    ImagePO getByRecipeID(long recipeID);
}
