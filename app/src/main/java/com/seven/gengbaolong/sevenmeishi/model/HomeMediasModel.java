package com.seven.gengbaolong.sevenmeishi.model;

import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.VideoItemEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public class HomeMediasModel {

    /**
     * 获取视频列表
     *
     * @param id
     * @param type
     * @param page
     * @param count
     * @param callback
     * @return
     */
    public OkHttpRequest getVideoList(int id, int type, int page, int count, ResultCallback<List<VideoItemEntity>> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY,id);
        paramsMap.put(AppConstants.ParamKey.TYPE_KEY, type);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put(AppConstants.ParamKey.COUNT_KEY, count);
        return ApiClient.create(AppConstants.RequestPath.VIDEO_LIST, paramsMap).tag("").get(callback);
    }


    /**
     * 获取热门视频列表
     *
     * @param page
     * @param count
     * @param callback
     * @return
     */
    public OkHttpRequest getHotVideoList(int page, int count, ResultCallback<List<VideoItemEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.COUNT_KEY, count);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        return ApiClient.create(AppConstants.RequestPath.HOT_VIDEO_LIST, paramsMap).tag("").get(callback);
    }
}
