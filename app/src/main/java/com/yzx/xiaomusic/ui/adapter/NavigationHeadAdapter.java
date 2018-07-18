package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.library.NiceImageView;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.ShapeTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yzx on 2018/5/31.
 * Description
 */
public class NavigationHeadAdapter extends RecyclerView.Adapter {

    public static final int TYPE_HEAD = 1;
    public static final int TYPE_DEFAULT = 2;

    private ArrayList<Integer> navigationMenuIcons;
    private ArrayList<Integer> navigationMenuTitles;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }
        return TYPE_DEFAULT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (TYPE_HEAD == viewType) {
            return new HeadHolder(ResourceUtils.parseLayout(context, R.layout.include_navigation_head, parent));
        }
        return new MenuHolder(ResourceUtils.parseLayout(context, R.layout.item_navigation_menu, parent));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        if (position == 0) {
            HeadHolder headHolder = (HeadHolder) holder;
            GlideUtils.loadImg(context, R.drawable.ic_head, GlideUtils.TYPE_HEAD, headHolder.ivHead);
            GlideUtils.loadImg(context, R.drawable.ic_background, headHolder.ivNavigationHeadBackground);
            headHolder.tvName.setText("杨子晓");
            headHolder.stvAccountGrade.setText(String.format("Lv %s", 8));
            headHolder.stvSignIn.setText(R.string.signIn);
        } else {
            MenuHolder menuHolder = (MenuHolder) holder;
            menuHolder.tvNavigationMenuTitle.setText(navigationMenuTitles.get(position - 1));
            menuHolder.ivNavigationMenuIcon.setImageResource(navigationMenuIcons.get(position - 1));
        }
    }


    @Override
    public int getItemCount() {
        return navigationMenuTitles.size() + 1;
    }

    public void setData(ArrayList<Integer> navigationMenuIcons, ArrayList<Integer> navigationMenuTitles) {
        this.navigationMenuIcons = navigationMenuIcons;
        this.navigationMenuTitles = navigationMenuTitles;
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_navigation_head_background)
        ImageView ivNavigationHeadBackground;
        @BindView(R.id.iv_head)
        NiceImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.stv_account_grade)
        ShapeTextView stvAccountGrade;
        @BindView(R.id.stv_sign_in)
        ShapeTextView stvSignIn;

        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MenuHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_navigation_menu_icon)
        ImageView ivNavigationMenuIcon;
        @BindView(R.id.tv_navigation_menu_title)
        TextView tvNavigationMenuTitle;

        public MenuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
