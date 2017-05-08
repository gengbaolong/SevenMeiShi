package com.seven.gengbaolong.sevenmeishi.view;

import com.seven.gengbaolong.sevenmeishi.bean.SimpleUserEntity;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public interface FriendshipsView {
    void showError();

    void showFriends(List<SimpleUserEntity> data);
}
