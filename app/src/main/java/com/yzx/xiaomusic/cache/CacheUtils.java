package com.yzx.xiaomusic.cache;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.yzx.xiaomusic.Constant.PATH_ABSOLUTE_CACHE_LYRIC;
import static com.yzx.xiaomusic.Constant.PATH_ABSOLUTE_CACHE_MUSIC;

/**
 * @author yzx
 * @date 2018/2/23
 * Description
 */

public class CacheUtils {

    private static final int IO_BUFFER_SIZE = 8 * 1024;
    private static final String TAG = "yglCacheUtils";

    /**
     * 缓存音乐文件
     *
     * @param id
     * @param url
     */
    @SuppressLint("CheckResult")
    public static void cacheMusic(final String id, final String url) {
        final DiskLruCache musicCache = CacheManager.getCacheManager().getMusicCache();

        Observable.create((ObservableOnSubscribe<String>) e -> {
            if (musicCache == null) {
                return;
            }
            try {
                DiskLruCache.Editor editor = musicCache.edit(id);
                if (editor != null) {
                    OutputStream outputStream = editor.newOutputStream(0);
                    if (downloadUrlToStream(url, outputStream)) {
                        editor.commit();//提交
                    } else {
                        editor.abort();//重复操作
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                Log.i(TAG, "cacheMusicFail: " + url);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> Log.i(TAG, "cacheMusicSuccess: " + url));

    }

    /**
     * 缓存音乐文件
     *
     * @param id
     * @param url
     */
    @SuppressLint("CheckResult")
    public static void cacheLyric(final String id, final String url) {
        DiskLruCache lyricCache = CacheManager.getCacheManager().getLyricCache();

        Observable.create((ObservableOnSubscribe<String>) e -> {
            try {
                DiskLruCache.Editor editor = lyricCache.edit(id);
                if (editor != null) {
                    OutputStream outputStream = editor.newOutputStream(0);
                    if (downloadUrlToStream(url, outputStream)) {
                        editor.commit();//提交
                    } else {
                        editor.abort();//重复操作
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                Log.i(TAG, "cacheLyricFail: " + url);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> Log.i(TAG, "cacheLyricSuccess: " + url));

    }

    /**
     * Download a bitmap from a URL and write the content to an output stream.
     *
     * @param urlString The URL to fetch
     * @return true if successful, false otherwise
     */
    public static boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;

        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error in downloadBitmap - " + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
            }
        }
        return false;
    }

    public static String getCacheMusic(String id) {
        File file = new File(PATH_ABSOLUTE_CACHE_MUSIC + "/" + id + ".0");
        if (file.exists()) {
            return PATH_ABSOLUTE_CACHE_MUSIC + "/" + id + ".0";
        }
        return null;
    }

    public static boolean isMusicCache(String id) {

        return !TextUtils.isEmpty(getCacheMusic(id));
    }


    public static String getCacheLyric(String id) {
        File file = new File(PATH_ABSOLUTE_CACHE_LYRIC + "/" + id + ".0");
        if (file.exists()) {
            return PATH_ABSOLUTE_CACHE_LYRIC + "/" + id + ".0";
        }
        return null;
    }

    public static boolean writeStringToStream(String lyricString, OutputStream outputStream) {
        try {
            outputStream.write(lyricString.getBytes());
            outputStream.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
