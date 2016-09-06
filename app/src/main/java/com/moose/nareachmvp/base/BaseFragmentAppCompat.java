package com.moose.nareachmvp.base;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moose.nareachmvp.utils.Loger;

/**
 * Created by Moose Yang on 2016/1/12.
 * todo Copy Right MooseStudio
 * 本类注释：Activity 必须是 BaseAppCompatActivity，也必须实现一个V类型的View接口。
 */
public abstract class BaseFragmentAppCompat<V,P extends BasePresenter<V>> extends Fragment{

    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        initData();
        return view;
    }

    /**
     * 在view初始化完毕之后执行的获取数据方法
     */
    protected abstract void initData();

    /**
     * 初始化view，此方法在 onCreateView 中执行返回
     * @return fragment 的 view
     */
    protected abstract View initView();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    /**
     * 得到Presenter对象
     * @return mPresenter对象
     */
    public P getPresenter(){
        return mPresenter;
    }

    private void initFragment() {
        // 创建presenter
        mPresenter = attachPresenter();
        // 贴上view
        mPresenter.attachView((V) this);
    }

    /**
     * 创建presenter，需要继承BasePresenter
     * @return 自定义的presenter
     */
    protected abstract P attachPresenter();

    /**
     * 放在主线程中执行的任务
     * @param r runnable
     */
    public void post(Runnable r){
        getBaseActivity().post(r);
    }

    /**
     * 得到FragmentActivity 对象并强转成BaseAppCompatActivity 对象
     * @return BaseAppCompatActivity 对象
     */
    public BaseAppCompatActivity getBaseActivity(){
        return (BaseAppCompatActivity) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // todo onDestroy方法不是一直都会执行，需要加上软引用。
        mPresenter = null;
    }
}
