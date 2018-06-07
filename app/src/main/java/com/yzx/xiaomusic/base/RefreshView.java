package com.yzx.xiaomusic.base;

import java.util.List;

public interface RefreshView<T> {

    void onRefreshSuccess(List<T> datas);

    void onRefreshFail(String errorMsg);
}
