package com.yzx.xiaomusic.network.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author yzx
 * @date 2018/7/13
 * Description
 */
public interface DownloadApi {

    /**
     * 下载
     * @param url
     * @return
     */
    @Streaming
    @GET()
    Observable<ResponseBody> download(@Url String url);
}
