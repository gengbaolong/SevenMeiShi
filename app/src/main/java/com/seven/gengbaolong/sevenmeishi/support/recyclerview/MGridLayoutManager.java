package com.seven.gengbaolong.sevenmeishi.support.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.seven.gengbaolong.sevenmeishi.adapter.BaseLoadMoreRecyclerAdapter;

/**
 * Created by gengbaolong on 2017/3/9.
 */

public class MGridLayoutManager extends GridLayoutManager {
    public MGridLayoutManager(Context context, final int spanCount, final BaseLoadMoreRecyclerAdapter mAdapter) {
        super(context, spanCount);
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                switch (mAdapter.getItemViewType(position)){
                    case BaseLoadMoreRecyclerAdapter.TYPE_FOOTER:
                        return spanCount;
                    case BaseLoadMoreRecyclerAdapter.TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
                }
            }
        });

    }
}
