package com.yzx.xiaomusic.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yzx.xiaomusic.R;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 底部条目对话框
 */
public abstract class BaseListBottomDialog extends BaseBottomDialog {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        boolean callHeadScroll = canHeadScroll();
        if (callHeadScroll) {
            rootView = inflater.inflate(R.layout.dialog_base_bottom_list_scroll, container, false);
        }else {
            rootView = inflater.inflate(R.layout.dialog_base_bottom_list_unscroll, container, false);
        }
        LinearLayout llContent = (LinearLayout) rootView.findViewById(R.id.ll_content);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(setAdapter(recyclerView));
        View head = setHead(llContent);
        if (head != null) {
            llContent.addView(head, 0, head.getLayoutParams());
        }

        return rootView;
    }

    protected boolean canHeadScroll() {
        return false;
    }

    /**
     * 自定义Adapter
     *
     * @param recyclerView
     * @return
     */
    protected abstract RecyclerView.Adapter setAdapter(RecyclerView recyclerView);


    /**
     * 添加头布局
     *
     * @param container
     * @return
     */
    public View setHead(LinearLayout container) {
        return null;
    }
}
