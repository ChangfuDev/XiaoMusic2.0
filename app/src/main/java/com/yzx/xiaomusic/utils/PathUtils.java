package com.yzx.xiaomusic.utils;

import java.io.File;

import static com.yzx.xiaomusic.Constant.MD5_KEY;
import static com.yzx.xiaomusic.Constant.PATH_ABSOLUTE_DOWNLOAD;
import static com.yzx.xiaomusic.Constant.PATH_ABSOLUTE_DOWNLOAD_COVER;
import static com.yzx.xiaomusic.Constant.PATH_APP;

/**
 * @author yzx
 * @date 2018/7/16
 * Description  下载缓存等路径
 */
public class PathUtils {

    /**
     * 封面下载地址
     *
     * @param title
     * @param cover
     * @return
     */
    public static String getDownloadCoverPath(String title, String cover) {
        makeSureDownloadCoverDir();
        return PATH_ABSOLUTE_DOWNLOAD_COVER + "/" + title + "_" + EncryptUtils.encryptHmacMD5ToString(cover, MD5_KEY) + ".jpg";
    }

    private static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 下载封面目录
     */
    private static void makeSureDownloadCoverDir() {
        if (!isFileExist(PATH_ABSOLUTE_DOWNLOAD_COVER)) {
            makeSureDownload();
            makeSureDir(PATH_ABSOLUTE_DOWNLOAD_COVER);
        }
    }


    /**
     * 下载目录
     */
    private static void makeSureDownload() {
        if (!isFileExist(PATH_ABSOLUTE_DOWNLOAD)) {
            makeAppDir();
            makeSureDir(PATH_ABSOLUTE_DOWNLOAD);
        }
    }

    /**
     * app 目录
     */
    private static void makeAppDir() {
        makeSureDir(PATH_APP);
    }


    private static void makeSureDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


}
