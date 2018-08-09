package com.yzx.xiaomusic.widget.video;

import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.yzx.commonlibrary.base.mvp.CommonBasePresenter;
import com.yzx.xiaomusic.base.BaseMvpFragment;

/**
 * Created by yzx on 2018/8/9.
 * Description
 */
public abstract class BaseVideoFragment<T extends GSYBaseVideoPlayer, P extends CommonBasePresenter> extends BaseMvpFragment<P> implements VideoAllCallBack {

    protected boolean isPlay;

    protected boolean isPause;

    protected OrientationUtils orientationUtils;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    /**
     * 选择普通模式
     */
    public void initVideo() {
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(getActivity(), getGSYVideoPlayer());
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        if (getGSYVideoPlayer().getFullscreenButton() != null) {
            getGSYVideoPlayer().getFullscreenButton().setOnClickListener(v -> {
                showFull();
                clickForFullScreen();
            });
        }
    }

    /**
     * 选择builder模式
     */
    public void initVideoBuilderMode() {
        initVideo();
        getGSYVideoOptionBuilder().
                setVideoAllCallBack(this)
                .build(getGSYVideoPlayer());
    }

    public void showFull() {
        if (orientationUtils.getIsLand() != 1) {
            //直接横屏
            orientationUtils.resolveByClick();
        }
        //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
        getGSYVideoPlayer().startWindowFullscreen(getContext(), hideActionBarWhenFull(), hideStatusBarWhenFull());

    }


//    @Override
//    public void onBackPressedSupport() {
//        if (orientationUtils != null) {
//            orientationUtils.backToProtVideo();
//        }
//        if (GSYVideoManager.backFromWindowFull(this)) {
//            return;
//        }
//        super.onBackPressedSupport();
//    }


    @Override
    public boolean onBackPressedSupport() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(getContext())) {
            return false;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        getGSYVideoPlayer().getCurrentPlayer().onVideoPause();
        isPause = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        getGSYVideoPlayer().getCurrentPlayer().onVideoResume();
        isPause = false;
    }

    @Override
    public void onDestroy() {
        if (isPlay) {
            GSYBaseVideoPlayer currentPlayer = getGSYVideoPlayer().getCurrentPlayer();
            if (currentPlayer != null) {
                currentPlayer.release();
            }
        }
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            getGSYVideoPlayer().onConfigurationChanged(getActivity(), newConfig, orientationUtils, hideActionBarWhenFull(), hideStatusBarWhenFull());
        }
    }

    @Override
    public void onStartPrepared(String url, Object... objects) {

    }

    @Override
    public void onPrepared(String url, Object... objects) {

        if (orientationUtils == null) {
            throw new NullPointerException("initVideo() or initVideoBuilderMode() first");
        }
        //开始播放了才能旋转和全屏
        orientationUtils.setEnable(getDetailOrientationRotateAuto() && !isAutoFullWithSize());
        isPlay = true;
    }

    @Override
    public void onClickStartIcon(String url, Object... objects) {

    }

    @Override
    public void onClickStartError(String url, Object... objects) {

    }

    @Override
    public void onClickStop(String url, Object... objects) {

    }

    @Override
    public void onClickStopFullscreen(String url, Object... objects) {

    }

    @Override
    public void onClickResume(String url, Object... objects) {

    }

    @Override
    public void onClickResumeFullscreen(String url, Object... objects) {

    }

    @Override
    public void onClickSeekbar(String url, Object... objects) {

    }

    @Override
    public void onClickSeekbarFullscreen(String url, Object... objects) {

    }

    @Override
    public void onAutoComplete(String url, Object... objects) {

    }

    @Override
    public void onEnterFullscreen(String url, Object... objects) {

    }

    @Override
    public void onQuitFullscreen(String url, Object... objects) {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
    }

    @Override
    public void onQuitSmallWidget(String url, Object... objects) {

    }

    @Override
    public void onEnterSmallWidget(String url, Object... objects) {

    }

    @Override
    public void onTouchScreenSeekVolume(String url, Object... objects) {

    }

    @Override
    public void onTouchScreenSeekPosition(String url, Object... objects) {

    }

    @Override
    public void onTouchScreenSeekLight(String url, Object... objects) {

    }

    @Override
    public void onPlayError(String url, Object... objects) {

    }

    @Override
    public void onClickStartThumb(String url, Object... objects) {

    }

    @Override
    public void onClickBlank(String url, Object... objects) {

    }

    @Override
    public void onClickBlankFullscreen(String url, Object... objects) {

    }

    public boolean hideActionBarWhenFull() {
        return true;
    }

    public boolean hideStatusBarWhenFull() {
        return true;
    }

    /**
     * 播放控件
     */
    public abstract T getGSYVideoPlayer();

    /**
     * 配置播放器
     */
    public abstract GSYVideoOptionBuilder getGSYVideoOptionBuilder();

    /**
     * 点击了全屏
     */
    public abstract void clickForFullScreen();

    /**
     * 是否启动旋转横屏，true表示启动
     */
    public abstract boolean getDetailOrientationRotateAuto();

    /**
     * 是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏，注意，这时候默认旋转无效
     */
    public boolean isAutoFullWithSize() {
        return false;
    }


    public void full(boolean enable) {

        FragmentActivity activity = getActivity();

        if (activity == null) {
            return;
        }
        if (enable) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(lp);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attr);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
