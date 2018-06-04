package com.yzx.xiaomusic.ui.main;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseActivity;
import com.yzx.xiaomusic.ui.adapter.NavigationHeadAdapter;

import java.util.ArrayList;
import java.util.Objects;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigationView)
    NavigationView navigationView;
    //    @BindView(R.id.tl)
//    TabLayout tl;
//    @BindView(R.id.tb)
//    Toolbar tb;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private ArrayList<Integer> navigationMenuTitles;
    private ArrayList<Integer> navigationMenuIcons;
    public NavController navController;


    @Override
    protected int initContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //menu文案
        navigationMenuTitles = new ArrayList<>();
        navigationMenuTitles.add(R.string.myMessage);
        navigationMenuTitles.add(R.string.vip);
        navigationMenuTitles.add(R.string.shoppingMall);
        navigationMenuTitles.add(R.string.gameRecommend);
        navigationMenuTitles.add(R.string.listenOnLine);

        navigationMenuTitles.add(R.string.myFriend);
        navigationMenuTitles.add(R.string.nearbyPerson);

        navigationMenuTitles.add(R.string.personalSkin);
        navigationMenuTitles.add(R.string.findSongByListen);
        navigationMenuTitles.add(R.string.stopPlayTimely);
        navigationMenuTitles.add(R.string.scan);
        navigationMenuTitles.add(R.string.musicAlarm);
        navigationMenuTitles.add(R.string.driveMode);
        navigationMenuTitles.add(R.string.musicCloudDisk);
        navigationMenuTitles.add(R.string.coupon);
        //menuIcon
        navigationMenuIcons = new ArrayList<>();
        navigationMenuIcons.add(R.drawable.ak4);
        navigationMenuIcons.add(R.drawable.akc);
        navigationMenuIcons.add(R.drawable.ak_);
        navigationMenuIcons.add(R.drawable.ak1);
        navigationMenuIcons.add(R.drawable.ajz);

        navigationMenuIcons.add(R.drawable.ak0);
        navigationMenuIcons.add(R.drawable.ak6);

        navigationMenuIcons.add(R.drawable.ak9);
        navigationMenuIcons.add(R.drawable.ak2);
        navigationMenuIcons.add(R.drawable.aka);
        navigationMenuIcons.add(R.drawable.ak6);
        navigationMenuIcons.add(R.drawable.akb);
        navigationMenuIcons.add(R.drawable.aju);
        navigationMenuIcons.add(R.drawable.ajv);
        navigationMenuIcons.add(R.drawable.ajx);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initNavigationView();

//        Navigation.setViewNavController();
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.hostFragment);
        if (host != null) {
            navController = host.getNavController();
            navController.navigate(R.id.mainFragment);
        }
    }


    /**
     * 初始化NavigationView
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initNavigationView() {

        LinearLayout navigationViewHeaderView = (LinearLayout) navigationView.getHeaderView(0);
        ViewGroup.LayoutParams layoutParams = navigationViewHeaderView.getLayoutParams();
        layoutParams.height = getResources().getDisplayMetrics().heightPixels;
        navigationViewHeaderView.setLayoutParams(layoutParams);

        RecyclerView recyclerView = (RecyclerView) navigationViewHeaderView.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (recyclerView.getChildLayoutPosition(view) == 4 || recyclerView.getChildLayoutPosition(view) == 6) {
                    outRect.bottom = DensityUtils.dip2px(getBaseContext(), 5);
                }
            }
        });
        NavigationHeadAdapter adapter = new NavigationHeadAdapter();
        adapter.setData(navigationMenuIcons, navigationMenuTitles);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigateUp() {
//        NavigationUi.
        return NavigationUI.navigateUp(drawerLayout, navController);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(drawerLayout,
                Navigation.findNavController(this, R.id.hostFragment));
    }
}
