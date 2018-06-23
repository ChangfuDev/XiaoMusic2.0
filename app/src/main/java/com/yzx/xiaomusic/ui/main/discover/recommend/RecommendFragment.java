package com.yzx.xiaomusic.ui.main.discover.recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.ui.adapter.RecommendAdapter;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 * @date 2018/6/21
 * Description 推荐页面
 */
public class RecommendFragment extends BaseMvpFragment<RecommendPresenter> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected RecommendPresenter getPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        SupportFragment parentFragment = (SupportFragment) getParentFragment().getParentFragment();
        RecommendAdapter recommendAdapter = new RecommendAdapter(parentFragment);
        recyclerView.setAdapter(recommendAdapter);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        mPresenter.getSongSheet(0);
    }

}
