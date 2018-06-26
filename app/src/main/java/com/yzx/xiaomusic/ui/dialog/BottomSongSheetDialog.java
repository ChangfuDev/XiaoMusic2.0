package com.yzx.xiaomusic.ui.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.BottomSongSheetDialogAdapter;

import java.util.List;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 底部歌单列表
 */
public class BottomSongSheetDialog extends BaseListBottomDialog implements CommonBaseAdapter.OnItemClickListener {


    private List<MusicInfo> songSheet;
    private BottomSongSheetDialogAdapter adapter;

    @Override
    public View setHead(LinearLayout container) {
        View view = ResourceUtils.parseLayout(getContext(), R.layout.layout_bottom_dialog_song_sheet_head, container);
        return view;
    }


    @Override
    protected RecyclerView.Adapter setAdapter(RecyclerView recyclerView) {

        adapter = new BottomSongSheetDialogAdapter();
        adapter.setOnItemClickListener(this);
        MusicService service = ServiceManager.getInstance().getService();
        songSheet = service.getSongSheet();
        adapter.setCurrentPosition(service.getIndex());
        recyclerView.scrollToPosition(service.getIndex());
        adapter.setData(songSheet);
        return adapter;
    }

    @Override
    public void onItemClick(View view, int position) {
        MusicService service = ServiceManager.getInstance().getService();
        service.setSongSheet(songSheet);
        adapter.setCurrentPosition(position);
        //同一首歌
        if (songSheet.get(position) != service.getMusicInfo()) {
            service.setMusicIndex(position);
            service.realPlay();
        } else {
            service.playPause();
        }
    }

}
