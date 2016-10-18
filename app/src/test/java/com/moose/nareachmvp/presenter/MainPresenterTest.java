package com.moose.nareachmvp.presenter;

import com.moose.nareachmvp.contract.MainContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by yangshunfa on 2016/10/18.
 * tips:
 */
public class MainPresenterTest {

    @Mock
    private MainContract.View mView;
    @Mock
    private MainContract.Model mModel;
    private MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        // 初始化
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(mView);
        // 验证model中方法signOutIn是否执行
//        verify(mModel).signOutIn();
        // 模拟signOutIn方法返回null
        when(mModel.signOutIn()).thenReturn(null);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void signOutIn() throws Exception {

    }

}