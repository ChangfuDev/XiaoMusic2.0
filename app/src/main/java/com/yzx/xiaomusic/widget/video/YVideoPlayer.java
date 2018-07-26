package com.yzx.xiaomusic.widget.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.ui.mv.MvDetailsActivity;

/**
 * Created by yzx on 2018/7/24.
 * Description
 */
public class YVideoPlayer extends StandardGSYVideoPlayer {

    public static final String TAG = "yglYVideoPlayer";

    public static final String DEFINITION_1080P = "1080P";
    public static final String DEFINITION_720P = "超请";
    public static final String DEFINITION_480P = "高清";
    public static final String DEFINITION_240P = "清晰";
    //视频清晰度
    private TextView tvDefinition;
    //    private AppCompatSeekBar seekBar;
    private long duration;
//    private ProgressBar progressBar;
    private MvDetailsActivity activity;

    public YVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public YVideoPlayer(Context context) {
        super(context);
    }

    public YVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_video_y;
    }


    @Override
    protected void init(Context context) {
        super.init(context);

        tvDefinition = (TextView) findViewById(R.id.tv_definition);
//        progressBar = (ProgressBar) findViewById(R.id.bottom_progressbar);
//
//        mBottomContainer.setOnSystemUiVisibilityChangeListener(visibility -> {
//            Log.i(TAG, "init: " + visibility);
//        });
    }


//    @Override
//    public void onPrepared() {
//        super.onPrepared();
//
//        //全屏操作按钮图标
//        setEnlargeImageRes(R.drawable.aah);
//        setShrinkImageRes(R.drawable.aaj);
//
//        full(true);
////        setGSYVideoProgressListener(this);
//        duration = GSYVideoManager.instance().getDuration();
//        progressBar.setMax((int) duration);
//        mTotalTimeTextView.setText(String.format("/%s", com.yzx.xiaomusic.utils.TimeUtils.getFormatData(duration, FORMAT_MM_SS)));
//    }
//
//    @Override
//    public void onVideoResume() {
//        super.onVideoResume();
//        Log.i(TAG, "onVideoResume: ");
//
//    }

    /**
     * 根据状态更新状态体表
     */
    @Override
    protected void updateStartImage() {
        if (mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) mStartButton;
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                imageView.setImageResource(R.drawable.a03);
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                imageView.setImageResource(R.drawable.a02);
            } else {
                imageView.setImageResource(R.drawable.a02);
            }
        }
    }


//    @Override
//    public void onInfo(int what, int extra) {
//        super.onInfo(what, extra);
////        Log.i(TAG, "onInfo: " + what + "--extra:" + extra);
//    }
//
//    //状态
//    @Override
//    protected void resolveUIState(int state) {
//        super.resolveUIState(state);
//
////        Log.i(TAG, "resolveUIState: " + state);
//    }

//    @Override
//    protected void hideAllWidget() {
//        super.hideAllWidget();
//        full(true);
//        Log.i(TAG, "hideAllWidget: ");
//    }

//    @Override
//    protected void changeUiToPreparingShow() {
//        super.changeUiToPreparingShow();
//        Log.i(TAG, "changeUiToPreparingShow: ");
//        full(true);
//    }

//    @Override
//    protected void changeUiToPlayingShow() {
//        super.changeUiToPlayingShow();
//        full(false);
//        Log.i(TAG, "changeUiToPlayingShow: ");
//    }
//
//    @Override
//    protected void changeUiToPlayingClear() {
//        super.changeUiToPlayingClear();
//        full(true);
//        Log.i(TAG, "changeUiToPlayingClear: ");
//    }
//
//    @Override
//    protected void changeUiToPreparingShow() {
//        super.changeUiToPreparingShow();
//        Log.i(TAG, "changeUiToPreparingShow: ");
//    }
//
//
//    @Override
//    protected void changeUiToClear() {
//        super.changeUiToClear();
//        Log.i(TAG, "changeUiToClear: ");
//    }
//
//    @Override
//    protected void changeUiToNormal() {
//        super.changeUiToNormal();
//        Log.i(TAG, "changeUiToNormal: ");
//    }

    /**
     * 设置清晰度
     *
     * @param definition
     */
    public void setDefinition(String definition) {
        if (tvDefinition != null) {
            tvDefinition.setText(definition);
        }
    }


//    @Override
//    public void onBufferingUpdate(int percent) {
//        super.onBufferingUpdate(percent);
////        Log.i(TAG, "onBufferingUpdate: " + percent);
//        if (duration <= 0) {
//            return;
//        }
//        progressBar.setSecondaryProgress((int) (duration * percent / 100));
//    }

//    @Override
//    public void onProgress(int progress, int secProgress, int currentPosition, int duration) {
//
//        mCurrentTimeTextView.setText(com.yzx.xiaomusic.utils.TimeUtils.getFormatData(currentPosition, FORMAT_MM_SS));
//        progressBar.setProgress(currentPosition);
//    }

//    @Override
//    public void onError(int what, int extra) {
//        super.onError(what, extra);
//        Log.i(TAG, "onError: " + what + "---" + extra);
//    }
//
//    private void full(boolean enable) {
//        if (activity == null) {
//            return;
//        }
//        if (enable) {
//            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//            activity.getWindow().setAttributes(lp);
////            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        } else {
//            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
//            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            activity.getWindow().setAttributes(attr);
////            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
//    }
//
//    public void setActivity(MvDetailsActivity activity) {
//        this.activity = activity;
//        hideAllWidget();
//    }
}
