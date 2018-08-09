package com.yzx.xiaomusic.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.ui.adapter.BottomMusicInfoDialogAdapter;
import com.yzx.xiaomusic.ui.album.AlbumDetailFragment;
import com.yzx.xiaomusic.ui.mv.MvDetailsActivity;
import com.yzx.xiaomusic.ui.singer.SingerDetailsFragment;
import com.yzx.xiaomusic.utils.FragmentStartUtils;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;
import static com.yzx.xiaomusic.ui.adapter.MusicAdapter.KEY_MUSIC_INFO;
import static com.yzx.xiaomusic.ui.singer.SingerDetailsFragment.KEY_ID_SINGER;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 底部音乐信息
 */
public class BottomMusicInfoDialog extends BaseListBottomDialog implements CommonBaseAdapter.OnItemClickListener {

    private List<Integer> musicInfoDialogBeans;
    private Bundle arguments;
    private MusicInfo musicInfo;
    private SupportFragment parentFragment;

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
        if (musicInfo == null) {
            throw new NullPointerException("musicInfo == null");
        }
        if (musicInfo.isLocal()) {
            TextView view = (TextView) ResourceUtils.parseLayout(getContext(), R.layout.layout_bottom_dialog_music_info_head, container);
            ExtraMusicInfo extraMusicInfo = DBUtils.getExtraMusicInfoDao().getExtraMusicInfoById(musicInfo.getMusicId());
            if (extraMusicInfo == null) {
                view.setText(String.format("歌曲：%s", musicInfo.getMusicName()));
            } else {
                view.setText(String.format("歌曲：%s(共播放了%s次)", musicInfo.getMusicName(), extraMusicInfo.getPlayCount()));
            }
            return view;
        } else {
            TextView view = (TextView) ResourceUtils.parseLayout(getContext(), R.layout.layout_bottom_dialog_music_info_head, container);
            ExtraMusicInfo extraMusicInfo = DBUtils.getExtraMusicInfoDao().getExtraMusicInfoById(musicInfo.getMusicId());
            if (extraMusicInfo == null) {
                view.setText(String.format("歌曲：%s", musicInfo.getMusicName()));
            } else {
                view.setText(String.format("歌曲：%s(共播放了%s次)", musicInfo.getMusicName(), extraMusicInfo.getPlayCount()));
            }
            return view;
        }
    }

    @Override
    protected boolean canHeadScroll() {
        return true;
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
                if (parentFragment != null) {
                    arguments.clear();
                    arguments.putString(KEY_ID_SINGER, musicInfo.getSingerInfos().get(0).getSingerId());
                    FragmentStartUtils.startFragment(parentFragment, new SingerDetailsFragment(), arguments);
                }
                break;
            case 4:
                if (parentFragment != null) {
                    arguments.clear();
                    arguments.putString(KEY_ID, musicInfo.getAlbumId());
                    arguments.putString(KEY_NAME, musicInfo.getAlbumName());
                    arguments.putString(KEY_COVER, musicInfo.getAlbumCoverPath());
                    FragmentStartUtils.startFragment(parentFragment, new AlbumDetailFragment(), arguments);
                }
                break;
            case 5:
                ToastUtils.showToast(R.string.MusicQuality);
                break;
            case 6:
                if (!TextUtils.equals("0", musicInfo.getMvId())) {

                    Intent intent = new Intent(getContext(), MvDetailsActivity.class);
                    intent.putExtra(KEY_ID, musicInfo.getMvId());
                    getActivity().startActivity(intent);
                    parentFragment.getActivity().overridePendingTransition(R.anim.v_fragment_enter, R.anim.v_fragment_exit);
//                    arguments.clear();
//                    arguments.putString(KEY_ID, musicInfo.getMvId());
//                    FragmentStartUtils.startFragment(parentFragment, new MvDetailFragment(), arguments);
                }
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
        dismiss();
    }

    public void setParentFragment(SupportFragment parentFragment) {
        this.parentFragment = parentFragment;
    }
}
