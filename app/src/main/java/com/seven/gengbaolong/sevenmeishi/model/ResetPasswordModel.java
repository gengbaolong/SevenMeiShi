package com.seven.gengbaolong.sevenmeishi.model;

import com.google.gson.JsonElement;
import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.OauthUserEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * 找回密码对应的model
 * Created by gengbaolong on 2017/3/15.
 */

public class ResetPasswordModel {

    /**
     * 获取短信验证码
     *
     * @param phone
     * @param password
     * @param callback
     * @return
     */
    public OkHttpRequest getVerifySMS(String phone, String password, ResultCallback<JsonElement> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PHONE_KEY, phone);
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, password);
        paramsMap.put(AppConstants.ParamKey.TYPE_KEY, AppConstants.ParamDefaultValue.TYPR_RESET_PASSWORD);
        return ApiClient.create(AppConstants.RequestPath.SEND_VERIFY_CODE, paramsMap).tag("").post(callback);
    }

    /**
     * 重置密码
     *
     * @param phone
     * @param password
     * @param verify_code
     * @param callback
     * @return
     */
    public OkHttpRequest resetPassword(String phone, String password, String verify_code, ResultCallback<OauthUserEntity> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PHONE_KEY, phone);
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, password);
        paramsMap.put(AppConstants.ParamKey.VERIFY_CODE_KEY, verify_code);
        paramsMap.put(AppConstants.ParamKey.GRANT_TYPE_KEY, AppConstants.ParamDefaultValue.GRANT_TYPE);
        paramsMap.put(AppConstants.ParamKey.AUTO_LOGIN_KEY, AppConstants.ParamDefaultValue.AUTO_LOGIN);
        return ApiClient.create(AppConstants.RequestPath.RESET_PASSWORD, paramsMap).tag("").post(callback);
    }

}
