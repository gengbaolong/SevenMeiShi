package com.seven.gengbaolong.sevenmeishi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.adapter.FriendshipsAdapter;
import com.seven.gengbaolong.sevenmeishi.bean.SimpleUserEntity;
import com.seven.gengbaolong.sevenmeishi.presenter.FriendshipsPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.FriendshipsPresenterImpl;
import com.seven.gengbaolong.sevenmeishi.view.FriendshipsView;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class FriendshipsFragment extends LoadMoreRecyclerFragemnt implements FriendshipsView {

    private static final String UID_KEY = "uid";
    private RecyclerView mRecyclerView;
    private FriendshipsAdapter mAdapter;
    private FriendshipsPresenter mPresenter;
    private String uid;

    public static Fragment newInstance(int uid){
        Bundle bundle = new Bundle();
        bundle.putString(UID_KEY, uid + "");
        FriendshipsFragment fragment = new FriendshipsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        uid = getArguments().getString(UID_KEY);
        mPresenter = new FriendshipsPresenterImpl(this);
        mRecyclerView = getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FriendshipsAdapter(getActivity());
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        setAdapter(mAdapter);
        onFragmentLoadMore();
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getFriends(uid, getCurrentPage());
    }


    @Override
    public void showError() {
        showLoadError(R.string.msg_error_load);
    }

    @Override
    public void showFriends(List<SimpleUserEntity> data) {
        showMoreData(data);
    }
}
