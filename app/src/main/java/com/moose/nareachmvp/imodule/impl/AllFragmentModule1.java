package com.moose.nareachmvp.imodule.impl;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.moose.nareachmvp.bean.ImageListBean;
import com.moose.nareachmvp.contract.AllFragmentContract;
import com.moose.nareachmvp.fragment.AllFragment;
import com.moose.nareachmvp.presenter.AllFragmentPresenter;
import com.moose.nareachmvp.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Moose Yang on 2016/1/19.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class AllFragmentModule1 implements AllFragmentContract.Model {


    private int showCount = 5;// 拉取得数据个数

    @Override
    public List<ImageListBean> pullData() {
        return pullData(0);
    }

    @Override
    public List<ImageListBean> pullData(int skip) {

        List<ImageListBean> imageListBeans = new ArrayList<ImageListBean>();
        AVQuery<AVObject> query = new AVQuery<AVObject>("_File");
        query.setLimit(showCount);
        if (skip != 0) {
            query.skip(skip);// 跳过已有的
        }
        query.orderByDescending("updatedAt");

        try {
            List<AVObject> avObjects = query.find();
            if (avObjects != null && avObjects.size() >= 0) {
                ImageListBean bean = null;
                for (AVObject av : avObjects) {
                    bean = new ImageListBean();
                    Map<String, Object> metaData = av.getMap("metaData");
                    Object scaleObj = metaData.get("scale");
                    float scale = 0.6f;
                    if (scaleObj != null) {
                        scale = Float.parseFloat(String.valueOf(scaleObj));
                    }
                    bean.url = av.get("url");
                    bean.scale = scale;
                    bean.date = av.getUpdatedAt().toString();
                    imageListBeans.add(bean);
                }
            }
        } catch (AVException e) {
            e.printStackTrace();
        }

        return imageListBeans;
    }

}
