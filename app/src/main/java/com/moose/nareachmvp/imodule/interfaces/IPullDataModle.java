package com.moose.nareachmvp.imodule.interfaces;

import com.moose.nareachmvp.bean.ImageListBean;

import java.util.List;

/**
 * Created by Moose Yang on 2016/2/23.
 * todo Copy Right MooseStudio
 * 本类注释：
 * 拉取数据的接口
 */
public interface IPullDataModle {

    List<ImageListBean> pullData();

    List<ImageListBean> pullData(int skip);
}
