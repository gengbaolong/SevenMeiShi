package com.seven.gengbaolong.sevenmeishi.model;

import android.util.Pair;

import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.api.HeaderMap;
import com.seven.gengbaolong.sevenmeishi.api.ParamsMap;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.UserEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.io.File;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class EditUserInfoModel {

    public OkHttpRequest update(String screen_name, String gender, String description, String filePath, ResultCallback<UserEntity> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.SCREEN_NAEM_KEY,screen_name);
        paramsMap.put(AppConstants.ParamKey.GENDER_KEY,gender);
        paramsMap.put(AppConstants.ParamKey.DESCRIPTION_KEY,description);
        HeaderMap headerMap = new HeaderMap();
        OkHttpRequest.Builder builder = ApiClient.create(AppConstants.RequestPath.USERS_UPDATE, paramsMap, headerMap).tag("");
        if (filePath != null) {
            return builder.files(new Pair<>("avatar", new File(filePath))).upload(callback);
        } else {
            return builder.post(callback);
        }
    }

}
