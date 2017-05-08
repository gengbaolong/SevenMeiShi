package com.seven.gengbaolong.sevenmeishi.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.activity.VideoPlayActivity;
import com.seven.gengbaolong.sevenmeishi.adapter.MediasAdapter;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;
import com.seven.gengbaolong.sevenmeishi.view.MediasView;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class MediasFragment extends LoadMoreRecyclerFragemnt implements MediasView, MediasAdapter.OnItemClickListener {

    protected static final String KEY_UID = "uid";
    private RecyclerView recyclerView;
    private MediasAdapter mAdapter;
    private int uid;

    @Override
    protected void onFragmentCreate() {
        uid = getArguments().getInt(KEY_UID);
        recyclerView = getRecyclerView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MediasAdapter(getActivity());
        mAdapter.setHasMoreData(true);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        setAdapter(mAdapter);
    }

    @Override
    protected void onFragmentLoadMore() {

    }



    //MediasView的方法

    @Override
    public void showError() {
        showLoadError(R.string.msg_error_load);
    }

    @Override
    public void showVideo(List<MediaEntity> entities) {
        showMoreData(entities);
    }

    //OnItemClickListener方法

    @Override
    public void onItemClick(MediaEntity entity) {
        startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
    }


    public void showLoading() {
        mAdapter.setHasFooter(true);
    }

    public int getUid() {
        return uid;
    }
}
