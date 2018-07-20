package com.yzx.xiaomusic.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.FormatUtil;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.simplelistenner.SimpleTextWathcer;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author yzx
 * @date 2018/7/19
 * Description   登录注册页面
 */
public class LoginFragment extends BaseFragment {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.et_userNam_l)
    EditText etUserNamL;
    @BindView(R.id.til_userName_l)
    TextInputLayout tilUserNameL;
    @BindView(R.id.et_password_l)
    EditText etPasswordL;
    @BindView(R.id.til_password_l)
    TextInputLayout tilPasswordL;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.et_userName_r)
    EditText etUserNameR;
    @BindView(R.id.til_userName_r)
    TextInputLayout tilUserNameR;
    @BindView(R.id.et_password_r)
    EditText etPasswordR;
    @BindView(R.id.til_password_r)
    TextInputLayout tilPasswordR;
    @BindView(R.id.et_rePassword_r)
    EditText etRePasswordR;
    @BindView(R.id.til_rePassword_r)
    TextInputLayout tilRePasswordR;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.ll_register)
    LinearLayout llRegister;
    @BindView(R.id.cv_content)
    CardView cvContent;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    //    private int hypotenuse;
    private int[] location;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        GlideUtils.loadImg(getContext(), R.drawable.ic_splash, ivBg);
        llLogin.setVisibility(View.VISIBLE);
        llRegister.setVisibility(View.INVISIBLE);
        etUserNamL.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                checkUserName(tilUserNameL, s);
            }
        });
        etUserNameR.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                checkUserName(tilUserNameR, s);
            }
        });

        etPasswordL.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                checkPassword(tilPasswordL, s);
            }
        });
        etPasswordR.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                checkPassword(tilPasswordR, s);
            }
        });
        etRePasswordR.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    tilRePasswordR.setErrorEnabled(true);
                    tilRePasswordR.setError("empty");
                } else if (TextUtils.equals(getEditContent(etPasswordR), getEditContent(etRePasswordR))) {
                    tilRePasswordR.setErrorEnabled(false);
                } else {
                    tilRePasswordR.setErrorEnabled(true);
                    tilRePasswordR.setError("两次密码不一致");
                }
            }
        });

    }

    /**
     * 检查密码
     *
     * @param textInputLayout
     * @param s
     */
    private void checkPassword(TextInputLayout textInputLayout, Editable s) {
        if (TextUtils.isEmpty(s)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("empty");
        } else if (FormatUtil.isPassword(String.valueOf(s))) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("请输入以字母开头至少6位的密码");
        }
    }

    /**
     * 检查用户名
     *
     * @param textInputLayout
     * @param s
     */
    private void checkUserName(TextInputLayout textInputLayout, Editable s) {
        if (TextUtils.isEmpty(s)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("empty");
        } else if (!FormatUtil.isMobileNO(String.valueOf(s))) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("请输入正确的手机号");
        } else {
            textInputLayout.setErrorEnabled(false);
        }
    }


    private String getEditContent(EditText editText) {
        return editText.getText().toString().trim();
    }

    @SuppressLint("CheckResult")
    @OnClick({R.id.btn_login, R.id.btn_register, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                showLoading();
                login();
                break;
            case R.id.btn_register:
                showLoading();
                register();
                break;
            case R.id.fab:

                location = new int[2];
                cvContent.getLocationInWindow(location);

                if (llRegister.getVisibility() == View.VISIBLE) {
                    Observable.timer(50, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aLong -> {
                                fab.animate().translationX(0).rotation(0).setDuration(300).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        fab.getLocationInWindow(location);

                                        Animator circularReveal = ViewAnimationUtils.createCircularReveal(llRegister,
                                                location[0] + fab.getWidth() / 2 - DensityUtils.dip2px(getContext(), 45),
                                                0, (float) Math.hypot(llRegister.getWidth() / 2, llRegister.getHeight()), 0);
                                        circularReveal.setDuration(500);
                                        circularReveal.addListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);
                                                llRegister.setVisibility(View.INVISIBLE);
                                            }
                                        });
                                        circularReveal.start();
                                    }
                                }).start();
                            });

                } else {
                    Observable.timer(50, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aLong -> {
                                fab.animate().translationX(cvContent.getWidth() / 2).rotation(45).setDuration(300).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {

                                        Animator circularReveal = ViewAnimationUtils.createCircularReveal(llRegister, location[0] + cvContent.getWidth(),
                                                0, 0, (float) Math.hypot(llRegister.getWidth() / 2, llRegister.getHeight()));
                                        circularReveal.setDuration(500);
                                        circularReveal.addListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);
                                            }

                                            @Override
                                            public void onAnimationStart(Animator animation) {
                                                super.onAnimationStart(animation);
                                                llRegister.setVisibility(View.VISIBLE);
                                            }
                                        });
                                        circularReveal.start();
                                    }
                                }).start();
                            });

                }
                break;
        }
    }

    private void login() {

        if (TextUtils.isEmpty(getEditContent(etUserNamL)) || TextUtils.isEmpty(getEditContent(etPasswordL))) {
            showToast("用户名和密码不能为空");
            return;
        }
        if (tilUserNameL.isErrorEnabled() || tilPasswordL.isErrorEnabled()) {
            showToast("请输入正确的用户名和密码");
            return;
        }
        AVUser.logInInBackground(getEditContent(etUserNamL), getEditContent(etPasswordL), new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                hideLoading();
                if (e == null) {
                    showToast("登录成功");
                    EventBusUtils.post(new MessageEvent(MessageEvent.TYPE_USER_INFOR_CHANGED));
                    pop();
                } else {
                    showToast("登录失败" + e.toString());
                }
            }
        });
    }

    private void register() {
        if (TextUtils.isEmpty(getEditContent(etUserNameR)) || TextUtils.isEmpty(getEditContent(etPasswordR)) || TextUtils.isEmpty(getEditContent(etRePasswordR))) {
            showToast("用户名和密码不能为空");
            return;
        }
        if (tilUserNameR.isErrorEnabled() || tilPasswordR.isErrorEnabled() || tilRePasswordR.isErrorEnabled()
                || !TextUtils.equals(getEditContent(etPasswordR), getEditContent(etRePasswordR))) {
            showToast("按正确的格式填写");
            return;
        }
        // 新建 AVUser 对象实例
        AVUser user = new AVUser();
        // 设置用户名
        user.setUsername(getEditContent(etUserNameR));
        // 设置密码
        user.setPassword(getEditContent(etPasswordR));
        //设置电话
        user.setMobilePhoneNumber(getEditContent(etPasswordR));
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                hideLoading();
                if (e == null) {
                    showToast("注册成功");
                    EventBusUtils.post(new MessageEvent(MessageEvent.TYPE_USER_INFOR_CHANGED));
                    pop();
                } else {
                    showToast("注册失败" + e.toString());
                }
            }
        });
    }
}
