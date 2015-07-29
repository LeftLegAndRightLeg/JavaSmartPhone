package com.mamarecipe.database.idba;

import com.mamarecipe.model.DirectionPO;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IDirectionDBA {
    void add(DirectionPO directionPO);
    void update(DirectionPO directionPO);
    DirectionPO getByRecipeID(long recipeID);
}
