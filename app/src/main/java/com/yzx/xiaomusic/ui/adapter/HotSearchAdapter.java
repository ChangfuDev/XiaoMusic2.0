package com.yzx.xiaomusic.ui.adapter;

import android.view.View;

import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.widget.ShapeTextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class HotSearchAdapter extends TagAdapter<String> {

    public HotSearchAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {

        ShapeTextView view = (ShapeTextView) ResourceUtils.parseLayout(parent.getContext(), R.layout.item_hot_search);
        view.setText(s);
        return view;
    }
}
