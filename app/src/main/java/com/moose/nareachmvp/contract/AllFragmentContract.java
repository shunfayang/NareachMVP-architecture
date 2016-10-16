package com.moose.nareachmvp.contract;

import com.moose.nareachmvp.base.BaseModel;
import com.moose.nareachmvp.base.BaseView;
import com.moose.nareachmvp.bean.ImageListBean;

import java.util.List;

/**
 * Created by Moose Yang on 2016/10/16.
 * todo Copy Right MooseStudio
 * 本类注释：
 */

public interface AllFragmentContract {
    // =================================================================
    //                      以下是Module和View接口、callback
    // =================================================================

    interface Model extends BaseModel{
        List<ImageListBean> pullData();

        List<ImageListBean> pullData(int skip);
    }

    interface View extends BaseView {
        void setRecyclerViewAdapter(List<ImageListBean> lists);

        void notifyChangedRecyclerAdapter();

        void addImageListData(List<ImageListBean> addList);
    }
}
