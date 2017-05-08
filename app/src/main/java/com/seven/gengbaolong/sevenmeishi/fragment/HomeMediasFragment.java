package com.seven.gengbaolong.sevenmeishi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.activity.MainActivity;
import com.seven.gengbaolong.sevenmeishi.activity.VideoPlayActivity;
import com.seven.gengbaolong.sevenmeishi.adapter.MediasAdapter;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;
import com.seven.gengbaolong.sevenmeishi.bean.VideoItemEntity;
import com.seven.gengbaolong.sevenmeishi.presenter.HomeMediasPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.HomeMediasPresenterImpl;
import com.seven.gengbaolong.sevenmeishi.support.recyclerview.MGridLayoutManager;
import com.seven.gengbaolong.sevenmeishi.view.HomeMediasView;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public  class HomeMediasFragment extends RefreshAndLoadFragment implements HomeMediasView, MediasAdapter.OnItemClickListener {
    private static final String KEY_VIDEO_ID = "id";
    private static final String KEY_VIDEO_TYPE = "type";
    private static final int PAGER_SIZE = 20;
    private static final int GRID_COLUMN = 2;
    static final int ACTION_NONE = 0;
    private MainActivity mainActivity;
    private RecyclerView mRecyclerView;
    private MediasAdapter mAdapter;
    private HomeMediasPresenter mPresenter;
    private int id;
    private int type;

    public static Fragment newInstance(int id, int type){
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_VIDEO_ID,id);
        bundle.putInt(KEY_VIDEO_TYPE,type);
        Fragment fragment  = new HomeMediasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.loadMore(id, type, getCurrentPage(), PAGER_SIZE );
    }

    @Override
    public void onRefreshData() {
        mPresenter.refresh(id, type, PAGER_SIZE);
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        id = getArguments().getInt(KEY_VIDEO_ID);
        type = getArguments().getInt(KEY_VIDEO_TYPE);
        mPresenter = new HomeMediasPresenterImpl(this);
        mRecyclerView = getRecyclerView();
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MediasAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new MGridLayoutManager(getActivity(), GRID_COLUMN, mAdapter));
        autoRefresh();
    }

    private void autoRefresh() {
        getSwipeRefreshWidget().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_REFRESH;
                getSwipeRefreshWidget().setRefreshing(true);
                mPresenter.refresh(id, type, PAGER_SIZE);
            }
        },600);
    }

    @Override
    public void onItemClick(MediaEntity entity) {
        startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
    }

    @Override
    public void showError() {
       currentState = ACTION_NONE;
        if(getSwipeRefreshWidget().isRefreshing()){
            getSwipeRefreshWidget().setRefreshing(false);
            mainActivity.showMsgBelowTabLayout(getString(R.string.msg_error_refresh));
        }else {
            mainActivity.showMsgInBottom(R.string.msg_error_load);
            mAdapter.setHasFooter(false);
        }
    }

    @Override
    public void refreshView(List<MediaEntity> entities) {
        showRefreshData(entities);
    }

    @Override
    public void loadMoreView(List<MediaEntity> entities) {
        showMoreData(entities);
    }
}
