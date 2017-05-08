package com.seven.gengbaolong.sevenmeishi.view;

import com.seven.gengbaolong.sevenmeishi.bean.FollowEntity;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public interface FollowersView {
    void showError();

    void showFollows(List<FollowEntity> data);
}
