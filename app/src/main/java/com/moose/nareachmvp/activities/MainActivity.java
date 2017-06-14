package com.moose.nareachmvp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moose.nareachmvp.R;
import com.moose.nareachmvp.base.BaseAppCompatActivity;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.contract.MainContract;
import com.moose.nareachmvp.fragment.AllFragment;
import com.moose.nareachmvp.presenter.MainPresenter;
import com.moose.nareachmvp.utils.Loger;
import com.squareup.haha.perflib.Main;
import com.stylingandroid.prism.Prism;

import butterknife.ButterKnife;

public class MainActivity
        extends BaseAppCompatActivity<MainContract.View, MainPresenter>
        implements MainContract.View, Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener {


    private Toolbar mToolBar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ImageView mDrawerImageView;
    private TextView mUsername;
    private Menu menu;
    private View headerView;
    private int currentColor = 0xff00BFA5;
    private int tintColor = 0xff1DE9B6;
    private AllFragment allFragment;
    private MenuItem signMenuItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 贴上主界面fragment
        allFragment = new AllFragment();
        replaceFragment(allFragment);
        mPresenter.updateUserState();
    }

    @Override
    protected View[] setToolBar() {
        View[] views = new View[2];
        views[0] = mToolBar;
        views[1] = headerView;
        return views;
    }

    private Fragment currentFragment = null;

    public void replaceFragment(Fragment fragment) {
        String simpleName = fragment.getClass().getSimpleName();

        if (currentFragment != null) {
            String simpleName1 = currentFragment.getClass().getSimpleName();
            if (simpleName.equals(simpleName1)) {
                Loger.d("moose", "同一个fragment，返回。");
                return;
            }
        }
        Loger.d("moose", "替换fragment = " + simpleName);
        this.currentFragment = fragment;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.moose_content_ll, fragment);
        ft.commit();
    }

    private void setColor() {
//        Filter filter = new TintFilter(tintColor);
        Prism prism = Prism.Builder
                .newInstance()
                .background(getWindow())
                .background(mToolBar)
                .background(headerView)
                .build();
        prism.setColor(currentColor);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.updateUserState();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main2);
        initToolBarDrawer();
        // 初始化抽屉中 header view
        mDrawerImageView = (ImageView) headerView.findViewById(R.id.moose_drawer_image_view);
        mUsername = (TextView) headerView.findViewById(R.id.moose_drawer_username);

    }

    /**
     * 初始化toolbar和drawer抽屉
     */
    private void initToolBarDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.moose_drawer);
        mToolBar = (Toolbar) findViewById(R.id.moose_toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.moose_navigation_view);
        headerView = mNavigationView.getHeaderView(0);
        menu = mNavigationView.getMenu();
        signMenuItem = menu.findItem(R.id.item_toggle);

        mToolBar.setNavigationIcon(R.drawable.moose_menu);
//        mToolBar.setSubtitle(R.string.moose_subtitle);
//        mToolBar.showOverflowMenu();
        mToolBar.setOnMenuItemClickListener(this);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });
        mNavigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(mToolBar);
        mDrawer.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected MainPresenter attachPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setUserInfo(UserBean bean) {
        mUsername.setText(bean.username);

        updateMenuSign(bean.sign ? getString(R.string.moose_sign_out) : getString(R.string.moose_sign_out_in));
    }

    @Override
    public void updateMenuSign(String state) {
//        menu.getItem(3).setTitle(state);
        signMenuItem.setTitle(state);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    /**
     * 打开图库
     */
    @Override
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_toggle:
                dLog("我要上传。");
                mPresenter.openGallery();
                break;
            case android.R.id.home:
                mDrawer.openDrawer(Gravity.LEFT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("moose", "onActivity Result ; requestCode = " + requestCode + "; resultCode = " + resultCode);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    protected void dLog(String msg) {
        Loger.d(this.getClass().getSimpleName(), msg);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                dLog("首页");
//                currentColor = 0xff6D4C41;
//                tintColor = 0xff8D6E63;
                replaceFragment(new AllFragment());
                break;
            case R.id.item_my_photos:
                currentColor = 0xff78909C;
                tintColor = 0xff455A64;
                dLog("我的");
//                replaceFragment(new MyPhotoFragment());
//                startMyPhotoActivity();
                startAboutMeActivity(UserPhotoActivity.class);
                break;
            case R.id.item_support_us:
                currentColor = 0xff01579B;
                tintColor = 0xff03A9F4;
                startAboutMeActivity(AboutMeActivity.class);
                dLog("帮助我们——感谢您的帮助，我们会越做越好。");
                break;
            case R.id.item_toggle:
//                signOutIn(); todo 登录/登出
                mPresenter.signOutIn();
                break;
//            case R.id.item_about_me:
//                startAboutMeActivity(AboutMeActivity.class);
//                dLog("是的，我是发哥，一个不拘的独立开发者。");
//                break;
        }
        setColor();
//        setActivityThemeColor();
        mDrawer.closeDrawers();
        return false;
    }

    private void startAboutMeActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

}
