package com.moose.nareachmvp.presenter;

import android.content.Intent;

import com.moose.nareachmvp.NareachApp;
import com.moose.nareachmvp.R;
import com.moose.nareachmvp.activities.Main4ReplaceFragmentActivity;
import com.moose.nareachmvp.activities.MainActivity;
import com.moose.nareachmvp.activities.RegisterActivity;
import com.moose.nareachmvp.base.BaseAppCompatActivity;
import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.imodule.impl.MainModule;
import com.moose.nareachmvp.iview.interfaces.IActivityView;

/**
 * Created by yang2 on 2016/1/7.
 */
public class MainPresenter extends BasePresenter<MainPresenter.IMainView> {
    private IMainModule mMainModule;

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

    // =================================================================
    // 以下是Module和View接口
    // =================================================================


    /**
     * Module
     */
    public interface IMainModule {
        /**
         * 上传图片
         */
        boolean uploadImg(String path);

        /**
         * 检查登录状态
         */
        boolean checkSign();

        /**
         * 更新用户相关的数据
         */
        UserBean updateUserState();

        /**
         * 登录登出
         *
         * @param activity
         */
        void signOutIn(SignCallBack activity);

        /**
         * 处理onActivityResult结果
         */
        boolean onActivityResult(int requestCode, int resultCode, Intent data);

        /**
         * 打开图库
         */
        void requestOpenGalleryPermission(BaseAppCompatActivity activity, OpenGalleryCallBack callBack);

    }

    /**
     * iView
     */
    public interface IMainView extends IActivityView {

        /**
         * 打开图库
         */
        void openGallery();

        /**
         * 设置用户相关的数据
         */
        void setUserInfo(UserBean bean);

        /**
         * 更新menu菜单中登录/注册按钮显示的文字
         */
        void updateMenuSign(String state);

    }
}
