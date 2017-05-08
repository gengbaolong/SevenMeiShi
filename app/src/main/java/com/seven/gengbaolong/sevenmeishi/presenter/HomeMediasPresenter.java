package com.seven.gengbaolong.sevenmeishi.presenter;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public interface HomeMediasPresenter {
    void loadMore(int id, int type, int page, int count);
    void refresh(int id, int type, int count);
}
