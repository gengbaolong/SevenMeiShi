package com.seven.gengbaolong.sevenmeishi.view;

/**
 * 找回密码对应的接口
 * Created by gengbaolong on 2017/3/15.
 */

public interface ResetPasswordView {
    void showSuccess();

    void showResetError(String msg);


    void showSmsSuccess();


    void showMsg(String msg);
}
