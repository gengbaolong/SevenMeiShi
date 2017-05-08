package com.seven.gengbaolong.sevenmeishi.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class MProgressDialog extends ProgressDialog {
    public MProgressDialog(Context context) {
        super(context);
    }

    public MProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void delayedDismiss(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MProgressDialog.this.dismiss();
            }
        },500);
    }
}
