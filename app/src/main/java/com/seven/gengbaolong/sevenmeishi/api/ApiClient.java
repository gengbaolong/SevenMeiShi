package com.seven.gengbaolong.sevenmeishi.api;

import com.seven.gengbaolong.sevenmeishi.app.AppConstants;

import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.Map;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public class ApiClient {

    public static OkHttpRequest.Builder create(String path, Map paramsMap, Map headerMap){
        return new OkHttpRequest.Builder().url(AppConstants.RequestPath.BASE_URL + path).params(paramsMap).headers(headerMap);
    }


    public static OkHttpRequest.Builder create(String path, Map paramsMap){
        return create(path, paramsMap, null);
    }

    public static OkHttpRequest.Builder create(String path) {
        return create(path, new ParamsMap());
    }


}
