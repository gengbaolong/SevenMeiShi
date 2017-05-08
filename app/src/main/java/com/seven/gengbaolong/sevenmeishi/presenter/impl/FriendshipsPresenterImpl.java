package com.seven.gengbaolong.sevenmeishi.presenter.impl;


import com.seven.gengbaolong.sevenmeishi.bean.SimpleUserEntity;
import com.seven.gengbaolong.sevenmeishi.model.FriendshipsModel;
import com.seven.gengbaolong.sevenmeishi.presenter.FriendshipsPresenter;
import com.seven.gengbaolong.sevenmeishi.view.FriendshipsView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class FriendshipsPresenterImpl implements FriendshipsPresenter {

    private FriendshipsView view;
    private FriendshipsModel model;

    public FriendshipsPresenterImpl(FriendshipsView view){
        this.view = view;
        model = new FriendshipsModel();
    }


    @Override
    public void getFriends(String uid, int page) {
        model.getFriends(uid, page, new ResultCallback<List<SimpleUserEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<SimpleUserEntity> response) {
                view.showFriends(response);
            }
        });
    }
}
