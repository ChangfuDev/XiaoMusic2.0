package com.yzx.xiaomusic.ui.main.music.local;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.yzx.commonlibrary.base.mvp.CommonBaseModel;
import com.yzx.commonlibrary.utils.RxTransformer;
import com.yzx.xiaomusic.app.MusicApplication;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.common.SingerInfo;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yzx on 2018/6/19.
 * Description
 */
public class LocalMusicModel extends CommonBaseModel {
    public void getLocalMusics(Observer<MusicInfo> observer) {
        final Cursor cursor = MusicApplication.getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            Observable.create((ObservableOnSubscribe<MusicInfo>) e -> {
                while (cursor.moveToNext()) {
                    MusicInfo musicInfo = new MusicInfo();
                    musicInfo.setLocal(true);
                    //id
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    musicInfo.setMusicId(String.valueOf(id));
                    //音乐全名包含歌名和歌手
                    musicInfo.setMusicName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                    //歌手
                    ArrayList<SingerInfo> singerInfos = new ArrayList<>();
                    SingerInfo singerInfo = new SingerInfo();
                    singerInfo.setSingerName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                    singerInfos.add(singerInfo);
                    musicInfo.setSingerInfos(singerInfos);
                    //本地路径
                    musicInfo.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                    //歌曲时间
                    musicInfo.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                    //歌曲大小
                    musicInfo.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    String albumid = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    //海报
                    musicInfo.setPoster(getMusicBitemp(id, Long.parseLong(albumid)));

                    if (musicInfo.getSize() > 1024 * 1024 * 3) {
                        // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                        String musicName = musicInfo.getMusicName();
                        if (musicName.endsWith(".mp3") || musicName.endsWith(".ape")
                                || musicName.endsWith(".mp3") || musicName.endsWith(".wav")
                                || musicName.endsWith(".flac")) {
                            //去掉MP3等后缀
                            musicInfo.setMusicName(musicName.trim().substring(0, musicName.length() - 4));
                            dealMusicName(musicInfo);
                        } else {
                            dealMusicName(musicInfo);
                        }
                        e.onNext(musicInfo);
                    }
                }
                e.onComplete();
            }).compose(RxTransformer.switchSchedulers(this))
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MusicInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(MusicInfo musicInfo) {
                            observer.onNext(musicInfo);
                        }

                        @Override
                        public void onError(Throwable e) {
                            observer.onError(e);
                        }

                        @Override
                        public void onComplete() {
                            observer.onComplete();
                            cursor.close();
//                            PreferenceUtil.put(LOCAL_MUSIC_INFO, JsonUtils.objectToString(list));
                        }
                    });
        }
    }

    /**
     * 处理歌名和歌手
     *
     * @param musicInfo
     */
    private void dealMusicName(MusicInfo musicInfo) {

        if (musicInfo.getMusicName().contains("-")) {
            String[] str = musicInfo.getMusicName().split("-");
            musicInfo.getSingerInfos().get(0).setSingerName(str[0].trim());
            musicInfo.setMusicName(str[1].trim());
        } else if (musicInfo.getMusicName().contains("_")) {
            String[] str = musicInfo.getMusicName().split("_");
            musicInfo.getSingerInfos().get(0).setSingerName(str[1].trim());
            musicInfo.setMusicName(str[0].trim());
        }
//        else {
//            musicInfo.setMusicName(musicInfo.getAllName().trim());
//        }
    }

    private static final Uri sArtworkUri = Uri
            .parse("content://media/external/audio/albumart");

    public String getMusicBitemp(long songid, long albumid) {


        try {
            Uri uri;
            if (albumid < 0) {
                uri = Uri.parse("content://media/external/audio/media/" + songid + "/albumart");
            } else {
                uri = ContentUris.withAppendedId(sArtworkUri, albumid);
            }

            if (uri == null) {
                return null;
            }
            uri.toString();
            return uri.toString();
        } catch (Exception ignored) {
        }

        return null;
    }
}
