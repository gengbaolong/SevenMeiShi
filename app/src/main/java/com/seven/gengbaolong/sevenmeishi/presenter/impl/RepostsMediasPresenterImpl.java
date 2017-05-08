package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;
import com.seven.gengbaolong.sevenmeishi.model.RepostsMediasModel;
import com.seven.gengbaolong.sevenmeishi.presenter.RepostsMediasPresenter;
import com.seven.gengbaolong.sevenmeishi.view.MediasView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class RepostsMediasPresenterImpl implements RepostsMediasPresenter {
    RepostsMediasModel model;
    MediasView view;

    public RepostsMediasPresenterImpl(MediasView view){
        this.view = view;
        model = new RepostsMediasModel();
    }

    @Override
    public void getRepostsMedias(int uid, int page) {
        model.getReposts(uid, page, new ResultCallback<List<MediaEntity>>() {
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
