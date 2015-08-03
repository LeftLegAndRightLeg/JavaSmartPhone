package com.mamarecipe.database.idba;

import com.mamarecipe.model.DirectionPO;

import java.util.List;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IDirectionDBA {
    void add(DirectionPO directionPO);
    void update(DirectionPO directionPO);
    List<DirectionPO> getByRecipeID(long recipeID);
}
