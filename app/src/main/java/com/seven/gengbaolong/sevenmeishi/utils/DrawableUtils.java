package com.seven.gengbaolong.sevenmeishi.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.seven.gengbaolong.sevenmeishi.app.App;

/**
 * Created by gengbaolong on 2017/3/7.
 */

public class DrawableUtils {
    public static Drawable roundedBitmap(Bitmap bitmap) {
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(App.getInstance().getResources(), bitmap);
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCircular(true);
        circleDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
        return circleDrawable;
    }
}
