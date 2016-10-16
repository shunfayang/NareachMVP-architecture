package com.moose.nareachmvp.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.moose.nareachmvp.R;
import com.moose.nareachmvp.base.BaseFragmentAppCompat;
import com.moose.nareachmvp.base.BasePresenter;
import com.moose.nareachmvp.base.BaseView;

/**
 * Created by Moose Yang on 2016/1/26.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public abstract class CollapsedToobarFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseFragmentAppCompat {


    private View view;

    protected Toolbar mToolbar;
    protected RecyclerView mRecyclerView;
    protected CollapsingToolbarLayout mCollapsingLayout;
    protected AppBarLayout mAppBarLayout;
    protected CoordinatorLayout mCoordinatorLayout;

    @Override
    protected abstract void initData();

    @Override
    protected View initView() {

        view = View.inflate(getBaseActivity(), R.layout.fragment_collapsed_toobar, null);
        mToolbar = (Toolbar) view.findViewById(R.id.moose_toolbar_my_photos);

        // 其他控件
        mRecyclerView = (RecyclerView) view.findViewById(R.id.moose_recycler_view_images_my_photos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
//        mRecyclerView.setAdapter(new MyphotoAdapter());

        mCollapsingLayout = (CollapsingToolbarLayout) view.findViewById(R.id.moose_collapsing_toolbar_layout);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.moose_app_bar_layout);
        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.moose_coordinator_layout);
        initMyView();
        getBaseActivity().setSupportActionBar(mToolbar);
//        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return view;
    }
//    protected View[] setToolBar() {
//        mCollapsingLayout.setContentScrimColor(getBaseActivity().getNareachApp().getCurrentColor());
//        return new View[0];
//    }

    /**
     * 设置adapter等
     */
    protected abstract void initMyView();

    @Override
    protected abstract BasePresenter attachPresenter();
}
