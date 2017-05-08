package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.google.gson.JsonElement;
import com.seven.gengbaolong.sevenmeishi.app.App;
import com.seven.gengbaolong.sevenmeishi.bean.OauthUserEntity;
import com.seven.gengbaolong.sevenmeishi.model.ResetPasswordModel;
import com.seven.gengbaolong.sevenmeishi.presenter.ResetPasswordPresenter;
import com.seven.gengbaolong.sevenmeishi.view.ResetPasswordView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public class ResetPasswordPresenterImpl implements ResetPasswordPresenter {
    ResetPasswordView view;
    ResetPasswordModel model;

    public ResetPasswordPresenterImpl(ResetPasswordView view){
        this.view = view;
        model = new ResetPasswordModel();
    }

    @Override
    public void resetPassword(String verify_code, String phone, String pwd) {
        model.resetPassword(phone, pwd, verify_code, new ResultCallback<OauthUserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showResetError(e.getMessage());
            }

            @Override
            public void onResponse(OauthUserEntity response) {
                App.getInstance().setOauth(response);
                view.showSuccess();
            }
        });
    }

    @Override
    public void getVerifySMS(String phone, String pwd) {
        model.getVerifySMS(phone, pwd, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showMsg(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                view.showSmsSuccess();
            }
        });
    }
}
