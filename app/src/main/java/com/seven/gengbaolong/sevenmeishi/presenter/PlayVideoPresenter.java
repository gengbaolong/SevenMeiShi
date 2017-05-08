package com.seven.gengbaolong.sevenmeishi.presenter;

/**
 * Created by gengbaolong on 2017/3/14.
 */

public interface PlayVideoPresenter {

    void getMedia(int id);

    void refresh(int id);

    void loadMore(int id, int page);

    void createLikeVideo(int id);

    void destoryLikeVideo(int id);


    void createLikeComment(int id);

    void destoryLikeComment(int id);

}
