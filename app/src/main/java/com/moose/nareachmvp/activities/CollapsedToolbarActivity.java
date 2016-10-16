package com.moose.nareachmvp.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.moose.nareachmvp.NareachApp;
import com.moose.nareachmvp.R;
import com.moose.nareachmvp.base.BaseAppCompatActivity;
import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.base.BaseView;

/**
 * Created by Moose Yang on 2016/1/21.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class CollapsedToolbarActivity<V extends BaseView, P extends BasePresenter<V>> extends BaseAppCompatActivity{

    protected Toolbar toolbar;
    protected RecyclerView mRecyclerView;
    protected CollapsingToolbarLayout mCollapsingLayout;
    protected AppBarLayout mAppBarLayout;
    protected CoordinatorLayout mCoordinatorLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {

        setContentView(R.layout.activity_my_photo);
        toolbar = (Toolbar) findViewById(R.id.moose_toolbar_my_photos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 其他控件
        mRecyclerView = (RecyclerView) findViewById(R.id.moose_recycler_view_images_my_photos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(new MyphotoAdapter());

        mCollapsingLayout = (CollapsingToolbarLayout) findViewById(R.id.moose_collapsing_toolbar_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.moose_app_bar_layout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.moose_coordinator_layout);
    }

    @Override
    protected View[] setToolBar() {
        mCollapsingLayout.setContentScrimColor(((NareachApp)getApplication()).getCurrentColor());
        return new View[0];
    }

    @Override
    protected BasePresenter attachPresenter() {
        return null;
    }
}
