package com.yzx.xiaomusic.widget.signview;

/**
 * Created by yzx on 2018/8/4.
 * Description
 */
public class SignInData {
    /**
     * 未签到
     */
    public static final int STATE_NO = 0;
    /**
     * 已签到
     */
    public static final int STATE_SIGNED = 1;

    /**
     * 今天已签到
     */
    public static final int STATE_SIGNED_TODAY = 2;


    public SignInData(int state, String des) {
        this.state = state;
        this.des = des;
    }

    /**
     * 签到状态
     */
    int state;
    /**
     * 描述
     */
    String des;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
