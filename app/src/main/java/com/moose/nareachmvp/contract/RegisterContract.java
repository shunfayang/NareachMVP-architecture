package com.moose.nareachmvp.contract;

import com.moose.nareachmvp.base.BaseModel;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.view.IActivityView;

/**
 * Created by Moose Yang on 2016/10/16.
 * todo Copy Right MooseStudio
 * 本类注释：
 */

public interface RegisterContract {

    // =================================================================
    //                      以下是Module和View接口、callback
    // =================================================================

    interface Model extends BaseModel{

        void sign(UserBean bean, SignRegCallBack signRegCallBack);

        void register(UserBean bean, SignRegCallBack signRegCallBack);

    }

    interface SignRegCallBack {
        void success(UserBean bean);

        void failed();
    }

    interface View extends IActivityView {

        void clearUserName();

        void clearPassword();

        void hideSoftInput();

        String getUsername();

        String getPassword();

    }
}
