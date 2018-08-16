package com.yzx.xiaomusic.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.SignUpCallback;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.leancloud.SimpleSaveCallBack;
import com.yzx.xiaomusic.model.entity.eventbus.MessageEvent;
import com.yzx.xiaomusic.utils.EventBusUtils;
import com.yzx.xiaomusic.utils.FormatUtil;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by yzx on 2018/8/15.
 * Description
 */
public class RegisterFagment extends BaseFragment {
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_rePassword)
    EditText etRePassword;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

    }


    @OnClick(R.id.btn_register)
    public void onViewClicked() {

        String phoneNum = getEditContent(etPhoneNum);
        String password = getEditContent(etPassword);
        String rePassword = getEditContent(etRePassword);
        if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
            showToast("信息不能为空");
            return;
        }

        if (!FormatUtil.isMobileNO(phoneNum)) {
            showToast("请输入正确的手机号");
            return;
        }

        if (!TextUtils.equals(password, rePassword)) {
            showToast("两次密码不一致");
            return;
        }

        register(phoneNum, password);

    }


    private String getEditContent(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * 注册
     *
     * @param phoneNum
     * @param password
     */
    private void register(String phoneNum, String password) {

        // 新建 AVUser 对象实例
        AVUser user = new AVUser();
        // 设置用户名
        user.setUsername(phoneNum);
        // 设置密码
        user.setPassword(password);
        //设置电话
        user.setMobilePhoneNumber(phoneNum);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    createDefaultSongSheet();
                } else {
                    hideLoading();
                    showToast("注册失败" + e.toString());
                }
            }
        });
    }

    /**
     * 注册成功后，创建一个默认的喜欢的歌单
     */
    private void createDefaultSongSheet() {

        AVObject songSheet = new AVObject("SongSheetList");
        songSheet.put("name", "我喜欢的音乐");
        songSheet.put("creator", AVUser.getCurrentUser());
        songSheet.put("bgUrl", "http://lc-cghdjwlo.cn-n1.lcfile.com/4bd8a31b902407ffc7f1.jpg");
        songSheet.saveInBackground(new SimpleSaveCallBack() {
            @Override
            public void onSuccess() {
                hideLoading();
                showToast("注册成功");
                EventBusUtils.post(new MessageEvent(MessageEvent.TYPE_USER_INFOR_CHANGED));
                ((SupportFragment) getParentFragment()).pop();
            }

            @Override
            public void onFail(AVException e) {
                hideLoading();
                AVUser.getCurrentUser().deleteInBackground(new DeleteCallback() {
                    @Override
                    public void done(AVException e) {
                        showToast("创建歌单失败" + e.toString());
                    }
                });
            }
        });
    }
}
