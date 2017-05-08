package com.seven.gengbaolong.sevenmeishi.model;

import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.HeaderMap;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.FollowEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class FollowersModel {
    public OkHttpRequest getFollowers(String uid, int page, ResultCallback<List<FollowEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.UID_KEY, uid);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put(AppConstants.ParamKey.WITH_CAPTION_KEY, AppConstants.ParamDefaultValue.WITH_CAPTION);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.FOLLOWERS).headers(headerMap).params(paramsMap).get(callback);
    }
}
