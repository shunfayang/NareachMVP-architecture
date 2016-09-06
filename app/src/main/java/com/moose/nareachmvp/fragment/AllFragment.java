package com.moose.nareachmvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.moose.nareachmvp.R;
import com.moose.nareachmvp.base.BaseFragmentAppCompat;
import com.moose.nareachmvp.bean.ImageListBean;
import com.moose.nareachmvp.presenter.AllFragmentPresenter;
import com.moose.nareachmvp.utils.Loger;
import com.moose.nareachmvp.utils.ScreenUtils;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Moose Yang on 2016/1/17.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class AllFragment
        extends BaseFragmentAppCompat<AllFragmentPresenter.IAllFragmentView, AllFragmentPresenter>
        implements AllFragmentPresenter.IAllFragmentView {
    private static final java.lang.String TAG = "AllFragment";
    private View view;
    private RecyclerView mRecyclerView;
    private List<ImageListBean> mImageList;
    private ImageAdapter adapter;

    @Override
    protected void initData() {
        mPresenter.initPagerImage();
    }

    @Override
    protected View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_all, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.moose_recycler_view_images);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setLayoutManager(new AutoHeightLinearLayoutManager(getActivity()));
        setListViewPull();
        setRecyclerViewListener();
        return view;
    }

    private void setRecyclerViewListener() {
        // todo 设置RecyclerView的滚动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                /*if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
//                     底部，继续加载

                    }
                }*/
                Loger.d(TAG, "RecyclerView.SCROLL_INDICATOR_END  " + RecyclerView.SCROLL_INDICATOR_END
                        + "   recyclerView.getChildCount()   = " + recyclerView.getAdapter().getItemCount()
                        + " newState = " + newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.getVerticalScrollbarPosition() == recyclerView.getChildCount() - 1) {
                    mPresenter.loadMore(mImageList.size());

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setListViewPull() {
        final PtrFrameLayout ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.fragment_ptr_home_ptr_frame);
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.setPadding(0, (int) ScreenUtils.dp2px(getActivity(), 10), 0, (int) ScreenUtils.dp2px(getActivity(), 10));
        header.initWithString("nareach");
        header.setTextColor(getBaseActivity().getNareachApp().getCurrentColor());
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        // 设置回调
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                show();
                // 重新取数据
//                mPresenter.loadMore(mImageList.size());
                mPresenter.updateList();
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected AllFragmentPresenter attachPresenter() {
        return new AllFragmentPresenter();
    }

    @Override
    public void setRecyclerViewAdapter(List<ImageListBean> lists) {
        this.mImageList = lists;
        if (adapter == null) {
            adapter = new ImageAdapter();
            mRecyclerView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void notifyChangedRecyclerAdapter() {

    }

    @Override
    public void addImageListData(List<ImageListBean> addList) {
        mImageList.addAll(addList);
    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

        @Override
        public ImageAdapter.ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getActivity(), R.layout.item_image, null);
            ImageHolder holder = new ImageHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ImageHolder holder, int position) {
            // 设置值的一些操作放在这儿
            ImageListBean imageListBean = mImageList.get(position);

            // 得到RecyclerView的宽度
            int width = ScreenUtils.get(getActivity()).widthPixels - (int) ScreenUtils.dp2px(getActivity(), 20);
            int ottherHeight = 64 + 10 + 1 + 48;// 一个view 64px
            int marginHeight = (int) ScreenUtils.dp2px(getActivity(), 20);

            int height = (int) ((float) (width) * imageListBean.scale + 0.5f) + marginHeight;
//            int height = (int) ((float) (ScreenUtils.get(getActivity()).widthPixels) * imageListBean.scale + 0.5f);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    height + (int) ScreenUtils.dp2px(getActivity(), ottherHeight));
            Log.d("moose", "position = " + position + "scale = " + imageListBean.scale + " ; height = " + height);

            holder.itemView.setLayoutParams(params);
            holder.itemView.setTag(position);
            // 设置日期时间
            holder.ivDate.setText(imageListBean.date);
            holder.userName.setText(imageListBean.username);

            // 设置用户名和用户头像

            // 请求得到图片
            DrawableRequestBuilder<Object> drBuilder = Glide.with(getActivity())
                    .load(imageListBean.url);
//            drBuilder.dontAnimate();
            drBuilder.placeholder(R.mipmap.moose_naratu);
            drBuilder.into(holder.mImageView);
//            drBuilder.skipMemoryCache(true);
        }

        @Override
        public int getItemCount() {
            return mImageList.size();
        }

        class ImageHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;
            ImageView mIcon;
            TextView ivDate;
            TextView userName;

            public ImageHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.moose_iv);
                ivDate = (TextView) itemView.findViewById(R.id.moose_tv_date);
                userName = (TextView) itemView.findViewById(R.id.moose_tv_username);
                mIcon = (ImageView) itemView.findViewById(R.id.moose_user_icon);
            }


        }
    }
}
