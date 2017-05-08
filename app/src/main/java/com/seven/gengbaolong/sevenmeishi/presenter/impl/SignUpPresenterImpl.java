package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.google.gson.JsonElement;
import com.seven.gengbaolong.sevenmeishi.app.App;
import com.seven.gengbaolong.sevenmeishi.bean.OauthUserEntity;
import com.seven.gengbaolong.sevenmeishi.model.LoginModel;
import com.seven.gengbaolong.sevenmeishi.model.SignUpMdel;
import com.seven.gengbaolong.sevenmeishi.presenter.SignUpPresenter;
import com.seven.gengbaolong.sevenmeishi.view.SignView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpMdel model;
    private SignView view;

    public SignUpPresenterImpl(SignView view){
        this.view = view;
        this.model = model;
    }


    @Override
    public void signUp(final String phone, final String pwd, String verify_code) {
        model.signUp(phone, pwd, verify_code, new ResultCallback<JsonElement>(){
            @Override
            public void onError(Request request, Exception e) {
                view.showSignError(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                view.showSignSuccess();
                autoLogin(phone, pwd);
            }
        });
    }

    @Override
    public void getVerifySMS(String phone, String pwd) {
        model.getVerifySMS(phone, pwd, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showVerifyError(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                if (response.getAsBoolean()) {
                    view.showVerifySuccess();
                }
            }
        });

    }

    @Override
    public void autoLogin(String phone, String pwd) {
        new LoginModel().login(phone, pwd, new ResultCallback<OauthUserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showMsg("自动登录失败");
            }

            @Override
            public void onResponse(OauthUserEntity response) {
                App.getInstance().setOauth(response);
                view.showMsg("自动登录成功");
            }
        });
    }
}
