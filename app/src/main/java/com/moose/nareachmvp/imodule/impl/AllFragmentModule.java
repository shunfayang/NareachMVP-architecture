package com.moose.nareachmvp.imodule.impl;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.moose.nareachmvp.bean.ImageListBean;
import com.moose.nareachmvp.contract.AllFragmentContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moose Yang on 2016/1/19.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class AllFragmentModule implements AllFragmentContract.Model {


    private int showCount = 10;// 拉取得数据个数

    @Override
    public List<ImageListBean> pullData() {
        return pullData(0);
    }

    @Override
    public List<ImageListBean> pullData(int skip) {
        List<ImageListBean> imageListBeans = new ArrayList<ImageListBean>();

        AVQuery<AVObject> file = new AVQuery<AVObject>("Images");
        file.include("file");
        file.include("user");
        file.setLimit(showCount);
        if (skip != 0) {
            file.skip(skip);// 跳过已有的
        }
        file.orderByDescending("updatedAt");

        try {
            List<AVObject> avObjects = file.find();
            if (avObjects != null && avObjects.size() >= 0) {
                ImageListBean bean = null;
                for (AVObject av : avObjects) {
                    bean = new ImageListBean();
                    AVFile f = av.getAVFile("file");
                    AVUser user = av.getAVUser("user");

                    Object scaleObj = f.getMetaData("scale");
                    float scale = 0.6f;
                    if (scaleObj != null) {
                        scale = Float.parseFloat(String.valueOf(scaleObj));
                    }
                    bean.url = f.getUrl();
                    bean.scale = scale;
                    bean.date = av.getUpdatedAt().toLocaleString();
                    if(user != null){
                        bean.username = user.getUsername();
                    }


                    imageListBeans.add(bean);
                }
            }
        } catch (AVException e) {
            e.printStackTrace();
        }

        return imageListBeans;
    }

}
