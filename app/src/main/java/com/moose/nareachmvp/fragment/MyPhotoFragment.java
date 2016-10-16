package com.moose.nareachmvp.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moose.nareachmvp.R;
import com.moose.nareachmvp.base.BaseFragmentAppCompat;
import com.moose.nareachmvp.contract.MyPhotoContract;
import com.moose.nareachmvp.presenter.MyPhotoPresenter;
import com.moose.nareachmvp.utils.ScreenUtils;

/**
 * Created by Moose Yang on 2016/1/20.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class MyPhotoFragment
        extends BaseFragmentAppCompat<MyPhotoContract.View, MyPhotoPresenter>
        implements MyPhotoContract.View {
    private View view;

    protected Toolbar mToolbar;
    protected RecyclerView mRecyclerView;
    protected CollapsingToolbarLayout mCollapsingLayout;
    protected AppBarLayout mAppBarLayout;
    protected CoordinatorLayout mCoordinatorLayout;

    @Override
    protected View initView() {
        view = View.inflate(getBaseActivity(), R.layout.fragment_my_photo, null);
        mToolbar = (Toolbar) view.findViewById(R.id.moose_toolbar_my_photos);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.moose_recycler_view_images_my_photos);
        mCollapsingLayout = (CollapsingToolbarLayout) view.findViewById(R.id.moose_collapsing_toolbar_layout);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.moose_app_bar_layout);
        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.moose_coordinator_layout);
        initMyView();
        return view;
    }

    @Override
    protected void initData() {

    }

    protected void initMyView() {
        getBaseActivity().setSupportActionBar(mToolbar);

//        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setBackgroundColor(getBaseActivity().getNareachApp().getTintColor());

        mCollapsingLayout.setCollapsedTitleTextColor(0xffffffff);
        mCollapsingLayout.setExpandedTitleColor(0xffffffff);
        // 设置rv 的 adapter和manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mRecyclerView.setAdapter(new MyphotoAdapter());
        // 设置toolbar和包裹的颜色
        mCollapsingLayout.setContentScrimColor(getBaseActivity().getNareachApp().getCurrentColor());
        mToolbar.setTitle(getString(R.string.moose_about_us));
        mCollapsingLayout.setTitle(getString(R.string.moose_about_us));
        mCollapsingLayout.setTitleEnabled(true);

    }


    @Override
    protected MyPhotoPresenter attachPresenter() {
        return new MyPhotoPresenter();
    }

    private class MyphotoAdapter extends RecyclerView.Adapter<MyphotoAdapter.MyPhotoHolder> {

        @Override
        public MyPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(getBaseActivity());
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64));
            return new MyPhotoHolder(tv);
        }

        @Override
        public void onBindViewHolder(MyPhotoHolder holder, int position) {
            holder.tv.setText("我是 " + position);
        }

        @Override
        public int getItemCount() {
            return 200;
        }

        class MyPhotoHolder extends RecyclerView.ViewHolder {

            private TextView tv;

            public MyPhotoHolder(View itemView) {
                super(itemView);
                this.tv = (TextView) itemView;
            }
        }
    }
}
