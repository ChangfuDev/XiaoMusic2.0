package com.yzx.xiaomusic.ui.mv;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.kingja.loadsir.core.LoadService;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.mv.MvInfo;
import com.yzx.xiaomusic.widget.video.BaseVideoActivity;
import com.yzx.xiaomusic.widget.video.StandardVideoPlayer;

import butterknife.BindView;

import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.widget.video.StandardVideoPlayer.DEFINITION_1080P;
import static com.yzx.xiaomusic.widget.video.StandardVideoPlayer.DEFINITION_240P;
import static com.yzx.xiaomusic.widget.video.StandardVideoPlayer.DEFINITION_480P;
import static com.yzx.xiaomusic.widget.video.StandardVideoPlayer.DEFINITION_720P;

/**
 * @author yzx
 * @date 2018/7/23
 * Description
 */
public class MvDetailsActivity extends BaseVideoActivity<StandardVideoPlayer, MvDetailsPresenter> {

    @BindView(R.id.yPlayer)
    StandardVideoPlayer yPlayer;
    private String mvId;
    OrientationUtils orientationUtils;
    public LoadService loadService;

    @Override
    protected MvDetailsPresenter getPresenter() {
        return new MvDetailsPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.activity_mv;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        Intent intent = getIntent();
        mvId = intent.getStringExtra(KEY_ID);

        yPlayer.getCurrentPlayer().setEnlargeImageRes(R.drawable.aah);
        yPlayer.getCurrentPlayer().setShrinkImageRes(R.drawable.aaj);
        //控制
        yPlayer.setOnBottomContainerVisibleListener(enable -> {
            full(!enable);
        });
        initVideoBuilderMode();
        mPresenter.getMv(mvId);
    }


    /**
     * 选择builder模式
     */
    @Override
    public void initVideoBuilderMode() {
        initVideo();
        getGSYVideoOptionBuilder().
                setVideoAllCallBack(this)
                .build(getGSYVideoPlayer());
    }

    public void setData(MvInfo mvInfo) {
        MvInfo.DataBean data = mvInfo.getData();

        String mostClearUrl = getMostClearUrl(data.getBrs());
        if (TextUtils.isEmpty(mostClearUrl)) {
            return;
        }
        //设置清晰度
        yPlayer.setDefinition(getDefinitionByBrs(data.getBrs()));
        getGSYVideoOptionBuilder().setVideoTitle(data.getName())
                .setUrl(mostClearUrl)
                .build(getGSYVideoPlayer());
        yPlayer.startPlayLogic();
    }

    /**
     * 根据码率获取清晰度描述
     *
     * @param brs
     * @return
     */
    private String getDefinitionByBrs(MvInfo.DataBean.BrsBean brs) {
        String p1080 = brs.get_$1080();
        if (!TextUtils.isEmpty(p1080)) {
            return DEFINITION_1080P;
        }

        String p720 = brs.get_$720();
        if (!TextUtils.isEmpty(p720)) {
            return DEFINITION_720P;
        }

        String p480 = brs.get_$480();
        if (!TextUtils.isEmpty(p480)) {
            return DEFINITION_480P;
        }

        String p240 = brs.get_$240();
        if (!TextUtils.isEmpty(p240)) {
            return DEFINITION_240P;
        }
        return null;
    }

    /**
     * 获取已有的最高清晰度
     *
     * @param brs
     * @return
     */
    private String getMostClearUrl(MvInfo.DataBean.BrsBean brs) {
        String p1080 = brs.get_$1080();
        if (!TextUtils.isEmpty(p1080)) {
            return p1080;
        }

        String p720 = brs.get_$720();
        if (!TextUtils.isEmpty(p720)) {
            return p720;
        }

        String p480 = brs.get_$480();
        if (!TextUtils.isEmpty(p480)) {
            return p480;
        }

        String p240 = brs.get_$240();
        if (!TextUtils.isEmpty(p240)) {
            return p240;
        }
        return null;
    }


    @Override
    protected void onPause() {
        super.onPause();
        yPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        yPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    @Override
    public StandardVideoPlayer getGSYVideoPlayer() {
        return yPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        return new GSYVideoOptionBuilder()
//                .setThumbImageView(imageView)
//                .setUrl(url)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                //打开动画
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

}
