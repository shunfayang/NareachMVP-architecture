package com.moose.nareachmvp.presenter;

import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.iview.interfaces.IActivityView;
import com.moose.nareachmvp.imodule.impl.RegisterModule;

/**
 * Created by Moose Yang on 2016/1/18.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class RegisterPresenter extends BasePresenter<RegisterPresenter.IRegisterView> {

    private IRegisterModule mModule;

    public RegisterPresenter() {
        this.mModule = new RegisterModule();
    }

    public UserBean createUserBean() {
        UserBean bean = new UserBean();
        final String username = mView.getUsername();
        final String password = mView.getPassword();
        bean.username = username;
        bean.password = password;
        return bean;
    }

    public void sign() {
        mView.hideSoftInput();
        mModule.sign(createUserBean(), new SignRegCallBack() {

            @Override
            public void success(UserBean bean) {
                // 登录成功
                mView.showToast("欢迎您，" + bean.username);
                mView.finishActivity();
            }

            @Override
            public void failed() {
                mView.showToast("登录失败了 > 0 <");
            }
        });
        // todo 处理登录结果
    }

    public void register() {
        mView.hideSoftInput();
        mView.clearPassword();
        mModule.register(createUserBean(), new SignRegCallBack() {

            @Override
            public void success(UserBean bean) {
                // 如果这里是访问网络去登录，那么应该也是在子线程中，不能直接show，还是说，在view中每次执行view的操作都放在主线程？
                // 注册成功
                post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showToast("再次输入密码就可以登陆啦~");
                    }
                });
            }

            @Override
            public void failed() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showToast("注册失败哟~");
                    }
                });
            }
        });
        // todo 处理注册结果
    }

    // =================================================================
    //                      以下是Module和View接口、callback
    // =================================================================

    public interface IRegisterModule {

        void sign(UserBean bean, SignRegCallBack signRegCallBack);

        void register(UserBean bean, SignRegCallBack signRegCallBack);

    }

    public interface SignRegCallBack {
        void success(UserBean bean);

        void failed();
    }

    public interface IRegisterView extends IActivityView {

        void clearUserName();

        void clearPassword();

        void hideSoftInput();

        String getUsername();

        String getPassword();

    }
}
