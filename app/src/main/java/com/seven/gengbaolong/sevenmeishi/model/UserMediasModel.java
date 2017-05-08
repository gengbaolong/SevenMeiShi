package com.seven.gengbaolong.sevenmeishi.model;

import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class UserMediasModel {
    public OkHttpRequest getMedias(int uid, int page, ResultCallback<List<MediaEntity>> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.UID_KEY, uid);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        return ApiClient.create(AppConstants.RequestPath.USER_MEDIAS, paramsMap).get(callback);
    }
}
