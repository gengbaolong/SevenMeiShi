package com.seven.gengbaolong.sevenmeishi.view;

import com.seven.gengbaolong.sevenmeishi.bean.CommentEntity;
import com.seven.gengbaolong.sevenmeishi.bean.MediaEntity;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/14.
 */

public interface PlayVideoView {

    void showMediaData(MediaEntity mediaEntity);

    void showLoadMediaError();

    void refreshComment(List<CommentEntity> dataList);


    void showMoreComments(List<CommentEntity> dataList);

}
