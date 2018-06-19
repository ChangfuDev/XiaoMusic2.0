package com.yzx.xiaomusic.ui.dialog;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;

/**
 * @author yzx
 * @date 2018/2/27
 * Description  底部对话框基类，限制高度，设置背景色微透明
 */

public abstract class BaseBottomDialog extends BottomSheetDialogFragment {

    @Override
    public void onStart() {
        super.onStart();

        int dialogHeight = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.6);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogHeight);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) getDialog();
        View view = bottomSheetDialog.getWindow().findViewById(android.support.design.R.id.design_bottom_sheet);
        view.setBackgroundResource(ResourceUtils.parseColor(getContext(), R.color.colorTransparent));
        BottomSheetBehavior.from(view).setPeekHeight(1600);
    }

}
