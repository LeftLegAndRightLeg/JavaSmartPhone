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

    /**
     * 用于展示消息的Fragment
     */
    private RecipeFragment recipeFragment;

    /**
     * 用于展示联系人的Fragment
     */
    private NewFragment newFragment;

    /**
     * 用于展示动态的Fragment
     */
    private MeFragment meFragment;

    /**
     * 消息界面布局
     */
    private View recipeLayout;

    /**
     * 联系人界面布局
     */
    private View newLayout;

    /**
     * 动态界面布局
     */
    private View meLayout;

    /**
     * 设置界面布局
     */
    private ImageView recipeImage;

    /**
     * 在Tab布局上显示联系人图标的控件
     */
    private ImageView newImage;

    /**
     * 在Tab布局上显示动态图标的控件
     */
    private ImageView meImage;

    /**
     * 在Tab布局上显示设置图标的控件
     */
    private TextView recipeText;

    /**
     * 在Tab布局上显示联系人标题的控件
     */
    private TextView newText;

    /**
     * 在Tab布局上显示动态标题的控件
     */
    private TextView meText;

    /**
     * 在Tab布局上显示设置标题的控件
     */
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
//        recipeImage = (ImageView) findViewById(R.id.message_image);
//        newImage = (ImageView) findViewById(R.id.contacts_image);
//        meImage = (ImageView) findViewById(R.id.news_image);
//        recipeText = (TextView) findViewById(R.id.message_text);
//        newText = (TextView) findViewById(R.id.contacts_text);
//        meText = (TextView) findViewById(R.id.news_text);
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
        // 每次选中之前先清楚掉上次的选中状态
//        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
//                messageImage.setImageResource(R.drawable.message_selected);
//                messageText.setTextColor(Color.WHITE);
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
                // 当点击了联系人tab时，改变控件的图片和文字颜色
//                contactsImage.setImageResource(R.drawable.contacts_selected);
//                contactsText.setTextColor(Color.WHITE);
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
                // 当点击了动态tab时，改变控件的图片和文字颜色
//                newsImage.setImageResource(R.drawable.news_selected);
//                newsText.setTextColor(Color.WHITE);
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
     * 清除掉所有的选中状态。
     */
//    private void clearSelection() {
//        messageImage.setImageResource(R.drawable.message_unselected);
//        messageText.setTextColor(Color.parseColor("#82858b"));
//        contactsImage.setImageResource(R.drawable.contacts_unselected);
//        contactsText.setTextColor(Color.parseColor("#82858b"));
//        newsImage.setImageResource(R.drawable.news_unselected);
//        newsText.setTextColor(Color.parseColor("#82858b"));
//        settingImage.setImageResource(R.drawable.setting_unselected);
//        settingText.setTextColor(Color.parseColor("#82858b"));
//    }

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