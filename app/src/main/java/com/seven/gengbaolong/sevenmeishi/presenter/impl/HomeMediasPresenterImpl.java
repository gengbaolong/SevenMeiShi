package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import android.os.Handler;

import com.seven.gengbaolong.sevenmeishi.app.AppUtils;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;
import com.seven.gengbaolong.sevenmeishi.bean.VideoItemEntity;
import com.seven.gengbaolong.sevenmeishi.model.HomeMediasModel;
import com.seven.gengbaolong.sevenmeishi.presenter.HomeMediasPresenter;
import com.seven.gengbaolong.sevenmeishi.utils.TimeUtils;
import com.seven.gengbaolong.sevenmeishi.view.HomeMediasView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

import java.util.Iterator;
import java.util.List;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public class HomeMediasPresenterImpl implements HomeMediasPresenter {

    //请求一次默认最短时间
    private static final int DEF_DELAY = (int) (1 * 1000);
    private HomeMediasModel model;
    private HomeMediasView view;

    public HomeMediasPresenterImpl(HomeMediasView view) {
        this.view = view;
        model = new HomeMediasModel();
    }

    @Override
    public void loadMore(int id, int type, int page, int count) {
        loadData(id, type, page, count);
    }

    @Override
    public void refresh(int id, int type, int count) {
        loadData(id, type, 1, count);
    }


    /**
     * 判断是否是热门视频
     * @param id
     * @param type
     * @return
     */
    private boolean isHot(int id,int type){
        return id == 1 || type == 1;
    }

    private void getVideo(int id, int type, int page, int count,ResultCallback<List<VideoItemEntity>> callback){
        if(isHot(id, type)){
            model.getHotVideoList( page, count, callback);
        }else{
            model.getVideoList(id, type, page, count, callback);
        }
    }



    private void loadData(int id, int type, final int page, int count) {
        //这里设置一个变量标记时间，避免UI刷新过快
        final long current_time = TimeUtils.getCurrentTime();
        getVideo(id, type, page, count, new ResultCallback<List<VideoItemEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<VideoItemEntity> response) {
                for (Iterator<VideoItemEntity> it = response.iterator(); it.hasNext(); ){
                    VideoItemEntity entity = it.next();
                    if(entity.getMedia() == null){
                        it.remove();
                    }
                }

                int delay = 0;
                if(TimeUtils.getCurrentTime() - current_time < DEF_DELAY){
                    //延时1秒
                    delay = DEF_DELAY;
                }
                updateView(AppUtils.toMediaList(response), delay, page );
            }
        });

    }

    private void updateView(final List<MediaEntity> mediaEntities, int delay,final int page) {
        //定时器延时刷新界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(page == 1){
                    view.refreshView(mediaEntities);
                }else{
                    view.loadMoreView(mediaEntities);
                }
            }
        }, delay);
    }

}
