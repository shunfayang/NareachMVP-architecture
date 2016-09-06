package com.moose.nareachmvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Moose Yang on 2016/1/18.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class UserBean implements Serializable {

    public boolean sign = false;

    public String username;
    public String icon;
    public String sex;

    public String password;
}
