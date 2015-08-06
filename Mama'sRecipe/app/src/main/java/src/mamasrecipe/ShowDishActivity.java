package src.mamasrecipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import app.App;
import model.po.DirectionPO;
import model.po.ImagePO;
import model.po.IngredientPO;
import model.po.RecipePO;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShowDishActivity extends ActionBarActivity {

    private String showDishID;
    private String showUserID;
    private Bitmap bitmap;

    private ImageView showDishPhoto;

    private TextView showDishName;

    private TextView first0TextView;
    private TextView first1TextView;

    private TextView second0TextView;
    private TextView second1TextView;

    private TextView third0TextView;
    private TextView third1TextView;

    private TextView direction1TextView;
    private TextView direction2TextView;
    private TextView direction3TextView;
    private TextView direction4TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dish);
        View v = findViewById(R.id.showdish);
        v.getBackground().setAlpha(100);
        getReference();
        layoutSetterByDishID();
    }

    private void getReference(){
        Intent intent = getIntent();
        showUserID = intent.getStringExtra("userID");
        showDishID = intent.getStringExtra("dishID");

        System.out.println(" ------@@@@@----- UserID:  " + showUserID);
        System.out.println(" ------@@@@@----- DishID:  " + showDishID);

        showDishPhoto = (ImageView) findViewById(R.id.showDishPhoto);

        showDishName = (TextView) findViewById(R.id.showDishName);
        first0TextView = (TextView) findViewById(R.id.first0TextView);
        first1TextView = (TextView) findViewById(R.id.first1TextView);

        second0TextView = (TextView) findViewById(R.id.second0TextView);
        second1TextView = (TextView) findViewById(R.id.second1TextView);

        third0TextView = (TextView) findViewById(R.id.third0TextView);
        third1TextView = (TextView) findViewById(R.id.third1TextView);

        direction1TextView = (TextView) findViewById(R.id.direction1TextView);
        direction2TextView = (TextView) findViewById(R.id.direction2TextView);
        direction3TextView = (TextView) findViewById(R.id.direction3TextView);
        direction4TextView = (TextView) findViewById(R.id.direction4TextView);
    }
    private void layoutSetterByDishID(){
        App.getRestClient().getRecipeService().getRecipeByDishID(showDishID, new Callback<RecipePO>() {
            @Override
            public void success(RecipePO recipePO, Response response) {
                showDishName.setText(recipePO.getDishName());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        App.getRestClient().getPhotoService().getImageByDishID(showDishID, new Callback<ImagePO>() {
            @Override
            public void success(ImagePO imagePO, Response response) {
                System.out.println("-----------!!!!!!! Recieved Dish ID" + showDishID);
                System.out.println("-----------!!!!!!! Recieved " + imagePO.getDishID());

                imageThread thread = new  imageThread("http://" + imagePO.getImageURI());
                thread.start();
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
        App.getRestClient().getIngredientService().getIngreByDishID(showDishID, new Callback<List<IngredientPO>>() {
            @Override
            public void success(List<IngredientPO> ingredientPOs, Response response) {
                System.out.println("-----Ingredients-----" + ingredientPOs.toString());
                for(int i=0; i<ingredientPOs.size(); i++){
                    System.out.println("--------Ingredients: " + i + "   " +
                            ingredientPOs.get(i).getIngreName() + "     QTY:  " + ingredientPOs.get(i).getIngreQt());
                }
                first0TextView.setText(ingredientPOs.get(0).getIngreName());
                first1TextView.setText(ingredientPOs.get(0).getIngreQt());
                second0TextView.setText(ingredientPOs.get(1).getIngreName());
                second1TextView.setText(ingredientPOs.get(1).getIngreQt());
                third0TextView.setText(ingredientPOs.get(2).getIngreName());
                third1TextView.setText(ingredientPOs.get(2).getIngreQt());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        App.getRestClient().getDirectionService().getDirectByDishID(showDishID, new Callback<List<DirectionPO>>() {
            @Override
            public void success(List<DirectionPO> directionPOs, Response response) {
                System.out.println("----Directions------" + directionPOs.toString());
                for(int i=0; i<directionPOs.size(); i++){
                    System.out.println("-------------Direction:  " + i + "    " + directionPOs.get(i).getDirectionName());
                }
                direction1TextView.setText(directionPOs.get(0).getDirectionName());
                direction2TextView.setText(directionPOs.get(1).getDirectionName());
                direction3TextView.setText(directionPOs.get(2).getDirectionName());
                direction4TextView.setText(directionPOs.get(3).getDirectionName());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }

    private class imageThread extends Thread{
        private String imageUrl;

        private imageThread(String imageUrl){
            this.imageUrl = imageUrl;
        }
        @Override
        public void run() {
            try {
                URL url = new URL(imageUrl);
                InputStream is = url.openStream();
                bitmap = BitmapFactory.decodeStream(is);
                handler.sendEmptyMessage(0x1);
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
                showDishPhoto.setImageBitmap(bitmap);
            }
            else{
                // Write Exception here !
            }
        }

    };










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_dish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
