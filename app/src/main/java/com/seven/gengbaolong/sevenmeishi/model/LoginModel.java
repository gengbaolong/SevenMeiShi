package com.seven.gengbaolong.sevenmeishi.model;

import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.OauthUserEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public class LoginModel {

    public OkHttpRequest login(String phone, String password, ResultCallback<OauthUserEntity> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PHONE_KEY, phone);
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, password);
        paramsMap.put(AppConstants.ParamKey.GRANT_TYPE_KEY, AppConstants.ParamDefaultValue.GRANT_TYPE);
        return ApiClient.create(AppConstants.RequestPath.OAUTH, paramsMap).tag("").post(callback);
    }

}
