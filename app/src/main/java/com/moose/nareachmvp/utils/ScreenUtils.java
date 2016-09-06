package com.moose.nareachmvp.utils;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by yang2 on 2015/12/13.
 */
public class ScreenUtils {

    public static float getDis(Activity mActivity){
        return get(mActivity).density;

    }

    /**
     * dp转px
     * @param mActivity
     * @param dp
     * @return
     */
    public static float dp2px(Activity mActivity, float dp){
        return mActivity.getResources().getDisplayMetrics().density * dp + .5f;
    }

    /**
     * px转dp
     * @param mActivity
     * @param px
     * @return
     */
    public static float px2dp(Activity mActivity, float px){
        return px / mActivity.getResources().getDisplayMetrics().density + .5f;
    }

    public static DisplayMetrics get(Activity mActivity){
        DisplayMetrics outMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    public static Point point(Activity mActivity){
        Point outsize = new Point();
        mActivity.getWindowManager().getDefaultDisplay().getSize(outsize);
        return outsize;
    }

}
