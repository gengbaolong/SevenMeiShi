package com.seven.gengbaolong.sevenmeishi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.app.AppUtils;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;

import org.sunger.net.widget.ShadowImageView;
import org.sunger.net.widget.TextImageView;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public class MediasAdapter extends BaseLoadMoreRecyclerAdapter<MediaEntity, MediasAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(MediaEntity entity);
    }

    private OnItemClickListener onItemClickListener;
    private Activity mActivity;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MediasAdapter(Activity activity) {
        this.mActivity = activity;
    }


    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, final int position) {
        MediaEntity entity = getItem(position);
        holder.mImageViewCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(getItem(position));
            }
        });
        holder.mTextViewLikesCount.setTextImageStart(18, R.mipmap.ic_thumb_up_gray_18dp, " " + entity.getLikes_count());//点赞数目
        holder.mTextViewRecommendCaption.setText(entity.getCaption());//
        Glide.with(mActivity)
                .load(entity.getCover_pic())
                .placeholder(R.mipmap.bg_video_default).fitCenter()
                .into(holder.mImageViewCover);
        AppUtils.loadSmallUserAvata(mActivity,getItem(position).getUser(), holder.mImageViewAvatar);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final BGABadgeImageView mImageViewAvatar;
        public final ShadowImageView mImageViewCover;
        public final TextImageView mTextViewLikesCount;
        public final TextView mTextViewRecommendCaption;

        public ViewHolder(View view) {
            super(view);
            mImageViewAvatar = (BGABadgeImageView) view.findViewById(R.id.imageView_avatar);
            mImageViewCover = (ShadowImageView) view.findViewById(R.id.imageview_cover_pic);
            mTextViewLikesCount = (TextImageView) view.findViewById(R.id.textView_likes_count);
            mTextViewRecommendCaption = (TextView) view.findViewById(R.id.textView_recommend_caption);
        }
    }
}
