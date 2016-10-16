package com.moose.nareachmvp.contract;

import android.content.Intent;

import com.moose.nareachmvp.base.BaseAppCompatActivity;
import com.moose.nareachmvp.base.BaseModel;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.iview.interfaces.IActivityView;
import com.moose.nareachmvp.presenter.MainPresenter;

/**
 * Created by Moose Yang on 2016/10/16.
 * todo Copy Right MooseStudio
 * 本类注释：
 */

public interface MainContract {

    // =================================================================
    // 以下是Module和View接口
    // =================================================================


    /**
     * Module
     */
    public interface Model extends BaseModel{
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
        void signOutIn(MainPresenter.SignCallBack activity);

        /**
         * 处理onActivityResult结果
         */
        boolean onActivityResult(int requestCode, int resultCode, Intent data);

        /**
         * 打开图库
         */
        void requestOpenGalleryPermission(BaseAppCompatActivity activity, MainPresenter.OpenGalleryCallBack callBack);

    }

    /**
     * iView
     */
    public interface View extends IActivityView {

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
