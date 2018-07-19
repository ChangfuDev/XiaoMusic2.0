package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.othershe.library.NiceImageView;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.ui.login.LoginFragment;
import com.yzx.xiaomusic.ui.main.MainFragment;
import com.yzx.xiaomusic.utils.FragmentStartUtils;
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
    private MainFragment parentFragment;

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
            AVUser currentUser = AVUser.getCurrentUser();
            //未登录
            if (currentUser == null) {
                headHolder.llUnLogin.setVisibility(View.VISIBLE);
                headHolder.llLogin.setVisibility(View.GONE);

                headHolder.stvGoLogin.setOnClickListener(v -> {
                    final boolean[] hadStart = {false};
                    parentFragment.drawerLayout.closeDrawer(Gravity.START);
                    parentFragment.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                        @Override
                        public void onDrawerClosed(View drawerView) {
                            super.onDrawerClosed(drawerView);
                            Log.i("ygl", "onDrawerClosed: ");

                            if (!hadStart[0]) {
                                FragmentStartUtils.startFragment(parentFragment, new LoginFragment());
                                hadStart[0] = true;
                            }
                        }
                    });
                });
            } else {
                //登陆
                headHolder.llUnLogin.setVisibility(View.GONE);
                headHolder.llLogin.setVisibility(View.VISIBLE);
                GlideUtils.loadImg(context, R.drawable.ic_head, GlideUtils.TYPE_HEAD, headHolder.ivHead);
                GlideUtils.loadImg(context, R.drawable.ic_background, headHolder.ivNavigationHeadBackground);
                headHolder.tvName.setText(currentUser.getUsername());
                headHolder.stvAccountGrade.setText(String.format("Lv %s", 8));
                headHolder.stvSignIn.setText(R.string.signIn);
            }
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

    public void setData(MainFragment parentFragment, ArrayList<Integer> navigationMenuIcons, ArrayList<Integer> navigationMenuTitles) {
        this.parentFragment = parentFragment;
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
        @BindView(R.id.ll_login)
        LinearLayout llLogin;
        @BindView(R.id.stv_go_login)
        ShapeTextView stvGoLogin;
        @BindView(R.id.ll_unLogin)
        LinearLayout llUnLogin;

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
