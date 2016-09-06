package com.moose.nareachmvp.presenter;

import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.imodule.impl.UserPhotoModule;
import com.moose.nareachmvp.imodule.interfaces.IUserPhotoModule;
import com.moose.nareachmvp.iview.interfaces.IMyPhotoView;

/**
 * Created by Moose Yang on 2016/1/20.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class MyPhotoPresenter extends BasePresenter<IMyPhotoView> {

    private IUserPhotoModule mModule;

    public MyPhotoPresenter(){
        this.mModule = new UserPhotoModule();
    }

}
