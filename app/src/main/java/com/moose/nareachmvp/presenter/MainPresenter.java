package com.moose.nareachmvp.presenter;

import android.content.Intent;

import com.moose.nareachmvp.NareachApp;
import com.moose.nareachmvp.R;
import com.moose.nareachmvp.activities.RegisterActivity;
import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.contract.MainContract;
import com.moose.nareachmvp.model.MainModule;

import rx.functions.Action1;

/**
 * Created by yang2 on 2016/1/7.
 */
public class MainPresenter extends BasePresenter<MainContract.View> {
    private MainContract.Model mMainModule;

    public MainPresenter() {
        this.mMainModule = new MainModule();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean uploadState = mMainModule.onActivityResult(requestCode, resultCode, data);
//        Main4ReplaceFragmentActivity activity = (Main4ReplaceFragmentActivity) this.mView;
//        MainActivity activity = (MainActivity) this.mView;
        if (!uploadState) {
            mView.showToast("没有找到图片");
        }
    }

    private boolean checkSign() {
        return mMainModule.checkSign();
    }

    public void openGallery() {
        mMainModule.requestOpenGalleryPermission(getActivity(), new OpenGalleryCallBack() {
            @Override
            public void success() {
                mView.openGallery();
            }
        });

    }

    public void signOutIn() {
        // 登录注册界面，或者退出登录
        mMainModule.signOutIn(new SignCallBack() {
            @Override
            public void signOut() {
                // 退出了
                mView.showToast("欢迎大爷再次光临哟~");
                mView.updateMenuSign(NareachApp.getMyResources().getString(R.string.moose_sign_out_in));
            }

            @Override
            public void signIn() {
                // 进入注册登录界面
                Intent intent = new Intent();
                intent.setClass(getActivity(), RegisterActivity.class);
                mView.startActivity(intent);
            }
        });
        mMainModule
                .signOutIn()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        // TODO
                    }
                });

    }

    public void updateUserState() {
        UserBean bean = mMainModule.updateUserState();
        mView.setUserInfo(bean);
    }

    public interface SignCallBack {
        void signOut();

        void signIn();
    }

    public interface OpenGalleryCallBack{
        void success();
    }

}
