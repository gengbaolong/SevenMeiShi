package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.seven.gengbaolong.sevenmeishi.app.App;
import com.seven.gengbaolong.sevenmeishi.bean.OauthUserEntity;
import com.seven.gengbaolong.sevenmeishi.model.LoginModel;
import com.seven.gengbaolong.sevenmeishi.presenter.LoginPresenter;
import com.seven.gengbaolong.sevenmeishi.view.LoginView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public class LoginPresenterImpl implements LoginPresenter {

    LoginModel model;
    LoginView loginView;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        model = new LoginModel();
    }


    @Override
    public void login(String phone, String pwd) {
        loginView.showLoading();
        model.login(phone, pwd, new ResultCallback<OauthUserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                loginView.showLoginFaile(e.getMessage());
            }

            @Override
            public void onResponse(OauthUserEntity response) {
                App.getInstance().setOauth(response);
                loginView.loginSuccess();
            }
        });
    }
}
