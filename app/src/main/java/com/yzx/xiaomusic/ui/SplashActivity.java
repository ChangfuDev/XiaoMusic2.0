package com.yzx.xiaomusic.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseActivity;
import com.yzx.xiaomusic.ui.main.MainActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * @author yzx
 * @date 2018/7/10
 * Description
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    ImageView ivBg;

    @Override
    protected int initContentViewId() {
        return R.layout.activity_splash;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(Bundle savedInstanceState) {
        Glide.with(this).load(R.drawable.ic_splash).into(ivBg);
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.scan_in, R.anim.scan_out);
                });
    }

}
