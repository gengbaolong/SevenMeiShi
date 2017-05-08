package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.seven.gengbaolong.sevenmeishi.bean.FollowEntity;
import com.seven.gengbaolong.sevenmeishi.model.FollowersModel;
import com.seven.gengbaolong.sevenmeishi.presenter.FollowersPresenter;
import com.seven.gengbaolong.sevenmeishi.view.FollowersView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class FollowersPresenterImpl implements FollowersPresenter {
    private FollowersModel model;
    private FollowersView view;

    public FollowersPresenterImpl(FollowersView view) {
        this.view = view;
        model = new FollowersModel();
    }


    @Override
    public void getFollowers(String uid, int page) {
        model.getFollowers(uid, page, new ResultCallback<List<FollowEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<FollowEntity> response) {
                view.showFollows(response);
            }
        });
    }
}
