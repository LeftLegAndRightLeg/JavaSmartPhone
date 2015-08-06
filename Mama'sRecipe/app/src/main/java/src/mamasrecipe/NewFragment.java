package src.mamasrecipe;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import app.App;
import model.po.DirectionPO;
import model.po.IngredientPO;
import model.po.RecipePO;
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

    private Button newSaladButton;
    private Button newDesertButton;
    private Button newMaindishButton;
    private long newCategoryID;
    private long newDishID;
    private String newUserID;

    private File file;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newLayout = inflater.inflate(R.layout.fragment_new, container, false);
        newLayout.getBackground().setAlpha(100);

        getReference(newLayout);
        getImageFromCamera();

        newSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getIputs();
                recipeNameToServer.setText("");
                newfirst0TextView.setText("");
                newfirst0TextView.setText("");
                newfirst1TextView.setText("");
                newsecond0TextView.setText("");
                newsecond1TextView.setText("");
                newthird0TextView.setText("");
                newthird1TextView.setText("");

                direction0TextView.setText("");
                direction1TextView.setText("");
                direction2TextView.setText("");
                direction3TextView.setText("");

            }
        });
        newMaindishButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                newCategoryID = 1;
            }
        });
        newSaladButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                newCategoryID = 2;
            }
        });
        newDesertButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                newCategoryID = 3;
            }
        });


        return newLayout;
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
        try {
            displayImage(bp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayImage(Bitmap bp) throws FileNotFoundException {
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        file = new File(getActivity().getFilesDir(), "image.png");
        OutputStream op = new FileOutputStream(file);

        bp.compress(Bitmap.CompressFormat.PNG, 100, bos1);
        bp.compress(Bitmap.CompressFormat.PNG, 100, op);

        photoToServer.setImageBitmap(bp);

        try {
            op.close();
            bos1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private void getIputs(){
        String recipeName;



        recipeName = recipeNameToServer.getText().toString();
        App.ingredients = new ArrayList<String>();
        App.ingredientsQty = new ArrayList<String>();
        App.directions = new ArrayList<String>();

        App.ingredients.add(newfirst0TextView.getText().toString());
        App.ingredients.add(newsecond0TextView.getText().toString());
        App.ingredients.add(newthird0TextView.getText().toString());

        App.ingredientsQty.add(newfirst1TextView.getText().toString());
        App.ingredientsQty.add(newsecond1TextView.getText().toString());
        App.ingredientsQty.add(newthird1TextView.getText().toString());

        App.directions.add(direction0TextView.getText().toString());
        App.directions.add(direction1TextView.getText().toString());
        App.directions.add(direction2TextView.getText().toString());
        App.directions.add(direction3TextView.getText().toString());

        for(int i=0; i<4;i++){
            if(i<3){
                if(App.ingredients.get(i)=="" || App.ingredientsQty.get(i)==""){
                    Toast.makeText(getActivity().getBaseContext(), "Please Check Your Input !",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            }
            if(App.directions.get(i)==""){
                Toast.makeText(getActivity().getBaseContext(), "Please Check Your Input !",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }

        RecipePO recipePO = new RecipePO();
        recipePO.setUserID(Long.valueOf(newUserID));
        recipePO.setCategoryID(newCategoryID);
        recipePO.setDishName(recipeName);

        App.getRestClient().getRecipeService().addRecipe(recipePO, new Callback<RecipePO>() {

            @Override
            public void success(RecipePO recipePO, Response response) {
                newDishID = recipePO.getDishID();
                System.out.println("--------- !!!!!!!!!!!!! DishID:    " + newDishID);

                System.out.println("-------- Uploading RecipePO-------@@@@@--- CategoryID:" + newCategoryID );
                Log.e("Upload", "Success");
                for(int i=0; i<App.ingredients.size(); i++){
                    SystemClock.sleep(100);
                    IngredientPO temp = new IngredientPO();
                    temp.setDishID(newDishID);
                    temp.setIngreName(App.ingredients.get(i));
                    temp.setIngreQt(App.ingredientsQty.get(i));
                    App.getRestClient().getIngredientService().addRecipe(temp, new Callback<String>() {

                        @Override
                        public void success(String s, Response response) {
                            System.out.println("-----@@@@@@@@@@@@@@-----Uploading Ingredients");
                            Log.e("Upload Ingredient", "Success");
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            System.out.println("-----@@@@@@@@@@@@@@----- FFFFF Uploading Ingredients");
                        }
                    });
                }

                for(int i=0; i<App.directions.size(); i++){
                    SystemClock.sleep(100);
                    DirectionPO temp = new DirectionPO();
                    temp.setDishID(newDishID);
                    temp.setDirectionName(App.directions.get(i));
                    App.getRestClient().getDirectionService().addRecipe(temp, new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {
                            System.out.println("-----@@@@@@@@@@@@@@-----Uploading Directions");
                            Log.e("Upload Direction", "Success");
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            System.out.println("-----@@@@@@@@@@@@@@-----FFFFFF Uploading Directions");
                        }
                    });
                }
                if (photoToServer.getDrawable() == null) {
                    Toast.makeText(getActivity(), "Please Take Your Photo !", Toast.LENGTH_SHORT).show();
                    return;
                }
                TypedFile typedFile = new TypedFile("multipart/form-data", file);
                String dishID = String.valueOf(newDishID);
                App.getRestClient().getPhotoService().upload(typedFile, dishID,
                        new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                Log.e("Upload", "Success");
                                System.out.println("--------@@@@@@@@@@@----------Image Upload Success !!");
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e("Upload", "Fail");
                                //System.out.println("--------@@@@@@@@@@@--------FFFFFFF  Image Upload Success !!");
                            }

                        });

                Intent intent = new Intent(getActivity(), ShowDishActivity.class);
                intent.putExtra("userID", newUserID);
                intent.putExtra("dishID", String.valueOf(newDishID));
                System.out.println(String.valueOf(newDishID) + "@@@@@@@@@@@@@@@@@@@@@@");
                SystemClock.sleep(500);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });





    }

    private void getReference(View view){
        Bundle data = getArguments();
        newUserID = data.getString("userID");
        System.out.println("---------Receive from main activity:    " + newUserID);

        recipeNameToServer = (EditText) view.findViewById(R.id.recipeNameToServer);
        photoToServer = (ImageView) view.findViewById(R.id.photoToServer);
//        photoToServer.setAlpha(100);
//
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

        newSaladButton = (Button) view.findViewById(R.id.newSaladButton);
        newDesertButton = (Button) view.findViewById(R.id.newDesertButton);
        newMaindishButton = (Button) view.findViewById(R.id.newMaindishButton);

    }
}