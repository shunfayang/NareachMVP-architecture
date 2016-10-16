package com.moose.nareachmvp.contract;

import com.moose.nareachmvp.bean.ImageListBean;

import java.util.List;

/**
 * Created by Moose Yang on 2016/10/16.
 * todo Copy Right MooseStudio
 * 本类注释：
 */

public interface PullDataContract {
    interface IPullDataModle {

        List<ImageListBean> pullData();

        List<ImageListBean> pullData(int skip);
    }

}
