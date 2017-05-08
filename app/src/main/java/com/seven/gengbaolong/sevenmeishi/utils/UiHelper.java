package com.seven.gengbaolong.sevenmeishi.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.seven.gengbaolong.sevenmeishi.app.App;
import com.seven.gengbaolong.sevenmeishi.R;

import org.sunger.net.utils.ResourcesUtils;

/**
 * Created by gengbaolong on 2017/3/6.
 */

public class UiHelper {
    public static Dialog createListDialog(Context context, String[] data, DialogInterface.OnClickListener listener) {
        Dialog alertDialog = new AlertDialog.Builder(context).setItems(data,listener).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }


    /**
     * 创建一个简单的PopupWindow只显示一行文字
     *
     * @return
     */
    public static PopupWindow createSimplePopupWindow(String text, int backgroundColorId){
        View popView = LayoutInflater.from(App.getInstance()).inflate(R.layout.view_popwindow_text, null);
        TextView textView = (TextView) popView.findViewById(org.sunger.net.R.id.textView);
        int color = ResourcesUtils.getColor(popView, backgroundColorId);
        textView.setBackgroundColor(color);
        textView.setText(text);
        PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }
}
