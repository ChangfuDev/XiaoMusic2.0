package com.yzx.xiaomusic.network.download;

public interface ProgressListener {
    void onProgress(long currentBytes, long contentLength, boolean done);
}