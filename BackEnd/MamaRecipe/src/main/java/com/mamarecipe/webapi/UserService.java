package com.mamarecipe.webapi;

import com.mamarecipe.database.dba.UserDBA;
import com.mamarecipe.database.idba.IUserDBA;
import com.mamarecipe.database.po.UserPO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/user")
public class UserService {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/signup")
    public String addUser(UserPO user) {
        //TODO: check duplicated user name
        IUserDBA userDBA = new UserDBA();
        userDBA.add(user);
        return "OK";
    }
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/login")
    public UserPO login(UserPO user) {
        IUserDBA userDBA = new UserDBA();
        UserPO upo = userDBA.findByName(user.getUserName());
        if(upo!=null)
            return upo;
        else
            return new UserPO();
    }
}
