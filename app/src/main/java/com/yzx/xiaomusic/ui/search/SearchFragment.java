package com.yzx.xiaomusic.ui.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;

import com.yzx.commonlibrary.base.CommonBaseFragment;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.ui.adapter.HotSearchAdapter;
import com.yzx.xiaomusic.ui.adapter.SearchHistoryAdapter;
import com.yzx.xiaomusic.widget.simplelistenner.SimpleTextWathcer;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SearchFragment extends CommonBaseFragment implements TagFlowLayout.OnTagClickListener {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    Unbinder unbinder;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ListPopupWindow listPopupWindow;
    private List<String> hotSearchs;
    private ArrayList<String> searchHistorys;


    @Override
    protected int initContentViewId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        hotSearchs = new ArrayList<>();
        hotSearchs.add("刘惜君");
        hotSearchs.add("杨子晓");
        hotSearchs.add("一生所爱");
        hotSearchs.add("杨子晓");
        hotSearchs.add("一生所爱");
        hotSearchs.add("杨子晓");
        hotSearchs.add("一生所爱");
        hotSearchs.add("杨子晓");
        hotSearchs.add("一生所爱");
        hotSearchs.add("杨子晓");
        hotSearchs.add("一生所爱");

        searchHistorys = new ArrayList<>();
        searchHistorys.add("hello");
        searchHistorys.add("一生所爱");
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        //搜索框操作
        ivClear.setVisibility(View.GONE);
        if (listPopupWindow == null) {
            listPopupWindow = new ListPopupWindow(getContext());
        }

        listPopupWindow.setAnchorView(tb);
        listPopupWindow.setVerticalOffset(-15);
        listPopupWindow.setHorizontalOffset(15);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);

        etSearch.addTextChangedListener(new SimpleTextWathcer() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    adapter.clear();
                    adapter.insert(String.valueOf(s), adapter.getCount());
                    listPopupWindow.setAdapter(adapter);
                    listPopupWindow.show();
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    dismissPop();
                    ivClear.setVisibility(View.GONE);
                }
            }
        });

        //热门搜索
        flowLayout.setAdapter(new HotSearchAdapter(hotSearchs));
        flowLayout.setOnTagClickListener(this);

        //搜索历史

        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        searchHistoryAdapter.setData(searchHistorys);
        recyclerView.setAdapter(searchHistoryAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Navigation.findNavController(etSearch).popBackStack(R.id.mainFragment, false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onPause() {
        dismissPop();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_clear, R.id.ll_singer_classify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etSearch.setText(null);
                dismissPop();
                break;
            case R.id.ll_singer_classify:
                showToast(R.string.commingSoon);
                break;
        }

    }

    private void dismissPop() {
        if (listPopupWindow != null && listPopupWindow.isShowing()) {
            listPopupWindow.dismiss();
        }
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        showToast(hotSearchs.get(position));
        return true;
    }
}
