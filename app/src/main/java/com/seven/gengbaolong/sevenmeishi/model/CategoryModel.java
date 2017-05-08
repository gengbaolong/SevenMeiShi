package com.seven.gengbaolong.sevenmeishi.model;

import com.seven.gengbaolong.sevenmeishi.api.ApiClient;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.CategoryEntity;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public class CategoryModel {

    public OkHttpRequest getCategory(ResultCallback<List<CategoryEntity>> callback){
        return ApiClient.create(AppConstants.RequestPath.CATEGOTY).tag("").get(callback);
    }
}
