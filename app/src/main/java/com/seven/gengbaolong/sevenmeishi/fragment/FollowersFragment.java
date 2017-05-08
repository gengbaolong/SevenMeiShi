package com.seven.gengbaolong.sevenmeishi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.adapter.FollowsAdapter;
import com.seven.gengbaolong.sevenmeishi.bean.FollowEntity;
import com.seven.gengbaolong.sevenmeishi.presenter.FollowersPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.FollowersPresenterImpl;
import com.seven.gengbaolong.sevenmeishi.view.FollowersView;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class FollowersFragment extends LoadMoreRecyclerFragemnt implements FollowersView {

    private static final String UID_KEY = "uid";
    private RecyclerView mRecyclerView;
    private FollowsAdapter mAdapter;
    private FollowersPresenter mPresenter;
    private String uid;

    public static Fragment newInatance(int uid) {
        Bundle bundle = new Bundle();
        bundle.putString(UID_KEY, uid + "");
        FollowersFragment fragment = new FollowersFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void onFragmentCreate() {
        uid = getArguments().getString(UID_KEY);
        mPresenter = new FollowersPresenterImpl(this);
        mRecyclerView = getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FollowsAdapter(getActivity());
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        setAdapter(mAdapter);
        mAdapter.setHasFooter(true);
        onFragmentLoadMore();
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getFollowers(uid, getCurrentPage());
    }

    @Override
    public void showError() {
        showLoadError(getString(R.string.msg_error_load));
    }

    @Override
    public void showFollows(List<FollowEntity> data) {
        showMoreData(data);
    }

}
