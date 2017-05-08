package com.seven.gengbaolong.sevenmeishi.view;

import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public interface MediasView {
    void showError();

    void showVideo(List<MediaEntity> entities);
}
