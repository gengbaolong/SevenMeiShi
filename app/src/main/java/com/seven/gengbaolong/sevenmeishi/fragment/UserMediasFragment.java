package com.seven.gengbaolong.sevenmeishi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.seven.gengbaolong.sevenmeishi.presenter.UserMediasPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.UserMediasPresenterImpl;

import static com.seven.gengbaolong.sevenmeishi.fragment.MediasFragment.KEY_UID;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class UserMediasFragment extends MediasFragment{
    UserMediasPresenter mPresenter;

    public static Fragment newInstance(int uid){
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_UID, uid);
        Fragment fragment = new UserMediasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        mPresenter = new UserMediasPresenterImpl(this);//MediasFragment已经实现了MediasView
        showLoading();
        onFragmentLoadMore();
    }


    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getMedias(getUid(), getCurrentPage());
    }
}
