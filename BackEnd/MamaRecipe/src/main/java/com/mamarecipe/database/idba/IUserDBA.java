package com.mamarecipe.database.idba;

import com.mamarecipe.model.UserPO;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IUserDBA {
    long add(UserPO userPO);
    boolean update(UserPO userPO);
    UserPO findByName(String userName);
    UserPO findByUserID(long userID);
}
