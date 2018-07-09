package com.yzx.xiaomusic.ui.singer.top;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.SingerTopInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.ui.adapter.MusicAdapter;
import com.yzx.xiaomusic.ui.singer.SingerDetailsFragment;

import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.ui.singer.SingerDetailsFragment.KEY_ID_SINGER;

/**
 * @author yzx
 */
public class Top50Fragment extends BaseMvpFragment<Top50Presenter> implements CommonBaseAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String singerId;
    private MusicAdapter adapter;

    @Override
    protected Top50Presenter getPresenter() {
        return new Top50Presenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_detail_singer_top50;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle arguments = getArguments();
        singerId = arguments.getString(KEY_ID_SINGER);
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        adapter = new MusicAdapter(getFragmentManager(), (SupportFragment) getParentFragment());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getTop50(singerId);
    }

    public void setData(List<MusicInfo> musicInfos, SingerTopInfo.ArtistBean artist) {
        adapter.setData(musicInfos);
        SingerDetailsFragment singerDetailsFragment = (SingerDetailsFragment) getParentFragment();
        singerDetailsFragment.setData(artist);
    }

    @Override
    public void onItemClick(View view, int position) {
        playMusicWithStartFragment((SupportFragment) getParentFragment(), adapter.datas, position);
    }
}
