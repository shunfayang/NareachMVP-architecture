package com.moose.nareachmvp.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.moose.nareachmvp.NareachApp;
import com.stylingandroid.prism.Prism;
import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.TintFilter;

/**
 * Created by Moose Yang on 2016/1/12.
 * todo Copy Right MooseStudio
 * 本类注释：必须实现V类型的接口。
 * <p/>
 * 定义Activity时，暂定认为Activity中的东西越少越好，Activity中尽量少出现业务逻辑，
 * view的创建也尽量不在其中进行，仅仅负责贴上view（show），create也不负责。
 */
public abstract class BaseAppCompatActivity<V extends BaseView, P extends BasePresenter<V>>
        extends AppCompatActivity {
    protected P mPresenter;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO
        }
    };
    private long mMainThreadId;
    private NareachApp application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建presenter
        mPresenter = attachPresenter();
        // 贴上view
        mPresenter.attachView((V) this);
        application = (NareachApp) getApplication();
        initView();

        setActivityThemeColor();
    }

    /**
     * 重写此方法设置View
     */
    protected abstract void initView();

    protected Prism setActivityThemeColor() {
        Filter filter = new TintFilter(application.getTintColor());
        Prism.Builder builder = Prism.Builder
                .newInstance();
        builder.background(getWindow());
        View[] views = setToolBar();
        if (views != null && views.length >= 0) {
            for (View v : views) {
                builder.background(v);
            }
        }
        Prism prism = builder.build();
        int currentColor = application.getCurrentColor();
        prism.setColor(currentColor);
        return prism;
    }

    /**
     * 提供需要设置成主题颜色的view的数组
     *
     * @return view 的数组
     */
    protected abstract View[] setToolBar();

    /**
     * 得到Presenter对象
     *
     * @return mPresenter对象
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 创建presenter，需要继承BasePresenter
     * 可以通过 getPresenter() 方法去获取presenter对象
     *
     * @return 自定义的presenter
     */
    protected abstract P attachPresenter();

    public long currentThreadId() {
        mMainThreadId = getMainLooper().getThread().getId();
        return mMainThreadId;
    }

    /**
     * 保证在主线程执行的方法
     *
     * @param r 需要执行的Runnable
     */
    public void post(Runnable r) {
        if (Thread.currentThread().getId() != mMainThreadId) {
            // todo 不在主线程
            mHandler.post(r);
        } else {
            // todo 主线程
            r.run();
        }
    }

    private RequestPermissionCallBack mCallBack;
    private int requestCode;

    public void requestPermission(String checkPermission, String[] requestPermissions,
                                  RequestPermissionCallBack callBack, int requestCode) {
        this.mCallBack = callBack;
        this.requestCode = requestCode;
        if (ContextCompat.checkSelfPermission(this, checkPermission) == PackageManager.PERMISSION_GRANTED) {
            callBack.hasPermission();
        } else {
            // 请求权限
            Log.d("moose", "需要请求权限。");
            ActivityCompat.requestPermissions(this, requestPermissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*
        The grant results for the corresponding permissions which is either
        android.content.pm.PackageManager.PERMISSION_GRANTED or
        android.content.pm.PackageManager.PERMISSION_DENIED. Never null.
         */
        if (requestCode == this.requestCode) {
            mCallBack.hasPermission();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface RequestPermissionCallBack {
        void hasPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // todo onDestroy方法不是一直都会执行，需要加上软引用。
        mPresenter = null;
        mHandler = null;
    }

    public NareachApp getNareachApp() {
        return (NareachApp) getApplication();
    }
}

