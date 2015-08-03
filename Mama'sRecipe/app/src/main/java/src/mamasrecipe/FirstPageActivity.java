
package src.mamasrecipe;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 *
 * @author guolin
 */
public class FirstPageActivity extends Activity implements View.OnClickListener {
    private WelcomeFragment welcomeFragment;
    private LogInFragment loginFragment;
    private SignUpFragment signupFragment;


    private View welcomeLayout;
    private View loginLayout;
    private View signupLayout;

    private FragmentManager fragmentManager;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first_page);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        welcomeLayout = findViewById(R.id.welcome_layout);
        loginLayout = findViewById(R.id.login_layout);
        signupLayout = findViewById(R.id.signup_layout);
        welcomeLayout.setOnClickListener(this);
        loginLayout.setOnClickListener(this);
        signupLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.login_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(1);
                break;
            case R.id.signup_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(2);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (welcomeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    welcomeFragment = new WelcomeFragment();
                    transaction.add(R.id.content, welcomeFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(welcomeFragment);
                }
                break;
            case 1:
                if (loginFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    loginFragment = new LogInFragment();
                    transaction.add(R.id.content, loginFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(loginFragment);
                }
                break;
            case 2:
                if (signupFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    signupFragment = new SignUpFragment();
                    transaction.add(R.id.content, signupFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(signupFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void hideFragments(FragmentTransaction transaction) {
        if (welcomeFragment != null) {
            transaction.hide(welcomeFragment);
        }
        if (loginFragment != null) {
            transaction.hide(loginFragment);
        }
        if (signupFragment != null) {
            transaction.hide(signupFragment);
        }
    }
}