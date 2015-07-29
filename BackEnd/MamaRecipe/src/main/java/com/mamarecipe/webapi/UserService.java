package com.mamarecipe.webapi;

import com.mamarecipe.database.dba.UserDBA;
import com.mamarecipe.database.idba.IUserDBA;
import com.mamarecipe.model.UserPO;

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
    public Response signUp(UserPO user) {
        //TODO: check duplicated user name
        IUserDBA userDBA = new UserDBA();
        long userID = userDBA.add(user);
        user.setUserID(userID);
        if(userID>=0)
            return Response.ok(user).build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/login")
    public Response login(UserPO user) {
        IUserDBA userDBA = new UserDBA();
        UserPO upo = userDBA.findByName(user.getUserName());
        if(upo!=null)
            return Response.status(Response.Status.NOT_FOUND).build();
        //TODO: make authentication more robust
        if(upo.getUserPass().equals(user.getUserPass()))
            return Response.ok(upo).build();
        else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
