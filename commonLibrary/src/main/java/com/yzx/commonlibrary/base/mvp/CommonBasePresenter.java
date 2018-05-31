package com.yzx.commonlibrary.base.mvp;

/**
 * Created by yzx on 2018/4/3.
 * Description
 */
public abstract class CommonBasePresenter<V extends ICommonBaseView, M extends ICommonBaseModel> implements ICommonBasePresenter<V> {


    public M mModel;
    public V mView;


    @Override
    public void onCreate(V v) {
        mView = v;
        mModel = getModel();
    }

    protected abstract M getModel();

    @Override
    public void onDestroy() {
        mModel.onDestroy();
    }
}
