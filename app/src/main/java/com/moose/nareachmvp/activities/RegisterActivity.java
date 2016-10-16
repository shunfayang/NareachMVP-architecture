package com.moose.nareachmvp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moose.nareachmvp.R;
import com.moose.nareachmvp.base.BaseAppCompatActivity;
import com.moose.nareachmvp.bean.UserBean;
import com.moose.nareachmvp.contract.RegisterContract;
import com.moose.nareachmvp.presenter.RegisterPresenter;
import com.moose.nareachmvp.utils.SoftInputManager;

public class RegisterActivity
        extends BaseAppCompatActivity<RegisterContract.View, RegisterPresenter>
        implements View.OnClickListener,RegisterContract.View {

    private EditText etPersonName;
    private EditText etPassword;
    private Button btnReg;
    private Button btnSign;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置监听
        setListener();

    }

    @Override
    protected View[] setToolBar() {
        View[] views = new View[1];
        views[0] = toolbar;
        return views;
    }


    private void setListener() {
        btnReg.setOnClickListener(this);
        btnSign.setOnClickListener(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPersonName = (EditText) findViewById(R.id.moose_et_person_name);
        etPassword = (EditText) findViewById(R.id.moose_et_password);
        btnReg = (Button) findViewById(R.id.moose_btn_register);
        btnSign = (Button) findViewById(R.id.moose_btn_signin);
    }


    @Override
    protected RegisterPresenter attachPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.moose_btn_signin:
                // 登录
                mPresenter.sign();
                break;
            case R.id.moose_btn_register:
                // 请求服务器登录或注册用户
                mPresenter.register();
                break;
        }
    }

    @Override
    public void clearUserName() {
        etPassword.setText("");
    }

    @Override
    public void clearPassword() {
        etPersonName.setText("");

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void hideSoftInput() {
        SoftInputManager.hideSoftInput(this,etPassword.getWindowToken());
    }

//    public void startActivity(Class<?> clazzActivity) {
//        Intent intent = new Intent();
//        intent.setClass(this, clazzActivity);
//        startActivity(intent);
//    }

    @Override
    public String getUsername() {
        return etPersonName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }
}
