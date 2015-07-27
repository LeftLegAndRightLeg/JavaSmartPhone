package com.mamarecipe.database.idba;

import com.mamarecipe.database.po.UserPO;

/**
 * Created by Jeremiah on 7/26/15.
 */
public interface IUserDBA {
    void add(UserPO userPO);
    void update(UserPO userPO);
    UserPO findByName(String userName);
    UserPO findByUserID(long userID);
}
