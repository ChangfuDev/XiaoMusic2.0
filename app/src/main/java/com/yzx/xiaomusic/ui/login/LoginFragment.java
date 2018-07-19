package com.yzx.xiaomusic.ui.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.utils.FormatUtil;
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
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.cv_content)
    CardView cvContent;
    @BindView(R.id.fab_register)
    FloatingActionButton fabRegister;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.til_userName)
    TextInputLayout tilUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.et_rePassword)
    EditText etRePassword;
    @BindView(R.id.til_rePassword)
    TextInputLayout tilRePassword;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;

    //是注册
    boolean isRegister;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        etUserName.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    tilUserName.setErrorEnabled(true);
                    tilUserName.setError("empty");
                } else if (!FormatUtil.isMobileNO(String.valueOf(s))) {
                    tilUserName.setErrorEnabled(true);
                    tilUserName.setError("请输入正确的手机号");
                } else {
                    tilUserName.setErrorEnabled(false);
                }
            }
        });

        etPassword.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    tilPassword.setErrorEnabled(true);
                    tilPassword.setError("empty");
                } else if (FormatUtil.isPassword(String.valueOf(s))) {
                    tilPassword.setErrorEnabled(false);
                } else {
                    tilPassword.setErrorEnabled(true);
                    tilPassword.setError("请输入以字母开头至少6位的密码");
                }
            }
        });

        etRePassword.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    tilRePassword.setErrorEnabled(true);
                    tilRePassword.setError("empty");
                } else if (FormatUtil.isPassword(String.valueOf(s))) {
                    if (TextUtils.equals(getEditContent(etPassword), getEditContent(etRePassword))) {
                        tilRePassword.setErrorEnabled(false);
                    } else {
                        tilRePassword.setErrorEnabled(true);
                        tilRePassword.setError("两次密码不一致");
                    }
                } else {
                    tilRePassword.setErrorEnabled(true);
                    tilRePassword.setError("请输入以字母开头至少6位的密码");
                }
            }
        });
    }

    @OnClick({R.id.btn_login, R.id.fab_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                if (isRegister) {
                    if (tilUserName.isErrorEnabled() || tilPassword.isErrorEnabled() || tilRePassword.isErrorEnabled()
                            || !TextUtils.equals(getEditContent(etPassword), getEditContent(etRePassword))) {
                        showToast("请输入正确的用户名和密码");
                        return;
                    }

                    AVUser user = new AVUser();// 新建 AVUser 对象实例
                    user.setUsername(getEditContent(etUserName));// 设置用户名
                    user.setPassword(getEditContent(etPassword));// 设置密码
                    user.setMobilePhoneNumber(getEditContent(etUserName));//设置电话
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                showToast("注册成功");
                                pop();
                            } else {
                                showToast("注册失败" + e.toString());
                            }
                        }
                    });
                } else {

                    if (tilUserName.isErrorEnabled() || tilPassword.isErrorEnabled()) {
                        showToast("请输入正确的用户名和密码");
                        return;
                    }
                    AVUser.logInInBackground(getEditContent(etUserName), getEditContent(etPassword), new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            if (e == null) {
                                showToast("登录成功");
                                pop();
                            } else {
                                showToast("登录失败" + e.toString());
                            }
                        }
                    });
                }
                break;
            case R.id.fab_register:
                btnLogin.animate().alpha(0).setDuration(300).start();
                tvSubTitle.animate().translationX(-tvSubTitle.getWidth()).setDuration(300).start();
                if (isRegister) {
                    tvForgetPassword.animate().alpha(0).setDuration(500).start();
                    tilRePassword.animate().scaleY(0).scaleX(0).alpha(0).setDuration(500).start();
                    tilRePassword.setVisibility(View.GONE);
                } else {
                    tvForgetPassword.animate().alpha(1).setDuration(500).start();
                    tilRePassword.setVisibility(View.VISIBLE);
                    tilRePassword.animate().scaleY(1).scaleX(1).alpha(1).setDuration(500).start();
                }
                Observable.timer(350, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            fabRegister.animate().rotation(isRegister ? 90 : 0).setDuration(300).start();
                            btnLogin.setText(isRegister ? "注册" : "登录");
                            btnLogin.animate().alpha(1).setDuration(300).start();
                            tvSubTitle.setText(isRegister ? "Register" : "Login");
                            tvSubTitle.animate().translationX(0).setDuration(300).start();
                        });
                isRegister = !isRegister;
                break;
        }
    }

    private String getEditContent(EditText editText) {
        return editText.getText().toString().trim();
    }

}
