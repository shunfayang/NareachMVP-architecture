package com.moose.nareachmvp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.avos.avoscloud.AVOSCloud;
import com.moose.nareachmvp.utils.Loger;

/**
 * Created by Moose Yang on 2016/1/18.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class NareachApp extends Application {

    private static Context mContext;
    private static NareachApp mNareachApp;
    private String username = "未登录";
    // 整体的主题颜色
    private int currentColor = 0xff006064;
    private int tintColor = 0xff00838F;

    @Override
    public void onCreate() {
        super.onCreate();
        String clientKey = "wDPMpGNYCzesDPnNQN3N641g";
        String applicationId = "Vg2KvkpafHSbLP7c2u8nNcTP-gzGzoHsz";
        AVOSCloud.initialize(getApplicationContext(), applicationId, clientKey);
        mContext = getApplicationContext();
        mNareachApp = this;
        // 初始化应用颜色
        initAppThemeColor();
        Loger.i("moose","NareachApp onCreated.");
        Loger.i("moose","currentColor = " + currentColor);
    }

    private void initAppThemeColor() {
        // todo SharedPreferences中获取，或者从服务器获取。
//        this.currentColor = 0xff00BCD4;
//        this.tintColor = 0xff00BCD4;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Resources getMyResources() {
        return getContext().getResources();
    }

    public static NareachApp getApp() {
        return mNareachApp;
    }

    public void signin(String username) {
        this.username = username;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public int getTintColor() {
        return tintColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public void setTintColor(int tintColor) {
        this.tintColor = tintColor;
    }


}
