package com.yzx.xiaomusic.ui.songsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetDetail;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.ShapeTextView;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.yzx.xiaomusic.ui.songsheet.SongSheetDetailFragment.KEY_INFO_SONG_SHEET;

/**
 * Created by yzx on 2018/6/12.
 * Description
 */
public class SongSheetMoreFragment extends BaseFragment {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_little_bg)
    ImageView ivLittleBg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.stv_save)
    ShapeTextView stvSave;
    Unbinder unbinder;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_song_sheet_more;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        SongSheetDetail.ResultBean resultBean = (SongSheetDetail.ResultBean) arguments.getSerializable(KEY_INFO_SONG_SHEET);
        tvTitle.setText(resultBean.getName());
        tvDes.setText(resultBean.getDescription());
        GlideUtils.loadImg(getContext(), resultBean.getCoverImgUrl(), ivLittleBg);
    }


    @OnClick({R.id.iv_close, R.id.stv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                break;
            case R.id.stv_save:
                break;
        }
    }
}
