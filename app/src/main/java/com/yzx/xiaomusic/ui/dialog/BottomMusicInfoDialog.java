package com.yzx.xiaomusic.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.ui.adapter.BottomMusicInfoDialogAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.yzx.xiaomusic.ui.adapter.MusicAdapter.KEY_MUSIC_INFO;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 底部音乐信息
 */
public class BottomMusicInfoDialog extends BaseListBottomDialog implements CommonBaseAdapter.OnItemClickListener {

    private List<Integer> musicInfoDialogBeans;
    private Bundle arguments;
    private MusicInfo musicInfo;

    {
        musicInfoDialogBeans = new ArrayList<>();
        musicInfoDialogBeans.add(R.drawable.a0q);
        musicInfoDialogBeans.add(R.drawable.a1h);
        musicInfoDialogBeans.add(R.drawable.a0w);
        musicInfoDialogBeans.add(R.drawable.a0n);
        musicInfoDialogBeans.add(R.drawable.a0l);
        musicInfoDialogBeans.add(R.drawable.a1_);
        musicInfoDialogBeans.add(R.drawable.a1n);
        musicInfoDialogBeans.add(R.drawable.a1i);
        musicInfoDialogBeans.add(R.drawable.a1j);
        musicInfoDialogBeans.add(R.drawable.a1m);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        arguments = getArguments();
        musicInfo = (MusicInfo) arguments.getSerializable(KEY_MUSIC_INFO);
    }

    @Override
    public View setHead(LinearLayout container) {
        if (musicInfo.isLocal()) {
            TextView view = (TextView) ResourceUtils.parseLayout(getContext(), R.layout.layout_bottom_dialog_music_info_head, container);
            view.setText(String.format("歌曲：%s", musicInfo.getMusicName()));
            return view;
        } else {
            TextView view = (TextView) ResourceUtils.parseLayout(getContext(), R.layout.layout_bottom_dialog_music_info_head, container);
            view.setText(String.format("歌曲：%s", musicInfo.getMusicName()));
            return view;
        }
    }

    @Override
    protected RecyclerView.Adapter setAdapter(RecyclerView recyclerView) {

        BottomMusicInfoDialogAdapter adapter = new BottomMusicInfoDialogAdapter(musicInfo);
        adapter.setOnItemClickListener(this);
        adapter.setData(musicInfoDialogBeans);
        return adapter;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                ToastUtils.showToast(R.string.evaluate);
                break;
            case 1:
                ToastUtils.showToast(R.string.share);
                break;
            case 2:
                ToastUtils.showToast(R.string.collectToSongSheet);
                break;
            case 3:
                ToastUtils.showToast(R.string.singer);
                break;
            case 4:
                ToastUtils.showToast(R.string.album);
                break;
            case 5:
                ToastUtils.showToast(R.string.MusicQuality);
                break;
            case 6:
                ToastUtils.showToast(R.string.video);
                break;
            case 7:
                ToastUtils.showToast("相似推荐");
                break;
            case 8:
                ToastUtils.showToast(R.string.timeStopPlay);
                break;
            case 9:
                ToastUtils.showToast(R.string.driveMode);
                break;
        }
    }
}
