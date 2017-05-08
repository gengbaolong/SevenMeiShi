package com.seven.gengbaolong.sevenmeishi.view;

import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public interface HomeMediasView {
    void showError();

    void refreshView(List<MediaEntity> entities);

    void loadMoreView(List<MediaEntity> entities);
}
