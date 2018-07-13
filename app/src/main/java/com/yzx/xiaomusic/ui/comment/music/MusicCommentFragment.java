package com.yzx.xiaomusic.ui.comment.music;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseMvpFragment;
import com.yzx.xiaomusic.model.entity.comment.MusicComment;
import com.yzx.xiaomusic.ui.adapter.MusicCommentAdapter;
import com.yzx.xiaomusic.widget.MusicFooter;

import java.util.List;

import butterknife.BindView;

import static com.yzx.xiaomusic.Constant.KEY_ID;

/**
 * @author yzx
 * @date 2018/7/12
 * Description 歌曲评论
 */
public class MusicCommentFragment extends BaseMvpFragment<MusicCommentPresenter> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    int offset;
    String id;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.tb)
    Toolbar tb;
    private MusicCommentAdapter adapter;

    @Override
    protected MusicCommentPresenter getPresenter() {
        return new MusicCommentPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
//        loadService = LoadSir
//                .getDefault()
//                .register(smartRefreshLayout, (Callback.OnReloadListener) v -> mPresenter.getComment(id, 0));
        return view;
    }

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_comment_music;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle arguments = getArguments();
        id = arguments.getString(KEY_ID);
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        initToolBar(tb);
        tvTitle.setText(R.string.evaluate);
        smartRefreshLayout.setRefreshFooter(new MusicFooter(getContext()));
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.getComment(id, offset);
        });
        adapter = new MusicCommentAdapter(this);
        recyclerView.setAdapter(adapter);
        mPresenter.getComment(id, offset);
    }

    public void setData(MusicComment musicComment) {
        adapter.setData(musicComment);
        offset += 10;
    }

    public void addData(List<MusicComment.CommentsBean> comments) {
        adapter.addData(comments);
        offset += 10;
    }
}
