package com.moose.nareachmvp.presenter;

import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.bean.ImageListBean;
import com.moose.nareachmvp.contract.AllFragmentContract;
import com.moose.nareachmvp.imodule.impl.AllFragmentModule;

import java.util.List;

/**
 * Created by Moose Yang on 2016/1/17.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class AllFragmentPresenter extends BasePresenter<AllFragmentContract.View> {

    private AllFragmentContract.Model mModule;

    public AllFragmentPresenter() {
        this.mModule = new AllFragmentModule();
    }

    public void initPagerImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 初始化获取页面的图片
                final List<ImageListBean> imageListBeans = mModule.pullData();
                AllFragmentPresenter.super.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.setRecyclerViewAdapter(imageListBeans);
                    }
                });

            }
        }).start();
        // todo 替换RxAndroid
//        Observer<String> mObserver = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Log.d("moose","onCompleted.");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("moose","onError.");
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d("moose","onNext.");
//
//            }
//        };

    }

    public void loadMore(final int size) {
        // 获取更多数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<ImageListBean> imageListBeans = mModule.pullData(size);
                AllFragmentPresenter.super.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.addImageListData(imageListBeans);
                    }
                });

            }
        }).start();
    }

    public void updateList() {
        // 更新数据
        initPagerImage();
    }


}
