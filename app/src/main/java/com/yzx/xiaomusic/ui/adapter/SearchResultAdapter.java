package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.othershe.library.NiceImageView;
import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.AlbumInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;
import com.yzx.xiaomusic.model.entity.search.SearchMvResult;
import com.yzx.xiaomusic.model.entity.search.SearchRadioResult;
import com.yzx.xiaomusic.model.entity.search.SearchSingerResult;
import com.yzx.xiaomusic.model.entity.search.SearchUserResult;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.utils.TimeUtils;
import com.yzx.xiaomusic.widget.ShapeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_ALBUM;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_MV;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_RADIO;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_SINGER;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_SONG_SHEET;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_USER;

public class SearchResultAdapter extends CommonBaseAdapter<RecyclerView.ViewHolder, Object> {


    private final int searchType;


    public SearchResultAdapter(int searchType) {
        super();
        this.searchType = searchType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (searchType) {
            case TYPE_SEARCH_MV:
                return new MvHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search_result_video, parent));
            case TYPE_SEARCH_SINGER:
                return new SingerHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search_result_singer, parent));
            case TYPE_SEARCH_ALBUM:
                return new AlbumHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search_result_album, parent));
            case TYPE_SEARCH_SONG_SHEET:
                return new SongSheetHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_common_song_sheet, parent));
            case TYPE_SEARCH_RADIO:
                return new RadioHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search_result_radio, parent));
            case TYPE_SEARCH_USER:
                return new UserHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search_result_user, parent));
            default:
                return new MusicHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_search_result_music, parent));
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Context context = holder.itemView.getContext();
        switch (searchType) {
//            case TYPE_SEARCH_MUSIC:
//                dealMusicResult(context, (MusicHolder) holder, position);
//                break;
            case TYPE_SEARCH_MV:
                dealMvResult((MvHolder) holder, position);
                break;
            case TYPE_SEARCH_SINGER:
                dealSingerResult(context, (SingerHolder) holder, position);
                break;
            case TYPE_SEARCH_ALBUM:
                dealAlbumResult(context, (AlbumHolder) holder, position);
                break;
            case TYPE_SEARCH_SONG_SHEET:
                dealSongSheetResult(context, (SongSheetHolder) holder, position);
                break;
            case TYPE_SEARCH_RADIO:
                dealRadioResult(context, (RadioHolder) holder, position);
                break;
            case TYPE_SEARCH_USER:
                dealUserResult(context, (UserHolder) holder, position);
                break;
            default:
                dealMusicResult(context, (MusicHolder) holder, position);
                break;
        }

    }


    private void dealUserResult(Context context, UserHolder holder, int position) {
        SearchUserResult.ResultBean.UserprofilesBean userprofilesBean = (SearchUserResult.ResultBean.UserprofilesBean) datas.get(position);
        GlideUtils.loadCircleImg(context, userprofilesBean.getAvatarUrl(), holder.ivHead);
        holder.tvTitle.setText(userprofilesBean.getNickname());
        String signature = userprofilesBean.getSignature();
        holder.tvSubTitle.setText(signature);
        holder.tvSubTitle.setVisibility(TextUtils.isEmpty(signature) ? View.GONE : View.VISIBLE);
        holder.ivSex.setImageResource(userprofilesBean.getGender() == 1 ? R.drawable.yp : R.drawable.yy);
        holder.ivUserType.setVisibility(View.VISIBLE);
        if (userprofilesBean.getUserType() > 0) {
            holder.ivUserType.setImageResource(R.drawable.z2);
        } else if (userprofilesBean.getVipType() > 0) {
            holder.ivUserType.setImageResource(R.drawable.z7);
        } else {
            holder.ivUserType.setVisibility(View.GONE);
        }
    }

    private void dealRadioResult(Context context, RadioHolder holder, int position) {
        SearchRadioResult.ResultBean.DjRadiosBean radiosBean = (SearchRadioResult.ResultBean.DjRadiosBean) datas.get(position);
        GlideUtils.loadImg(context, radiosBean.getPicUrl(), holder.ivHead);
        holder.tvTitle.setText(radiosBean.getName());
        holder.tvSubTitle.setText(radiosBean.getDj().getNickname());
    }

    private void dealSongSheetResult(Context context, SongSheetHolder holder, int position) {
        SongSheetInfo songSheetInfo = (SongSheetInfo) datas.get(position);
        GlideUtils.loadImg(context, songSheetInfo.getCoverUrl(), holder.ivHead);
        holder.tvTitle.setText(songSheetInfo.getTitle());
        holder.tvSubTitle.setText(String.format("%s首", songSheetInfo.getMusicCount()));
        holder.tvAuthor.setText(String.format("by %s，播放了%s次", songSheetInfo.getCreatorNickName(), songSheetInfo.getPlayCount()));
    }

    private void dealAlbumResult(Context context, AlbumHolder holder, int position) {
        AlbumInfo albumInfo = (AlbumInfo) datas.get(position);
        GlideUtils.loadImg(context, albumInfo.getCover(), holder.ivHead);
        holder.tvTitle.setText(albumInfo.getName());
        holder.tvSubTitle.setText(albumInfo.getSingName());
        holder.tvDate.setText(TimeUtils.getFormatData(albumInfo.getDate(), "yyyy.MM.dd"));
    }

    private void dealSingerResult(Context context, SingerHolder holder, int position) {

        SearchSingerResult.ResultBean.ArtistsBean artistsBean = (SearchSingerResult.ResultBean.ArtistsBean) datas.get(position);
        GlideUtils.loadImg(context, artistsBean.getImg1v1Url(), holder.ivHead);
        holder.tvTitle.setText(artistsBean.getName());
        holder.llIn.setVisibility(artistsBean.getAccountId() > 0 ? View.VISIBLE : View.GONE);
    }

    private void dealMvResult(MvHolder holder, int position) {
        SearchMvResult.ResultBean.MvsBean mvsBean = (SearchMvResult.ResultBean.MvsBean) (datas.get(position));

        GlideUtils.loadImg(holder.ivMv.getContext(), mvsBean.getCover(), holder.ivMv);
        holder.tvDuration.setText(TimeUtils.getFormatData(mvsBean.getDuration(), TimeUtils.FORMAT_MM_SS));
        holder.tvPlayCount.setText(String.valueOf(mvsBean.getPlayCount()));
        holder.tvTitle.setText(mvsBean.getName());
        holder.tvSubTitle.setText(MusicDataUtils.getSingers(mvsBean.getArtists()));
    }

    private void dealMusicResult(Context context, MusicHolder holder, int position) {

        MusicInfo musicInfo = (MusicInfo) (datas.get(position));
        holder.tvTitle.setText(musicInfo.getMusicName());
        holder.tvSubTitle.setText(String.format("%s -- %s", MusicDataUtils.getSingers(musicInfo), musicInfo.getAlbumName()));
        holder.ivMv.setVisibility(TextUtils.equals("0", musicInfo.getMvId()) ? View.GONE : View.VISIBLE);

    }

    class MusicHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.iv_mv)
        ImageView ivMv;
        @BindView(R.id.iv_more)
        ImageView ivMore;

        public MusicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MvHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_mv)
        ImageView ivMv;
        @BindView(R.id.tv_play_count)
        TextView tvPlayCount;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_duration)
        TextView tvDuration;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;

        public MvHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SingerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ll_in)
        LinearLayout llIn;

        public SingerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AlbumHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public AlbumHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SongSheetHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;

        public SongSheetHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class RadioHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;

        public RadioHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class UserHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        NiceImageView ivHead;
        @BindView(R.id.iv_sex)
        ImageView ivSex;
        @BindView(R.id.iv_user_type)
        ImageView ivUserType;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.tv_follow)
        ShapeTextView tvFollow;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
