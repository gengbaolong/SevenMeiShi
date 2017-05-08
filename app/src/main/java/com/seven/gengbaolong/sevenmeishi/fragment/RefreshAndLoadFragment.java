package com.seven.gengbaolong.sevenmeishi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.utils.TimeUtils;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public abstract class RefreshAndLoadFragment<T> extends LoadMoreRecyclerFragemnt implements SwipeRefreshLayout.OnRefreshListener{
    protected final static int STATE_REFRESH = 2;
    private SwipeRefreshLayout mSwipeRefreshWidget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_video_refresh_loadmore_layout, container, false);
    }

    @Override
    protected void onFragmentCreate() {
        mSwipeRefreshWidget = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }


    public void showRefreshData(final List<T> dataList){
        int delay = 0;
        if(TimeUtils.getCurrentTime() - currentTime < DEF_DELAY){
            delay = DEF_DELAY;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                mSwipeRefreshWidget.setRefreshing(false);
                currentPage = 2;
                mAdapter.getList().clear();
                mAdapter.appendToList(dataList);
                mAdapter.notifyDataSetChanged();
            }
        },delay);
    }

    @Override
    public void onRefresh() {
        if(currentState == STATE_NORMAL){
            currentState = STATE_REFRESH;
            currentTime = org.sunger.net.utils.TimeUtils.getCurrentTime();
            mAdapter.setHasFooter(true);
            onRefreshData();
        } else {
            getHolder().showMsgInBottom(R.string.msg_waitting_loding);
        }
    }

    public abstract void onRefreshData();

    public SwipeRefreshLayout getSwipeRefreshWidget() {
        return mSwipeRefreshWidget;
    }
}
