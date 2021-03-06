package com.seven.gengbaolong.sevenmeishi.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.app.App;
import com.seven.gengbaolong.sevenmeishi.app.AppUtils;
import com.seven.gengbaolong.sevenmeishi.bean.OauthUserEntity;
import com.seven.gengbaolong.sevenmeishi.bean.SimpleUserEntity;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by gengbaolong on 2017/3/7.
 */

public class MainNavigationHeader implements View.OnClickListener {

    private BGABadgeImageView mImageViewAvatar;
    private TextView mTextViewNickName;
    private View mRelativeLayout1;
    private View mLinearLayout2;
    private TextView mTextViewVideosCount;
    private TextView mTextViewRepostsCount;
    private TextView mTextViewFriendsCount;
    private TextView mTextViewFollowersCount;
    private Activity mActivity;

    public MainNavigationHeader(Activity activity, NavigationView navigationView) {
        this.mActivity = activity;
        View headView = navigationView.getHeaderView(0);
        headView.findViewById(R.id.textView_login).setOnClickListener(this);
        headView.findViewById(R.id.textView_signup).setOnClickListener(this);
        mRelativeLayout1 = headView.findViewById(R.id.relative_layout1);
        mLinearLayout2 = headView.findViewById(R.id.linear_layout2);
        mTextViewVideosCount = (TextView) headView.findViewById(R.id.textView_videos_count);
        mTextViewRepostsCount = (TextView) headView.findViewById(R.id.textView_reposts_count);
        mTextViewFriendsCount = (TextView) headView.findViewById(R.id.textView_friends_count);
        mTextViewFollowersCount = (TextView) headView.findViewById(R.id.textView_followers_count);
        mImageViewAvatar = (BGABadgeImageView) headView.findViewById(R.id.imageView_avatar);
        mTextViewNickName = (TextView) headView.findViewById(R.id.textView_nickName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_login:
                mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.textView_signup:
                mActivity.startActivity(new Intent(mActivity, SignUpActivity.class));
                break;
            case R.id.imageView_avatar:
                mActivity.startActivity(new Intent(mActivity, UserInfoActivity.class));
                break;
        }
    }



    public void bindData() {
        OauthUserEntity oauthUserEntity = App.getInstance().getOauthUserEntity();
        if (oauthUserEntity != null) {
            mImageViewAvatar.setOnClickListener(this);
            mRelativeLayout1.setVisibility(View.GONE);
            mLinearLayout2.setVisibility(View.VISIBLE);
            SimpleUserEntity entity = oauthUserEntity.getUser();
            mTextViewNickName.setText(entity.getScreen_name());
            mTextViewVideosCount.setText(entity.getVideos_count() + "\n" + mActivity.getString(R.string.video));
            mTextViewRepostsCount.setText(entity.getReposts_count() + "\n" + mActivity.getString(R.string.reposts));
            mTextViewFriendsCount.setText(entity.getFriends_count() + "\n" + mActivity.getString(R.string.following));
            mTextViewFollowersCount.setText(entity.getFollowers_count() + "\n" + mActivity.getString(R.string.following));
            if (TextUtils.isEmpty(entity.getAvatar()))
                return;
            AppUtils.loadBigUserAvata(mActivity, entity, mImageViewAvatar);
        } else {
            mImageViewAvatar.setOnClickListener(null);
            mRelativeLayout1.setVisibility(View.VISIBLE);
            mLinearLayout2.setVisibility(View.GONE);
            mTextViewNickName.setText(R.string.msg_welcome);
            mImageViewAvatar.setImageResource(R.mipmap.ic_account_circle_48dp);
        }
    }

}
