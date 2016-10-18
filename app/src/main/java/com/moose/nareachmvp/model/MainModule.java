package com.moose.nareachmvp.model;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.moose.nareachmvp.NareachApp;
import com.moose.nareachmvp.R;
import com.moose.nareachmvp.base.BaseAppCompatActivity;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.contract.MainContract;
import com.moose.nareachmvp.presenter.MainPresenter;
import com.moose.nareachmvp.utils.Loger;

import java.io.File;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Moose Yang on 2016/1/17.
 * todo Copy Right MooseStudio
 * 本类注释：
 */

public class MainModule implements MainContract.Model {

    @Override
    public boolean uploadImg(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);
        // count scale
        float scale = (float) opts.outHeight / (float) opts.outWidth;
        String[] split = path.split("/");
        String name = split[split.length - 1];

        Log.d("moose", "图片宽：" + opts.outWidth + "; height = " + opts.outHeight + "; scale = " + scale + " ; username = " + name);

        try {
            final AVFile upFile = AVFile.withFile(name, new File(path));
            upFile.addMetaData("scale", scale);
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                upFile.addMetaData("username", currentUser.getUsername());
            }/* else {
                return false;
            }*/

            AVObject image = new AVObject("Images");
            image.put("file",upFile);

            image.put("user",AVUser.getCurrentUser());
            image.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if(e != null){
                        e.printStackTrace();
                    }else{
                        Loger.i("上传成功辣~");
                    }
                }
            });

//            upFile.saveInBackground(new SaveCallback() {
//                @Override
//                public void done(AVException e) {
//                    if (e != null) {
//                        e.printStackTrace();
//                    }else{
//                        AVObject image = new AVObject("Images");
//                        image.put("file",upFile);
//                        image.saveInBackground(new SaveCallback() {
//                            @Override
//                            public void done(AVException e) {
//
//                            }
//                        });
//                    }
//                }
//            });
//            upFile.save();;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean checkSign() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserBean updateUserState() {
        AVUser currentUser = AVUser.getCurrentUser();
        UserBean bean = new UserBean();
        String username = NareachApp.getMyResources().getString(R.string.moose_unsign);
        if (checkSign()) {
            username = currentUser.getUsername();
            bean.sign = true;
        }
        bean.username = username;
        return bean;
    }

    @Override
    public void signOutIn(MainPresenter.SignCallBack callBack) {
        if (checkSign()) {
            AVUser.logOut();
            if (AVUser.getCurrentUser() == null) {
                updateUserState();
                callBack.signOut();
            }
        } else {
            callBack.signIn();
        }
    }

    @Override
    public Observable<Boolean> signOutIn() {
        return Observable.just("signoutin").map(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                if(checkSign()){
                    AVUser.logOut();
                    if (AVUser.getCurrentUser() == null) {
                        updateUserState();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        // 得到图片否?
        String path = "";
        if (requestCode == 0 && null != data) {
            Uri uri = data.getData();
            if (uri != null) {
                String[] arrs = {MediaStore.Images.Media.DATA};
                Cursor cursor = NareachApp.getContext().getContentResolver().query(uri, arrs, null, null, null);
                if (cursor.moveToFirst()) {
                    path = cursor.getString(0);
                }
                cursor.close();
                Log.d("moose", "得到的路径：" + path);
            }
        }

        if (TextUtils.isEmpty(path)) {
            // null
//            Toast.makeText(activity, "没有找到图片", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // 上传图片
            uploadImg(path);
            return true;
        }
    }

    @Override
    public void requestOpenGalleryPermission(BaseAppCompatActivity activity, final MainPresenter.OpenGalleryCallBack callBack) {
        String checkPermission = "android.permission.READ_EXTERNAL_STORAGE";
        String[] requestPermissions = {"android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"};

        activity.requestPermission(
                checkPermission,
                requestPermissions,
                new BaseAppCompatActivity.RequestPermissionCallBack() {
                    @Override
                    public void hasPermission() {
                        callBack.success();
                    }
                }, 0);
    }
}
