package com.yzx.xiaomusic.ui.mv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.mv.MvInfo;
import com.yzx.xiaomusic.widget.video.BaseVideoActivity;
import com.yzx.xiaomusic.widget.video.StandardVideoPlayer;

import butterknife.BindView;

import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.utils.CommonUtils.getFriendlyCount;
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
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_play_count)
    TextView tvPlayCount;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.nestScrollView)
    NestedScrollView nestScrollView;
    @BindView(R.id.tv_like_num)
    TextView tvLikeNum;
    @BindView(R.id.tv_collect_num)
    TextView tvCollectNum;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.tv_share_num)
    TextView tvShareNum;
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

        loadService = LoadSir.getDefault().register(nestScrollView, (Callback.OnReloadListener) v -> mPresenter.getMv(mvId));

        Intent intent = getIntent();
        mvId = intent.getStringExtra(KEY_ID);

        yPlayer.getCurrentPlayer().setEnlargeImageRes(R.drawable.aah);
        yPlayer.getCurrentPlayer().setShrinkImageRes(R.drawable.aaj);
        //控制
        yPlayer.setOnBottomContainerVisibleListener(enable -> full(!enable));
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

    @SuppressLint("DefaultLocale")
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

        tvTitle.setText(data.getName());
        tvDate.setText(String.format("发布：%s", data.getPublishTime()));
        tvPlayCount.setText(String.format("播放：%s", getFriendlyCount(data.getPlayCount())));
        tvDes.setText(data.getDesc());

        tvLikeNum.setText(getFriendlyCount(data.getLikeCount()));
        tvCollectNum.setText(getFriendlyCount(data.getSubCount()));
        tvCommentNum.setText(getFriendlyCount(data.getCommentCount()));
        tvShareNum.setText(getFriendlyCount(data.getShareCount()));
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
