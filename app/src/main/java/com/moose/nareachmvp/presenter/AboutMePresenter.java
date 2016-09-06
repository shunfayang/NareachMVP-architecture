package com.moose.nareachmvp.presenter;

import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.imodule.impl.AboutMeModule;

/**
 * Created by Moose Yang on 2016/1/20.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class AboutMePresenter extends BasePresenter<AboutMePresenter.IAboutMeView> {

    private IAboutMeModule mModule;

    public AboutMePresenter() {
        this.mModule = new AboutMeModule();
    }


    public interface IAboutMeModule {

    }

    public interface IAboutMeView {

    }

}
