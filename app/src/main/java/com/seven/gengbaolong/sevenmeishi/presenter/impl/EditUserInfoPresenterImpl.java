package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.seven.gengbaolong.sevenmeishi.app.App;
import com.seven.gengbaolong.sevenmeishi.bean.UserEntity;
import com.seven.gengbaolong.sevenmeishi.model.EditUserInfoModel;
import com.seven.gengbaolong.sevenmeishi.presenter.EditUserInfoPresenter;
import com.seven.gengbaolong.sevenmeishi.view.EditUserInfoView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class EditUserInfoPresenterImpl implements EditUserInfoPresenter {

    private EditUserInfoModel model;
    private EditUserInfoView view;

    public EditUserInfoPresenterImpl(EditUserInfoView view) {
        this.view = view;
        model = new EditUserInfoModel();
    }

    @Override
    public void update(String screen_name, String gender, String description, String filePath) {
        model.update(screen_name, gender, description, filePath, new ResultCallback<UserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onResponse(UserEntity response) {
                App.getInstance().getOauthUserEntity().setUser(response);
                view.updateSuccess();
            }
        });
    }
}
