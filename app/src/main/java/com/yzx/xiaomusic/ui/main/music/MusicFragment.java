package com.yzx.xiaomusic.ui.main.music;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.ui.adapter.MainMusicAdapter;
import com.yzx.xiaomusic.ui.main.music.local.LocalMusicFragment;
import com.yzx.xiaomusic.ui.main.music.recent.RecentPlayFragment;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 */
public class MusicFragment extends BaseFragment implements CommonBaseAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<String> titles;
    private ArrayList<Integer> icons;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        titles = new ArrayList<>();
        titles.add(ResourceUtils.parseString(getContext(), R.string.localMusic));
        titles.add(ResourceUtils.parseString(getContext(), R.string.recentPlay));
        titles.add(ResourceUtils.parseString(getContext(), R.string.downloadManager));
        titles.add(ResourceUtils.parseString(getContext(), R.string.myRadio));
        titles.add(ResourceUtils.parseString(getContext(), R.string.myCollection));

        icons = new ArrayList<>();
        icons.add(R.drawable.a1_);
        icons.add(R.drawable.a1j);
        icons.add(R.drawable.a0t);
        icons.add(R.drawable.a0l);
        icons.add(R.drawable.a0m);
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        swipeRefreshLayout.setColorSchemeColors(ResourceUtils.parseColor(getContext(), R.color.colorAccent));

        SupportFragment parentFragment = (SupportFragment) getParentFragment();
        MainMusicAdapter adapter = new MainMusicAdapter(parentFragment);
        adapter.setData(titles, icons);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
            return true;
        } else {
            return super.onBackPressedSupport();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                easyParentStart(new LocalMusicFragment());
                break;
            case 1:
                easyParentStart(new RecentPlayFragment());
                break;
        }
    }
}
