package com.yzx.xiaomusic.widget.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yzx.xiaomusic.R;

/**
 * Created by yzx on 2018/7/24.
 * Description
 */
public class YVideoPlayer extends StandardGSYVideoPlayer {
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


    @Override
    public void startPlayLogic() {
        super.startPlayLogic();
    }


    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        super.updateViewLayout(view, params);

        ImageView fullScreen = (ImageView) view.findViewById(R.id.fullscreen);
        fullScreen.setImageResource(R.drawable.aah);

    }
}
