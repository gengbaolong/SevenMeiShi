package com.seven.gengbaolong.sevenmeishi.app;

import android.app.Activity;
import android.text.TextUtils;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;
import com.seven.gengbaolong.sevenmeishi.bean.SimpleUserEntity;
import com.seven.gengbaolong.sevenmeishi.bean.VideoItemEntity;
import com.seven.gengbaolong.sevenmeishi.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by gengbaolong on 2017/3/7.
 */

public class AppUtils {


    public static void loadBigUserAvata(Activity activity, SimpleUserEntity entity, BGABadgeImageView imageView) {
        loadImage(activity, R.mipmap.ic_verified_account_24dp, entity, imageView);
    }

    public static void loadSmallUserAvata(Activity activity, SimpleUserEntity entity, BGABadgeImageView imageView) {
        loadImage(activity, R.mipmap.ic_verified_account_16dp, entity, imageView);
    }

    private static void loadImage(Activity activity, int v, SimpleUserEntity entity, BGABadgeImageView imageView) {
        if (TextUtils.isEmpty(entity.getAvatar()))
            return;
        BitmapTypeRequest builder = Glide.with(activity).load(entity.getAvatar()).asBitmap();
        if (entity.getVerified()) {
            GlideUtils.loadBadgeImage(v, builder, imageView);
        } else {
            GlideUtils.loadCircleImage(builder, imageView);
        }
    }


    public static List<MediaEntity> toMediaList(List<VideoItemEntity> dataList) {
        List<MediaEntity> data = new ArrayList<>();
        for (VideoItemEntity entity : dataList) {
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setId(entity.getMedia().getId());
            mediaEntity.setUser(entity.getMedia().getUser());
            mediaEntity.setLikes_count(entity.getMedia().getLikes_count());
            mediaEntity.setCaption(entity.getRecommend_caption());
            mediaEntity.setCover_pic(entity.getRecommend_cover_pic());
            data.add(mediaEntity);
        }
        return data;
    }
}
