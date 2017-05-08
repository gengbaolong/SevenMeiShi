package com.seven.gengbaolong.sevenmeishi.presenter;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public interface ResetPasswordPresenter {
    void resetPassword(String verify_code, String phone, String pwd);

    void getVerifySMS(String phone, String pwd);
}
