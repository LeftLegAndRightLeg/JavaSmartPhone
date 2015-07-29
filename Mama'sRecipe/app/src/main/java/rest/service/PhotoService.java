package rest.service;

import model.po.ImagePO;
import model.po.UserPO;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

/**
 * Created by Jeremiah on 7/27/15.
 */
public interface PhotoService {
    @Multipart
    @POST("/image/upload")
    void upload(@Part("file") TypedFile file,
                @Part("dishID") String dishID,
                Callback<String> cb);
    @GET("/image/dishID/{dishID}")
    void getImageByDishID(@Path("dishID")String dishID, Callback<ImagePO> cb);
}
