package com.moose.nareachmvp.base;

import android.app.Activity;
import android.content.res.Resources;

/**
 * Created by Moose Yang on 2016/1/12.
 * todo Copy Right MooseStudio
 * 本类注释：Presenter 基类
 */
public abstract class BasePresenter<V> {

    protected V mView;

    public void attachView(V v){
        // 将view和presenter关联，
        this.mView = v;
    }

    public V getView(){
        return mView;
    }

    public BaseAppCompatActivity getActivity(){
        if(mView instanceof BaseFragmentAppCompat){
            return ((BaseFragmentAppCompat)mView).getBaseActivity();
        }
        return (BaseAppCompatActivity)mView;
    }

    public Resources getResources(){
        return getActivity().getResources();
    }

    public void post(Runnable r){
        BaseAppCompatActivity activity = getActivity();
        if(activity != null){
            activity.post(r);
        }
    }
}
