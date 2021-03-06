package com.moose.nareachmvp.iview.interfaces;

import android.content.Intent;

/**
 * Created by Moose Yang on 2016/1/18.
 * todo Copy Right MooseStudio
 * 本类注释：Activity中都有的一些操作
 */
public interface IActivityView {
    /**
     * 打印吐司
     * @param msg
     */
    void showToast(String msg);

    /**
     * 关闭activity
     */
    void finishActivity();


    /**
     * 开启指定的Activity
     * @param intent 指定的意图
     */
    void startActivity(Intent intent);
}
