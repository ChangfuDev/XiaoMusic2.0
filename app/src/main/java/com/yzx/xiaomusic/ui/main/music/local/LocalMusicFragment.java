package com.yzx.xiaomusic.ui.main.music.local;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;

import butterknife.BindView;

/**
 * @author yzx
 * @date 2018/6/15
 * Description 本地歌曲
 */
public class LocalMusicFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_music_local;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        initToolBar(tb);
        tvTitle.setText(R.string.localMusic);
        tb.inflateMenu(R.menu.menu_music_local);
        tb.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                showToast(R.string.search);
                return true;
            case R.id.menu_scan_local:
                showToast(R.string.scanLocalMusic);
                return true;
            case R.id.menu_sort:
                showToast(R.string.selectSortMethod);
                return true;
            case R.id.menu_get_cover:
                showToast(R.string.getCoverLyric);
                return true;
            case R.id.menu_update_music_quality:
                showToast(R.string.updateMusicQuality);
                return true;
            default:
                return false;
        }
    }

}
