package com.yzx.xiaomusic.ui.mv;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.yzx.commonlibrary.base.mvp.CommonBaseMvpActivity;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.mv.MvInfo;
import com.yzx.xiaomusic.widget.video.YVideoPlayer;

import butterknife.BindView;

import static com.yzx.xiaomusic.Constant.KEY_ID;

/**
 * @author yzx
 * @date 2018/7/23
 * Description
 */
public class MvDetailsActivity extends CommonBaseMvpActivity<MvDetailsPresenter> {

    //    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.tv_subTitle)
//    TextView tvSubTitle;
//    @BindView(R.id.tb)
//    Toolbar tb;
//    @BindView(R.id.player)
//    StandardGSYVideoPlayer player;
    @BindView(R.id.normalPlayer)
    NormalGSYVideoPlayer normalPlayer;
    @BindView(R.id.yPlayer)
    YVideoPlayer yPlayer;
    private String mvId;

    //是否是全屏
    boolean fullScreen = false;
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

        // 重新加载逻辑
        loadService = LoadSir.getDefault().register(this, (Callback.OnReloadListener) v -> {
            // 重新加载逻辑
            mPresenter.getMv(mvId);
        });
        Intent intent = getIntent();
        mvId = intent.getStringExtra(KEY_ID);
        Log.i(TAG, "initView: " + mvId);


//        //设置返回键
//        player.getBackButton().setVisibility(View.VISIBLE);
//        //设置旋转
//        orientationUtils = new OrientationUtils(this, player);
//        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
//        final ImageView fullscreenButton = player.getFullscreenButton();
//        fullscreenButton.setOnClickListener(v -> orientationUtils.resolveByClick());
//        //是否可以滑动调整
//        player.setIsTouchWiget(true);
//
//        final boolean[] show = {true};
//        player.setOnClickListener(v -> {
//            player.startWindowFullscreen(MvDetailsActivity.this, show[0], show[0]);
//            show[0] = !show[0];
//        });
//        //设置返回按键功能
//        player.getBackButton().setOnClickListener(v -> onBackPressed());
        yPlayer.setAutoFullWithSize(true);
        mPresenter.getMv(mvId);
    }


    public void setData(MvInfo mvInfo) {
        MvInfo.DataBean data = mvInfo.getData();

        String mostClearUrl = getMostClearUrl(data.getBrs());
        if (TextUtils.isEmpty(mostClearUrl)) {
            return;
        }
//        tvTitle.setText(data.getBriefDesc());
        Log.i(TAG, "setData: " + mostClearUrl);
        //增加title
//        player.getTitleTextView().setVisibility(View.VISIBLE);
//        player.setUp(mostClearUrl, true, data.getName());
//        player.startPlayLogic();

//        normalPlayer.setUp(mostClearUrl, true, data.getName());
//        normalPlayer.startAfterPrepared();

        yPlayer.setUp(mostClearUrl, true, data.getName());
        yPlayer.startPlayLogic();

    }

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

//    @Override
//    protected void onPause() {
//        super.onPause();
//        player.onVideoPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        player.onVideoResume();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

//
//    @Override
//    public void onBackPressedSupport() {
//        //先返回正常状态
//        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            player.getFullscreenButton().performClick();
//            return;
//        }
//        //释放所有
//        player.setVideoAllCallBack(null);
//        super.onBackPressedSupport();
//    }

}
