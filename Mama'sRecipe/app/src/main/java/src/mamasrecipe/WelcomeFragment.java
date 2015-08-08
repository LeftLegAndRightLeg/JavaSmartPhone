package src.mamasrecipe;
import android.annotation.TargetApi;
import android.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;

import app.App;
import model.po.ImagePO;
import model.po.RecipePO;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import src.mamasrecipe.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WelcomeFragment extends Fragment {

    public String categoryID;
    public String randomDishID;
    private ImageView welcomeImageView;
    private Bitmap bitmap1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        view.getBackground().setAlpha(100);
        getReference(view);
        setWelcomeImage();
        clickListener();

        SensorManagerHelper sensorHelper = new SensorManagerHelper(getActivity());
        sensorHelper.setOnShakeListener(new SensorManagerHelper.OnShakeListener() {

            @Override
            public void onShake() {
                // TODO Auto-generated method stub
                setWelcomeImage();
                System.out.println("--------------!!!!!!!!######$$$$$$$!!!!!!-------shake");
                SystemClock.sleep(1000);
            }
        });

        return view;
    }


    private void getReference(View view){
        welcomeImageView = (ImageView) view.findViewById(R.id.welcomeImage);
    }

    private void setWelcomeImage() {
        int randomCategoryID = new Random().nextInt(3);
        randomCategoryID++;
        categoryID = Integer.toString(randomCategoryID);

        App.getRestClient().getRecipeService().getRecipesByCategoryID(categoryID, new Callback<List<RecipePO>>(){
            @Override
            public void success(List<RecipePO> recipePOs, Response response) {
                int numberofRecipe = recipePOs.size();
                int randomRecipe = new Random().nextInt(numberofRecipe);
                long dishID = recipePOs.get(randomRecipe).getDishID();
                randomDishID = Long.toString(dishID);
                App.getRestClient().getPhotoService().getImageByDishID(randomDishID, new Callback<ImagePO>() {
                    @Override
                    public void success(ImagePO imagePO, Response response) {
                        SystemClock.sleep(80);
                        if(imagePO.getImageURI() == null){
                            App.getRestClient().getPhotoService().getImageByDishID("2", new Callback<ImagePO>() {
                                @Override
                                public void success(ImagePO imagePO, Response response) {
                                    imageThread thread = new  imageThread("http://" + imagePO.getImageURI(), 1);
                                    thread.start();
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });

                        }
                        else{
                            imageThread thread = new  imageThread("http://" + imagePO.getImageURI(), 1);
                            thread.start();
                        }

                    }
                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private class imageThread extends Thread{
        private String imageUrl;
        private int index;

        private imageThread(String imageUrl, int index){
            this.imageUrl = imageUrl;
            this.index = index;
        }
        @Override
        public void run() {
            try {
                URL url = new URL(imageUrl);
                InputStream is = url.openStream();
                switch (index){
                    case 1:
                        bitmap1 = BitmapFactory.decodeStream(is);
                        break;
                    default:
                        break;
                }

                handler.sendEmptyMessage(index);
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what == 0x1){
                welcomeImageView.setImageBitmap(bitmap1);
            }
            else{
                // Write Exception here !
            }
        }
    };

    private void clickListener() {
        welcomeImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent submit = new Intent(getActivity(), ShowDishActivity.class);
                submit.putExtra("dishID", randomDishID);
                startActivity(submit);
            }
        });
    }
}









