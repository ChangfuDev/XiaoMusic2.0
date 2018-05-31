package com.yzx.commonlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * @author yzx
 * @date 2018/4/25
 * Description Fragment懒加载（一层或两层嵌套，更多未测试）
 */
public class CommonLazyFragment extends Fragment {

    private boolean isFirstCreate;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        deal();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isFirstCreate = true;
        deal();
    }

    private void deal() {
        Fragment parentFragment = getParentFragment();
        //父层ViewPager逻辑
        if (parentFragment == null) {
            if (getUserVisibleHint()) {
                load();
                /**
                 * 在第一个ViewPager的Fragment的setUserVisibleHint方法调用时，尚未添加到Activity，所以加层判断
                 */
                if (isAdded()) {
                    /**如果Fragment里嵌套有ViewPager,在切换父ViewPager时，
                     * 子ViewPager的第一个不会走任何生命周期和setUserVisibleHint(boolean isVisibleToUser)方法，
                     * 因此需要遍历子ViewPager,调用其load()方法
                     */
                    List<Fragment> fragments = getChildFragmentManager().getFragments();
                    for (Fragment fragment : fragments) {
                        ((CommonLazyFragment) fragment).load();
                    }
                }
            }
        }
        /**
         * 子层逻辑
         * 嵌套多层时，需要父Fragment也是可见的
         */
        else {
            if (parentFragment.getUserVisibleHint()) {
                load();
            }
        }
    }

    /**
     * 第一次加载并且可见，才加载数据
     */
    private void load() {
        if (isFirstCreate && getUserVisibleHint()) {
            lazyLoadData();
            isFirstCreate = false;
        }
    }

    /**
     * 加载数据
     */
    protected void lazyLoadData() {

    }
}
