package com.mamarecipe.database.idba;

import com.mamarecipe.model.IngredientPO;

import java.util.List;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IIngredientDBA {
    void add(IngredientPO ipo);
    void update(IngredientPO ipo);
    List<IngredientPO> getByRecipeID(long recipeID);
}
