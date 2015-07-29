package com.mamarecipe.database.idba;

import com.mamarecipe.model.RecipePO;

import java.util.List;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IRecipeDBA {
    long add(RecipePO recipePO);
    void update(RecipePO recipePO);
    RecipePO getByRecipeID(long recipeID);
    List<RecipePO> getByCategoryID(long categoryID);
    List<RecipePO> getByUserID(long userID);
}
