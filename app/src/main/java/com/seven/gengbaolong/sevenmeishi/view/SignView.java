package com.seven.gengbaolong.sevenmeishi.view;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public interface SignView {

    void showVerifyError(String errorMsg);
    void showVerifySuccess();

    void showSignError(String errorMsg);
    void showSignSuccess();

    void showMsg(String msg);

}
