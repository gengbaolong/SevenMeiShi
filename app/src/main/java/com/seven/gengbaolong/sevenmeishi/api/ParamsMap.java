package com.seven.gengbaolong.sevenmeishi.api;

import android.text.TextUtils;

import com.seven.gengbaolong.sevenmeishi.app.AppConstants;

import org.sunger.net.utils.DeviceUtils;

import java.util.HashMap;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public class ParamsMap extends HashMap<String, String> {
    public ParamsMap() {
        put(AppConstants.ParamKey.CLIENT_ID_KEY, AppConstants.ParamDefaultValue.CLIENT_ID);
        put(AppConstants.ParamKey.CLIENT_SECRET_KEY, AppConstants.ParamDefaultValue.CLIENT_SECRET);
        put(AppConstants.ParamKey.DEVICE_ID_KEY, DeviceUtils.getDeviceId());
        put(AppConstants.ParamKey.LANGUAGE_KEY, AppConstants.ParamDefaultValue.LANGUAGE);
        put(AppConstants.ParamKey.MODEL_KEY, DeviceUtils.getModel());
    }

    public void put(String key, int value) {
        super.put(key, value + "");
    }

    @Override
    public String put(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value))
            return  "";
        return super.put(key, value);
    }
}
