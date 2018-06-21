package com.yzx.xiaomusic.ui.play.card;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.service.MusicService;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.adapter.PlayCardAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yzx
 * @date 2018/6/21
 * Description
 */
public class PlayCardFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_play_card;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        PlayCardAdapter adapter = new PlayCardAdapter();
        MusicService service = ServiceManager.getInstance().getService();
        adapter.setData(service.getSongSheet());
        recyclerView.setAdapter(adapter);

        recyclerView.scrollToPosition(service.getIndex());
    }

    @OnClick({R.id.iv_like, R.id.iv_download, R.id.iv_evaluate, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                break;
            case R.id.iv_download:
                break;
            case R.id.iv_evaluate:
                break;
            case R.id.iv_more:
                break;
        }
    }
}
