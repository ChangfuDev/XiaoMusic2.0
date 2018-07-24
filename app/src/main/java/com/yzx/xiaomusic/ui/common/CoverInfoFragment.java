package com.yzx.xiaomusic.ui.common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.network.AppHttpClient;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.base.BaseFragment;
import com.yzx.xiaomusic.network.api.DownloadApi;
import com.yzx.xiaomusic.ui.adapter.CommonTagAdapter;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.PathUtils;
import com.yzx.xiaomusic.widget.ShapeTextView;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import okhttp3.ResponseBody;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_DES;
import static com.yzx.xiaomusic.Constant.KEY_TAG;
import static com.yzx.xiaomusic.Constant.KEY_TITLE;

/**
 * Created by yzx on 2018/6/12.
 * Description 专辑、歌单封面等信息
 */
public class CoverInfoFragment extends BaseFragment {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_little_bg)
    ImageView ivLittleBg;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.ll_tags)
    LinearLayout llTags;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.stv_save)
    ShapeTextView stvSave;
    private String cover;
    private String title;

    @Override
    protected int initContentViewId() {
        return R.layout.fragment_cover_info;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator(R.anim.fragment_a_enter, R.anim.fragment_a_exit);
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {

        Bundle arguments = getArguments();

        cover = arguments.getString(KEY_COVER);
        title = arguments.getString(KEY_TITLE);
        String des = arguments.getString(KEY_DES);
        ArrayList<String> tags = arguments.getStringArrayList(KEY_TAG);
        tvTitle.setText(title);
        tvDes.setText(TextUtils.isEmpty(des) ? "暂无描述" : des);
        GlideUtils.loadImg(getContext(), cover, ivLittleBg);
        GlideUtils.loadBlurImg(getContext(), cover, ivBg);

        if (tags != null && tags.size() > 0) {
            flowLayout.setVisibility(View.VISIBLE);
            flowLayout.setAdapter(new CommonTagAdapter(tags));
        } else {
            flowLayout.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.iv_bg, R.id.stv_save, R.id.ll_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bg:
            case R.id.ll_content:
                pop();
                break;
            case R.id.stv_save:
                File file = new File(getDownloadCoverPath());
                if (file.exists()) {
                    showToast(R.string.fileExist);
                } else {
                    saveCover();
                }
                break;
        }
    }

    /**
     * 保存文件
     */
    @SuppressLint("CheckResult")
    private void saveCover() {

        new AppHttpClient.Builder()
                .context(getContext())
                .build()
                .getService(DownloadApi.class)
                .download(cover)
                .subscribeOn(Schedulers.io())
                .map(ResponseBody::byteStream)
                .subscribeOn(Schedulers.computation())
                .doOnNext(inputStream -> writeFile(inputStream, getDownloadCoverPath()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(inputStream -> ToastUtils.showToast("已保存至：" + getDownloadCoverPath()));
    }

    /**
     * 获取下载路径
     *
     * @return
     */
    @NonNull
    private String getDownloadCoverPath() {
        return PathUtils.getDownloadCoverPath(title, cover);
    }

    /**
     * 将输入流写入文件
     *
     * @param inputStream
     * @param filePath
     */
    private void writeFile(InputStream inputStream, String filePath) {

        String TAG = "yglDownload";
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputStream.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputStream.close();
            fos.close();

        } catch (IOException e) {
            Log.i(TAG, "writeFile: " + e.toString());
        }


        // 第二步：其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getContext().getContentResolver(), file.getAbsolutePath(), title, null);
//   /storage/emulated/0/Boohee/1493711988333.jpg
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 第三步：最后通知图库更新
        getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
        //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

    }
}
