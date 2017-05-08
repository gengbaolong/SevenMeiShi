package com.seven.gengbaolong.sevenmeishi.presenter;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public interface SignUpPresenter {
    void signUp(String phone, String pwd, String verify_code);

    void getVerifySMS(String phone, String pwd);

    void autoLogin(String phone, String pwd);
}
