package com.yzx.xiaomusic.ui.main.music.local;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadSir;
import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.ui.adapter.MusicAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @author yzx
 * @date 2018/6/15
 * Description 本地歌曲
 */
public class LocalMusicFragment extends BaseMvpFragment<LocalMusicPresenter> implements Toolbar.OnMenuItemClickListener, CommonBaseAdapter.OnItemClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_des)
    TextView tvDes;
    private MusicAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //重新注册到View里
        loadService = LoadSir
                .getDefault()
                .register(recyclerView, (Callback.OnReloadListener) v -> mPresenter.getLocalMusics());
        return view;
    }

    @Override
    protected LocalMusicPresenter getPresenter() {
        return new LocalMusicPresenter();
    }

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

        adapter = new MusicAdapter(getChildFragmentManager(), this);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mPresenter.getLocalMusics();
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

    public void setData(List<MusicInfo> musicInfos) {
        adapter.setData(musicInfos);
    }

    @Override
    public void onItemClick(View view, int position) {
        playMusicWithStartFragment(this, adapter.datas, position);
    }
}
