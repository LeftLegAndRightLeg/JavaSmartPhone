package src.mamasrecipe;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.App;
import model.po.UserPO;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SignUpFragment extends Fragment {

    private EditText accountSignUpEditText;
    private EditText pwSignUpEditText;
    private Button SignUpSubmitButton;

    private String userName;
    private String passWord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        view.getBackground().setAlpha(100);
        getReference(view);

        SignUpSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getInputs();
                UserPO upo = new UserPO();
                upo.setUserName(userName);
                upo.setUserPass(passWord);
                App.getRestClient().getUserService().signUp(upo, new Callback<UserPO>() {
                    @Override
                    public void success(UserPO userPO, Response response) {
                        if(userPO.getUserName().equals("") || userPO.getUserPass().equals("")){
                            Toast.makeText(getActivity().getBaseContext(),"Null UserName or PassWord" + "Please Check Your UserName and PassWord",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent submit = new Intent(getActivity(), MainActivity.class);
                        submit.putExtra("userID", String.valueOf(userPO.getUserID()));
                        submit.putExtra("userName", userName);
                        startActivity(submit);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getBaseContext(), "Please Check Your UserName and PassWord",
                                Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        return view;

    }
    public void getReference(View view){
        accountSignUpEditText = (EditText) view.findViewById(R.id.accountSignUpEditText);
        pwSignUpEditText = (EditText) view.findViewById(R.id.pwSignUpEditText);
        SignUpSubmitButton = (Button) view.findViewById(R.id.SignUpSubmitButton);
    }
    public void getInputs(){
        userName = accountSignUpEditText.getText().toString();
        passWord = pwSignUpEditText.getText().toString();
        if(userName == "" || passWord == ""){
            Toast.makeText(getActivity().getBaseContext(),"Please Check Your UserName and PassWord",
                    Toast.LENGTH_LONG).show();

        }
    }
}