package com.moose.nareachmvp.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moose.nareachmvp.iview.interfaces.IMyPhotoView;
import com.moose.nareachmvp.presenter.MyPhotoPresenter;

public class UserPhotoActivity
        extends CollapsedToolbarActivity<IMyPhotoView, MyPhotoPresenter>
        implements IMyPhotoView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setAdapter(new MyphotoAdapter());
    }

    @Override
    protected View[] setToolBar() {
//        View[] views = new View[1];
        return super.setToolBar();
    }

    @Override
    protected MyPhotoPresenter attachPresenter() {
        return new MyPhotoPresenter();
    }

    private class MyphotoAdapter extends RecyclerView.Adapter<MyphotoAdapter.MyPhotoHolder>{

        @Override
        public MyPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(UserPhotoActivity.this);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64));
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

        class MyPhotoHolder extends RecyclerView.ViewHolder{

            private TextView tv;
            public MyPhotoHolder(View itemView) {
                super(itemView);
                this.tv = (TextView) itemView;
            }
        }
    }

}
