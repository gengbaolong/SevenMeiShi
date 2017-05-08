package com.seven.gengbaolong.sevenmeishi.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.gc.materialdesign.views.ProgressWheel;
import com.gc.materialdesign.views.Slider;
import com.seven.gengbaolong.sevenmeishi.R;

import org.sunger.net.utils.DataTypeUtils;
import org.sunger.net.utils.MediaPlayerUtils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by gengbaolong on 2017/3/10.
 */

public class VideoControllerView extends FrameLayout implements View.OnTouchListener, Slider.OnValueChangedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{

    private static final long delayMillis = 1500;
    private VideoView mVideoView;
    private ProgressWheel mProgressWheel;
    private ImageButton viewPlay;
    private Slider slider;
    private Handler handler = new Handler();
    private Context context;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            slider.setVisibility(GONE);
        }
    };


    public VideoControllerView(Context context) {
        super(context);
        initView(context);
    }

    public VideoControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public VideoControllerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
        View v = LayoutInflater.from(context).inflate(R.layout.view_video_controller, null);
        addView(v);
        mVideoView = findView(R.id.videoView);
        mVideoView.setOnTouchListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnCompletionListener(this);
        mProgressWheel = findView(R.id.progressWheel);
        mProgressWheel.startSpinning();
        viewPlay = findView(R.id.button_play);
        slider = findView(R.id.slider);
        slider.setOnValueChangedListener(this);
        slider.setOnNumberIndicatorConvert(new Slider.OnNumberIndicatorConvert() {
            @Override
            public String covert(long val) {
                return MediaPlayerUtils.getVideoDisplayTime(val);
            }
        });
        setVideoPlayButton();
    }

    private void setVideoPlayButton() {
        viewPlay.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_circle_selector));
        viewPlay.setVisibility(GONE);
        viewPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playStateChange();
            }
        });

    }

    private void playStateChange() {
        updateTimeTask();
    }

    private void updateTimeTask() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delayMillis);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        playStateChange();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        slider.setMin(0);
        slider.setMax(DataTypeUtils.toInt(mp.getDuration()));
        mp.setPlaybackSpeed(1.0f);
    }

    @Override
    public void onValueChanged(int value) {
        updateTimeTask();
        mVideoView.seekTo(value);
        mVideoView.start();
        viewPlay.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        //视频暂停中不显示缓冲进度
        if(isVideoPause()){
            return;
        }
        if(percent >= 90){
            mProgressWheel.setVisibility(View.INVISIBLE);
        }else{
            mProgressWheel.setVisibility(View.VISIBLE);
            mProgressWheel.setText(percent+"%");
        }
    }

    @TargetApi(21)
    @Override
    public void onCompletion(MediaPlayer mp) {
        viewPlay.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_replay_white_48dp, null));
        viewPlay.setVisibility(VISIBLE);
        viewPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.seekTo(0);
                setVideoPlayButton();
            }
        });

    }

    private <T extends View> T findView(int id){
        return (T)super.findViewById(id);
    }


    /**
     * 开始播放
     */
    public void start(){
        mVideoView.start();
    }

    /**
     * 暂停播放
     */
    public void pause(){
        mVideoView.pause();
    }

    /**
     * 释放资源
     */
    public void release(){
        mVideoView.stopPlayback();
    }

    public VideoView getVideoView(){
        return mVideoView;
    }

    private boolean isVideoPause(){
        return viewPlay.getVisibility() == View.VISIBLE;
    }

    public void setVideoPath(String videoUrl){
        mVideoView.setVideoPath(videoUrl);
    }

}
