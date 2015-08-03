package src.mamasrecipe;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import src.mamasrecipe.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WelcomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View welcomeLayout = inflater.inflate(R.layout.fragment_welcome, container, false);
        return welcomeLayout;
    }

}