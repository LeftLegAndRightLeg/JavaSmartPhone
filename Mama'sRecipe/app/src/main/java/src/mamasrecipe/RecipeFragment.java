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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import app.App;
import model.po.ImagePO;
import model.po.RecipePO;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import src.mamasrecipe.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RecipeFragment extends Fragment {

    private ImageView recipeimageView1;
    private ImageView recipeimageView2;
    private ImageView recipeimageView3;

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View recipeLayout = inflater.inflate(R.layout.fragment_recipe, container, false);
        recipeLayout.getBackground().setAlpha(100);
        getReference(recipeLayout);
        imageSetterByCategoryID();
        clickListener();
        return recipeLayout;
    }

    private void getReference(View view){
        recipeimageView1 = (ImageView) view.findViewById(R.id.recipeimageView1);
        recipeimageView2 = (ImageView) view.findViewById(R.id.recipeimageView2);
        recipeimageView3 = (ImageView) view.findViewById(R.id.recipeimageView3);

    }
    private void clickListener(){
        recipeimageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent submit = new Intent(getActivity(), MyDishActivity.class);
                submit.putExtra("categoryID", "1"); // Add dishID later here !
                submit.putExtra("userID", "");
                startActivity(submit);
            }
        });
        recipeimageView2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent submit = new Intent(getActivity(), MyDishActivity.class);
                submit.putExtra("categoryID", "2"); // Add dishID later here !
                submit.putExtra("userID", "");
                startActivity(submit);
            }
        });
        recipeimageView3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent submit = new Intent(getActivity(), MyDishActivity.class);
                submit.putExtra("categoryID", "3"); // Add dishID later here !
                submit.putExtra("userID", "");
                startActivity(submit);
            }
        });
    }
    private void imageSetterByCategoryID(){

        App.getRestClient().getRecipeService().getRecipesByCategoryID("1", new Callback<List<RecipePO>>() {
            @Override
            public void success(List<RecipePO> recipePOs, Response response) {
                long currentDishID = recipePOs.get(recipePOs.size()-4).getDishID();
                App.getRestClient().getPhotoService().getImageByDishID(String.valueOf(currentDishID), new Callback<ImagePO>() {
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

            @Override
            public void failure(RetrofitError error) {

            }
        });
        App.getRestClient().getRecipeService().getRecipesByCategoryID("2", new Callback<List<RecipePO>>() {
            @Override
            public void success(List<RecipePO> recipePOs, Response response) {
                long currentDishID = recipePOs.get(recipePOs.size()-6).getDishID();
                App.getRestClient().getPhotoService().getImageByDishID(String.valueOf(currentDishID), new Callback<ImagePO>() {
                    @Override
                    public void success(ImagePO imagePO, Response response) {
                        imageThread thread = new  imageThread("http://" + imagePO.getImageURI(), 2);
                        thread.start();
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
        App.getRestClient().getRecipeService().getRecipesByCategoryID("3", new Callback<List<RecipePO>>() {
            @Override
            public void success(List<RecipePO> recipePOs, Response response) {
                long currentDishID = recipePOs.get(recipePOs.size()-6).getDishID();
                App.getRestClient().getPhotoService().getImageByDishID(String.valueOf(currentDishID), new Callback<ImagePO>() {
                    @Override
                    public void success(ImagePO imagePO, Response response) {
                        imageThread thread = new  imageThread("http://" + imagePO.getImageURI(), 3);
                        thread.start();
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
                    case 2:
                        bitmap2 = BitmapFactory.decodeStream(is);
                        break;
                    case 3:
                        bitmap3 = BitmapFactory.decodeStream(is);
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
                recipeimageView1.setImageBitmap(bitmap1);
            }
            if (msg.what == 0x2){
                recipeimageView2.setImageBitmap(bitmap2);
            }
            if (msg.what == 0x3){
                recipeimageView3.setImageBitmap(bitmap3);
            }
            else{
                // Write Exception here !
            }
        }

    };



}