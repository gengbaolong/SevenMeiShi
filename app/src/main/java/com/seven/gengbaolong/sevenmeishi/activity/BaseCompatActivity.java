package com.seven.gengbaolong.sevenmeishi.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.gc.materialdesign.widgets.Dialog;
import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.utils.UiHelper;

import org.sunger.net.support.okhttp.OkHttpClientManager;
import org.sunger.net.utils.NetWorkUtils;
import org.sunger.net.utils.SystemBarTintManager;
import org.sunger.net.widget.SolidToast;

/**
 * Created by gengbaolong on 2017/3/3.
 */

public class BaseCompatActivity extends AppCompatActivity {
    protected Handler taskHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStateBarColor(R.color.colorPrimaryDark);
    }

    /**设置状态栏颜色*/
    private void setStateBarColor(int resId){
        //判断版本是否在4.4以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            //沉浸式状态栏
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resId);
            tintManager.setStatusBarDarkMode(true, this);
        }
    }


    public void setUpCommonBackToolBar(int toolBarId, int titleId){
        setUpCommonBackToolBar(toolBarId, getString(titleId));
    }


    public void setUpCommonBackToolBar(int toolBarId, String title){
        Toolbar mToolbar = (Toolbar) findViewById(toolBarId);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        toobarAsBackButton(mToolbar);
    }

    /**
     * toolbar点击返回，模拟系统返回
     * 设置toolbar 为箭头按钮
     * app:navigationIcon="?attr/homeAsUpIndicator"
     *
     * @param toolbar
     */
    public void toobarAsBackButton(Toolbar toolbar) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public int getActionBarSize() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return 0;
    }


    private void showOpenWifiDialog() {
        final Dialog dialog = new Dialog(this, "美视提醒", "检测到当前网络没有连接是否开启网络？");
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkUtils.openWifi(BaseCompatActivity.this);
                dialog.dismiss();
            }
        });
        dialog.addCancelButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 检测网络
     */
    protected void checkNetWork() {
        if (!NetWorkUtils.isConnected(this)) {
            showOpenWifiDialog();
        }
        if (NetWorkUtils.isMobileType(this)) {
            showExitDialog("当前网络是移动网络，观看视频流量消耗会比较大，实现继续观看？取消将退出应用");
        }
    }


    private void showExitDialog(String msg) {
        final Dialog dialog = new Dialog(this, "美视提醒", msg);
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.addCancelButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                exitApp();
            }
        });
        dialog.show();
    }

    public void exitApp() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    public void showPopWindow(View parent, String text){
        final PopupWindow popupWindow = UiHelper.createSimplePopupWindow(text, R.color.colorPrimaryDark);
        popupWindow.showAsDropDown(parent);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, 1500);
    }


    public void showMsgInBottom(String msg) {
        SolidToast.make(this, msg, Gravity.BOTTOM).setBackgroundColorId(R.color.colorPrimaryDark).show();
    }

    public void showMsgInBottom(int msgSringId) {
        showMsgInBottom(getString(msgSringId));
    }

    protected <T extends View> T findView(int id) {
        return (T) super.findViewById(id);
    }

    public void cancelHttpRequest(String tag) {
        OkHttpClientManager.getInstance().cancelTag(tag);
    }

}
