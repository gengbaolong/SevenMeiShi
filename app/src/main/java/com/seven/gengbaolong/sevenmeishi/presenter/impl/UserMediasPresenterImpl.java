package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;
import com.seven.gengbaolong.sevenmeishi.model.UserMediasModel;
import com.seven.gengbaolong.sevenmeishi.presenter.UserMediasPresenter;
import com.seven.gengbaolong.sevenmeishi.view.MediasView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class UserMediasPresenterImpl implements UserMediasPresenter {
    UserMediasModel model;
    MediasView view;

    public UserMediasPresenterImpl(MediasView view){
        this.view = view;
        model = new UserMediasModel();
    }

    @Override
    public void getMedias(int uid, int page) {
        model.getMedias(uid, page, new ResultCallback<List<MediaEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<MediaEntity> response) {
                view.showVideo(response);
            }
        });
    }
}
