package com.moose.nareachmvp.presenter;

import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.contract.AboutMeContract;
import com.moose.nareachmvp.imodule.impl.AboutMeModule;

/**
 * Created by Moose Yang on 2016/1/20.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class AboutMePresenter extends BasePresenter<AboutMeContract.View> {

    private AboutMeContract.Model mModel;

    public AboutMePresenter() {
        this.mModel = new AboutMeModule();
    }



}
