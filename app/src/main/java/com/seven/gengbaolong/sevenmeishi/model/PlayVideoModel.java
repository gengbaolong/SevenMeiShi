package com.seven.gengbaolong.sevenmeishi.model;

import com.google.gson.JsonElement;
import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.HeaderMap;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.CommentEntity;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/14.
 */

public class PlayVideoModel {

    /**
     * 获取视频信息
     * @param id
     * @param callback
     * @return
     */
    public OkHttpRequest getMedias(int id, ResultCallback<MediaEntity> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        return ApiClient.create(AppConstants.RequestPath.MEDIAS, paramsMap).tag("").get(callback);
    }

    /**
     * 获取视频评论
     * @param id
     * @param page
     * @param callback
     * @return
     */
    public OkHttpRequest getComments(int id, int page, ResultCallback<List<CommentEntity>> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.COMMENTS, paramsMap, headerMap).tag("").get(callback);
    }

    /**
     * 视频点赞
     * @param id
     * @param callback
     * @return
     */
    public OkHttpRequest createVideoLike(int id, ResultCallback<JsonElement> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY ,id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.LIKES_VIDEO_CREATE, paramsMap, headerMap).tag("").post(callback);
    }


    /**
     * 取消视频点赞
     * @param id
     * @param callback
     * @return
     */
    public OkHttpRequest destroyVideoLike(int id, ResultCallback<JsonElement> callback){
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.LIKES_VIDEO_DESTORY, paramsMap, headerMap).tag("").post(callback);
    }


    /**
     * 评论点赞
     *
     * @param id
     * @param callback
     * @return
     */

    public OkHttpRequest createCommentLike(int id, ResultCallback<JsonElement> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.CREATE_COMMENTS_LIKE, paramsMap, headerMap).tag("").post(callback);
    }

    /**
     * 取消评论赞
     *
     * @param id
     * @param callback
     * @return
     */
    public OkHttpRequest destroyCommentLike(int id, ResultCallback<JsonElement> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.DESTORY_COMMENT_LIKE, paramsMap, headerMap).tag("").post(callback);
    }

}
