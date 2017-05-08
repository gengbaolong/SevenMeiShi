package com.seven.gengbaolong.sevenmeishi.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.seven.gengbaolong.sevenmeishi.activity.BaseCompatActivity;

/**
 * Created by gengbaolong on 2017/3/3.
 */

public class BaseFragment extends Fragment{
    private BaseCompatActivity holder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseCompatActivity) {
            holder = (BaseCompatActivity) context;
        }
    }

    public BaseCompatActivity getHolder() {
        if (holder == null) {
            throw new IllegalArgumentException("the acticity must be extends BaseCompatActivity");
        }
        return holder;
    }
}
