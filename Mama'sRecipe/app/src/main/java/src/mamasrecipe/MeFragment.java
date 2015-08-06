package src.mamasrecipe;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.App;
import model.po.RecipePO;
import model.po.UserPO;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import src.mamasrecipe.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MeFragment extends Fragment {
    private String meUserID;
    private String meUserName;

    private TextView meUserNameEditText;
    private TextView meShowRecipeNumber;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View meLayout = inflater.inflate(R.layout.fragment_me, container, false);
        meLayout.getBackground().setAlpha(100);
        getReference(meLayout);

        meShowRecipeNumber.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyDishActivity.class);
                intent.putExtra("userID", meUserID);
                intent.putExtra("categoryID", "");
                startActivity(intent);
            }
        });


        return meLayout;
    }


    private void getReference(View view){
        Bundle data = getArguments();
        meUserID = data.getString("userID");
        meUserName = data.getString("userName");

        meUserNameEditText = (TextView) view.findViewById(R.id.meUserNameEditText);
        meShowRecipeNumber = (TextView) view.findViewById(R.id.meShowRecipeNumber);
        App.getRestClient().getRecipeService().getRecipesByUserID(meUserID, new Callback<List<RecipePO>>() {
            @Override
            public void success(List<RecipePO> recipePOs, Response response) {
                Integer size = recipePOs.size();
                meShowRecipeNumber.setText(size.toString());
                meUserNameEditText.setText(meUserName);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

}
