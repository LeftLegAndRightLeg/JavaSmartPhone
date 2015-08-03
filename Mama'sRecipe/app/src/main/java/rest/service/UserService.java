package rest.service;

import model.po.UserPO;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Jeremiah on 7/27/15.
 */
public interface UserService {
    @POST("/user/signup")
    void signUp(@Body UserPO userPO, Callback<UserPO> cb);
    @POST("/user/login")
    void login(@Body UserPO userPO, Callback<UserPO> cb);
}
