package com.moose.nareachmvp.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moose.nareachmvp.presenter.AboutMePresenter;

/**
 * Created by Moose Yang on 2016/1/20.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class AboutMeActivity
        extends CollapsedToolbarActivity<AboutMePresenter.IAboutMeView, AboutMePresenter>
        implements AboutMePresenter.IAboutMeView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView.setAdapter(new MyphotoAdapter());
    }

    @Override
    protected AboutMePresenter attachPresenter() {
        return new AboutMePresenter();
    }


    private class MyphotoAdapter extends RecyclerView.Adapter<MyphotoAdapter.MyPhotoHolder> {

        @Override
        public MyPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(AboutMeActivity.this);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64));
            return new MyPhotoHolder(tv);
        }

        @Override
        public void onBindViewHolder(MyPhotoHolder holder, int position) {
            holder.tv.setText("我是 " + position);
        }

        @Override
        public int getItemCount() {
            return 10;
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
