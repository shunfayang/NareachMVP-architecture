package com.moose.nareachmvp.imodule.impl;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.moose.nareachmvp.NareachApp;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.presenter.RegisterPresenter;

/**
 * Created by Moose Yang on 2016/1/18.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class RegisterModule implements RegisterPresenter.IRegisterModule {


    @Override
    public void sign(UserBean bean, final RegisterPresenter.SignRegCallBack signRegCallBack) {
        // 处理用户名和密码
        // 请求服务器登录
        final AVUser mUser = new AVUser();
        mUser.setUsername(bean.username);
        mUser.setPassword(bean.password);

        mUser.logInInBackground(bean.username, bean.password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e != null) {
                    e.printStackTrace();
                    signRegCallBack.failed();
                } else if (avUser != null) {
                    NareachApp nareachApp = (NareachApp) NareachApp.getApp();
                    nareachApp.signin(avUser.getUsername());
                    UserBean bean = new UserBean();
                    bean.username = avUser.getUsername();
                    signRegCallBack.success(bean);
                }
            }
        });
    }

    @Override
    public void register(UserBean bean, final RegisterPresenter.SignRegCallBack signRegCallBack) {
        // 处理用户名和密码
        // 请求服务器注册
        final AVUser mUser = new AVUser();
        mUser.setUsername(bean.username);
        mUser.setPassword(bean.password);
        mUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e != null) {
                    e.printStackTrace();
                    signRegCallBack.failed();
                } else {
                    // 注册成功
                    UserBean bean = new UserBean();
                    signRegCallBack.success(bean);
                }
            }
        });
    }
}
