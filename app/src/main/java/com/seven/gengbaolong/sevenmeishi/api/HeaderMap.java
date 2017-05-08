package com.seven.gengbaolong.sevenmeishi.api;

import com.seven.gengbaolong.sevenmeishi.app.App;
import com.seven.gengbaolong.sevenmeishi.app.AppConstants;
import com.seven.gengbaolong.sevenmeishi.bean.OauthUserEntity;

import java.util.HashMap;

/**
 * Created by gengbaolong on 2017/3/14.
 */

public class HeaderMap extends HashMap<String, String> {
    public HeaderMap(){
        OauthUserEntity entity = App.getInstance().getOauthUserEntity();
        if(null!= entity){
            put(AppConstants.ParamKey.ACCESS_TOKEN_KEY, entity.getAccess_token());
        }
    }
}
