package src.mamasrecipe;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.App;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewFragment extends Fragment {


    private EditText recipeNameToServer;
    private ImageView photoToServer;
    private EditText newfirst0TextView;
    private EditText newfirst1TextView;
    private EditText newsecond0TextView;
    private EditText newsecond1TextView;
    private EditText newthird0TextView;
    private EditText newthird1TextView;

    private EditText direction0TextView;
    private EditText direction1TextView;
    private EditText direction2TextView;
    private EditText direction3TextView;

    private Button newSubmitButton;
    private byte[] image;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newLayout = inflater.inflate(R.layout.fragment_new, container, false);
        getReference(newLayout);
        getImageFromCamera();

        newSubmitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyDishActivity.class);
                startActivity(intent);
            }
        });
        return newLayout;
    }
    public void writeBytesToFile(InputStream is, File file) throws IOException {
        FileOutputStream fos = null;
        try {
            //byte[] data = new byte[2048];
            int nbread = 0;
            fos = new FileOutputStream(file);
            while((nbread=is.read(image))>-1){
                fos.write(image,0,nbread);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            if (fos!=null){
                fos.close();
            }
        }
    }


    private void getImageFromCamera(){
        photoToServer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openCamera();

            }
        });
    }

    //Take photos
    public void openCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
    //Get photos
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        displayImage(bp);
    }

    //Set photos and display
    public void displayImage(Bitmap bp){
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, bos1);
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream("image.png");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            bos1.writeTo(fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        image = bos1.toByteArray();
        photoToServer.setImageBitmap(bp);


//        try {
//            bos1.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream("image.png");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            fos.write(image);
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("----------SAVE SUCCESS--------");


//        InputStream inputStream = getActivity().getResources().openRawResource(R.raw.pass);
//        File file = new File("pass.png");
//
//
//        try {
//            writeBytesToFile(inputStream, file);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        TypedFile typedFile = new TypedFile("multipart/form-data", file);
//        String dishID = "2";
//        App.getRestClient().getPhotoService().upload(typedFile, dishID,
//                new Callback<String>() {
//                    @Override
//                    public void success(String s, Response response) {
//                        Log.e("Upload", "Success");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("Upload", "Fail");
//                    }
//
//                });

    }




    private void getReference(View view){
        recipeNameToServer = (EditText) view.findViewById(R.id.recipeNameToServer);
        photoToServer = (ImageView) view.findViewById(R.id.photoToServer);

        newfirst0TextView = (EditText) view.findViewById(R.id.newfirst0TextView);
        newfirst1TextView = (EditText) view.findViewById(R.id.newfirst1TextView);
        newsecond0TextView = (EditText) view.findViewById(R.id.newsecond0TextView);
        newsecond1TextView = (EditText) view.findViewById(R.id.newsecond1TextView);
        newthird0TextView = (EditText) view.findViewById(R.id.newthird0TextView);
        newthird1TextView = (EditText) view.findViewById(R.id.newthird1TextView);
        direction0TextView = (EditText) view.findViewById(R.id.direction0TextView);
        direction1TextView = (EditText) view.findViewById(R.id.direction1TextView);
        direction2TextView = (EditText) view.findViewById(R.id.direction2TextView);
        direction3TextView = (EditText) view.findViewById(R.id.direction3TextView);
        newSubmitButton = (Button) view.findViewById(R.id.newSubmitButton);

    }
}