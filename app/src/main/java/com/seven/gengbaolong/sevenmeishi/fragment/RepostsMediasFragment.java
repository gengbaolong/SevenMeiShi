package com.seven.gengbaolong.sevenmeishi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.seven.gengbaolong.sevenmeishi.presenter.RepostsMediasPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.RepostsMediasPresenterImpl;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class RepostsMediasFragment extends MediasFragment {
    private RepostsMediasPresenter mPresenter;

    public static Fragment newInstance(int uid) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_UID, uid);
        Fragment fragment = new RepostsMediasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        mPresenter = new RepostsMediasPresenterImpl(this);
        showLoading();
        onFragmentLoadMore();
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getRepostsMedias(getUid(), getCurrentPage());
    }
}
