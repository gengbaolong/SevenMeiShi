package com.seven.gengbaolong.sevenmeishi.model;

import com.google.gson.JsonElement;
import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public class SignUpMdel {

    /**
     * 获取短信验证码
     * @param phone
     * @param pwd
     * @param callback
     * @return
     */
    public OkHttpRequest getVerifySMS(String phone, String pwd, ResultCallback<JsonElement> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PHONE_KEY, phone);
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, pwd);
        return ApiClient.create(AppConstants.RequestPath.SEND_VERIFY_CODE, paramsMap).tag("").post(callback);
    }

    /**
     * 用户登陆
     *
     * @param phone
     * @param password
     * @param verify_code
     * @param callback
     * @return
     */
    public OkHttpRequest signUp(String phone, String password, String verify_code, ResultCallback<JsonElement> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PHONE_KEY, phone);
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, password);
        paramsMap.put(AppConstants.ParamKey.VERIFY_CODE_KEY, verify_code);
        paramsMap.put(AppConstants.ParamKey.GRANT_TYPE_KEY, AppConstants.ParamDefaultValue.GRANT_TYPE);
        return ApiClient.create(AppConstants.RequestPath.OAUTH, paramsMap).tag("").post(callback);
    }

}
