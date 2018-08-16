package com.yzx.xiaomusic.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.FormatUtil;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 * @date 2018/7/19
 * Description   登录注册页面
 */
public class LoginFragment extends BaseFragment {


    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

    }


    private String getEditContent(EditText editText) {
        return editText.getText().toString().trim();
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        String phoneNum = getEditContent(etPhoneNum);
        String password = getEditContent(etPassword);
        if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(password)) {
            showToast("信息不能为空");
            return;
        }

        if (!FormatUtil.isMobileNO(phoneNum)) {
            showToast("请输入正确的手机号");
            return;
        }

        AVUser.logInInBackground(phoneNum, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                hideLoading();
                if (e == null) {
                    showToast("登录成功");
                    EventBusUtils.post(new MessageEvent(MessageEvent.TYPE_USER_INFOR_CHANGED));
                    ((SupportFragment) getParentFragment()).pop();
                } else {
                    showToast("登录失败" + e.toString());
                }
            }
        });
    }
}
