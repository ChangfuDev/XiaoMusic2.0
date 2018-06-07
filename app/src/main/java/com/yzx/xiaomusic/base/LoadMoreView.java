package com.yzx.xiaomusic.base;

import java.util.List;

public interface LoadMoreView<T> {

    void onLoadMoreSuccess(List<T> datas);

    void onLoadMoreFail(String errorMsg);
}
