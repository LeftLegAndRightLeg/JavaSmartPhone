package com.mamarecipe.webapi;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.mamarecipe.database.dba.DirectionDBA;
import com.mamarecipe.database.dba.ImageDBA;
import com.mamarecipe.database.idba.IDirectionDBA;
import com.mamarecipe.database.idba.IImageDBA;
import com.mamarecipe.model.DirectionPO;
import com.mamarecipe.model.ImagePO;
import com.mamarecipe.util.ServerTrace;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/image")
public class ImageService {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Path("/upload")
    public Response addImage(
            @FormDataParam("file")InputStream fileInputStream,
            @FormDataParam("file")FormDataContentDisposition cntDispositionHeader,
            @FormDataParam("dishID")String dishID){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String filepath = "DishID_"+dishID+"_"+timeStamp+"_"+cntDispositionHeader.getFileName();
        ServerTrace.log("Upload Photo", "DishID: ", dishID);
        saveLocalFile(fileInputStream, filepath);
        saveS3File(filepath);
        ImagePO ipo = new ImagePO();
        ipo.setDishID(Long.parseLong(dishID));
        ipo.setImageURI("s3-us-west-1.amazonaws.com/mamarecipe/photo/" + filepath);
        IImageDBA idba = new ImageDBA();
        idba.add(ipo);
        return Response.ok().build();
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/dishID/{dishID}")
    public Response getImageByDID(@PathParam("dishID")String dishID){
        IImageDBA idba = new ImageDBA();
        ImagePO ipo = idba.getByRecipeID(Long.parseLong(dishID));
        return Response.ok(ipo).build();
    }


    private void saveS3File(String filepath){
        try{
            String existingBucketName = "mamarecipe";
            TransferManager tm = new TransferManager(new ProfileCredentialsProvider());
            // TransferManager processes all transfers asynchronously,
            // so this call will return immediately.
            Upload upload = tm.upload(
                    existingBucketName, "photo/"+filepath, new File(filepath));
            try {
                // Or you can block and wait for the upload to finish
                upload.waitForCompletion();
                System.out.println("Upload complete.");
                File toDelete = new File(filepath);
                toDelete.delete();
            } catch (AmazonClientException amazonClientException) {
                System.out.println("Unable to upload file, upload was aborted.");
                amazonClientException.printStackTrace();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void saveLocalFile(InputStream uploadedInputStream, String serverLocation){
        try{
            OutputStream output = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = uploadedInputStream.read(bytes))!=-1){
                output.write(bytes, 0, read);
            }
            output.flush();
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
