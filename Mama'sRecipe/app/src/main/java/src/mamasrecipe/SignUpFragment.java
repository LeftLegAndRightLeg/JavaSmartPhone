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
                        Intent submit = new Intent(getActivity(), MainActivity.class);
                        startActivity(submit);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println("----------- Sign Up Failure ----------");
                        Intent submit = new Intent(getActivity(), MainActivity.class);
                        startActivity(submit);
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
    }
}