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
public class MainActivity extends Activity implements View.OnClickListener {

    private RecipeFragment recipeFragment;
    private NewFragment newFragment;
    private MeFragment meFragment;

    private View recipeLayout;
    private View newLayout;
    private View meLayout;
    private FragmentManager fragmentManager;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
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
        recipeLayout = findViewById(R.id.recipe_layout);
        newLayout = findViewById(R.id.new_layout);
        meLayout = findViewById(R.id.me_layout);
        recipeLayout.setOnClickListener(this);
        newLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recipe_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.new_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.me_layout:
                // 当点击了动态tab时，选中第3个tab
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
                if (recipeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    recipeFragment = new RecipeFragment();
                    transaction.add(R.id.content, recipeFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(recipeFragment);
                }
                break;
            case 1:
                if (newFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    newFragment = new NewFragment();
                    transaction.add(R.id.content, newFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(newFragment);
                }
                break;
            case 2:
                if (meFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    meFragment = new MeFragment();
                    transaction.add(R.id.content, meFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(meFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void hideFragments(FragmentTransaction transaction) {
        if (recipeFragment != null) {
            transaction.hide(recipeFragment);
        }
        if (newFragment != null) {
            transaction.hide(newFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
    }
}