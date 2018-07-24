package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.othershe.library.NiceImageView;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.comment.MusicComment;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.service.ServiceManager;
import com.yzx.xiaomusic.ui.usercenter.UserCenterFragment;
import com.yzx.xiaomusic.utils.CommonUtils;
import com.yzx.xiaomusic.utils.FragmentStartUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;
import com.yzx.xiaomusic.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.ui.usercenter.UserCenterFragment.KEY_USER_ID;

/**
 * @author yzx
 * @date 2018/7/12
 * Description 歌曲评论
 */
public class MusicCommentAdapter extends RecyclerView.Adapter {

    public static final int TYPE_MUSIC = 1;
    public static final int TYPE_TITLE = 2;
    private final SupportFragment parentFragment;

    private MusicComment data;
    private List<MusicComment.CommentsBean> comments;

    public MusicCommentAdapter(SupportFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_MUSIC;
        } else if (position == 1) {
            return TYPE_TITLE;
        }
        //如果有热评
        if (data.getHotComments().size() > 0) {
            if (position == (data.getHotComments().size() + 2)) {
                return TYPE_TITLE;
            }
        }

        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        switch (viewType) {
            case TYPE_MUSIC:
                return new MusicHolder(ResourceUtils.parseLayout(context, R.layout.item_comment_music, parent));
            case TYPE_TITLE:
                return new TitleHolder(ResourceUtils.parseLayout(context, R.layout.item_comment_title, parent));
            default:
                return new CommentHolder(ResourceUtils.parseLayout(context, R.layout.item_comment, parent));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_MUSIC:
                MusicHolder musicHolder = (MusicHolder) holder;
                MusicInfo musicInfo = ServiceManager.getInstance().getService().getMusicInfo();
                if (musicInfo != null) {
                    GlideUtils.loadImg(holder.itemView.getContext(), musicInfo.getAlbumCoverPath(), GlideUtils.TYPE_HEAD, ((MusicHolder) holder).ivCover);
                    musicHolder.tvTitle.setText(musicInfo.getMusicName());
                    musicHolder.tvSubTitle.setText(MusicDataUtils.getSingers(musicInfo));
                }
                break;
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                if (data.getHotComments().size() > 0) {
                    titleHolder.tvTitle.setText(position == 1 ? "精彩评论" : "最新评论");
                } else {
                    titleHolder.tvTitle.setText("最新评论");
                }
                break;
            default:
                int size = data.getHotComments().size();
                //有热评
                if (size > 0) {
                    if (position > 1 && position < size + 2) {
                        MusicComment.HotCommentsBean hotCommentsBean = data.getHotComments().get(position - 2);
                        MusicComment.HotCommentsBean.UserBean user = hotCommentsBean.getUser();
                        setHotCommentData(holder, hotCommentsBean, user);
                    } else {
                        MusicComment.CommentsBean commentsBean = comments.get(position - size - 3);
                        MusicComment.CommentsBean.UserBeanX user = commentsBean.getUser();
                        setCommentData(holder, commentsBean, user);
                    }
                } else {
                    MusicComment.CommentsBean commentsBean = comments.get(position - 2);
                    MusicComment.CommentsBean.UserBeanX user = commentsBean.getUser();
                    setCommentData(holder, commentsBean, user);
                }
                break;
        }
    }

    private void setCommentData(@NonNull RecyclerView.ViewHolder holder, MusicComment.CommentsBean commentsBean, MusicComment.CommentsBean.UserBeanX user) {
        CommentHolder commentHolder = (CommentHolder) holder;
        GlideUtils.loadImg(holder.itemView.getContext(), user.getAvatarUrl(), GlideUtils.TYPE_HEAD, commentHolder.ivHead);
        commentHolder.tvTitle.setText(user.getNickname());
        commentHolder.tvSubTitle.setText(TimeUtils.getFriendlyData(commentsBean.getTime()));
        commentHolder.tvContent.setText(commentsBean.getContent());
        commentHolder.tvCount.setText(CommonUtils.getFriendlyCount(commentsBean.getLikedCount()));
        clickHead(commentHolder.ivHead, user.getUserId());
    }

    public void clickHead(View view, int id) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_USER_ID, String.valueOf(id));
        view.setOnClickListener(v -> FragmentStartUtils.startFragment(parentFragment, new UserCenterFragment(), bundle));
    }

    private void setHotCommentData(@NonNull RecyclerView.ViewHolder holder,
                                   MusicComment.HotCommentsBean hotCommentsBean, MusicComment.HotCommentsBean.UserBean user) {
        CommentHolder commentHolder = (CommentHolder) holder;
        GlideUtils.loadImg(holder.itemView.getContext(), user.getAvatarUrl(), GlideUtils.TYPE_HEAD, commentHolder.ivHead);
        commentHolder.tvTitle.setText(user.getNickname());
        commentHolder.tvSubTitle.setText(TimeUtils.getFriendlyData(hotCommentsBean.getTime()));
        commentHolder.tvContent.setText(hotCommentsBean.getContent());
        commentHolder.tvCount.setText(CommonUtils.getFriendlyCount(hotCommentsBean.getLikedCount()));
        clickHead(commentHolder.ivHead, user.getUserId());
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            List<MusicComment.HotCommentsBean> hotComments = data.getHotComments();
            return (hotComments == null ? 0 : hotComments.size() + 1) + (comments == null ? 0 : comments.size() + 1) + 1;
        } else {
            return 0;
        }
    }

    public void setData(MusicComment data) {
        this.data = data;
        this.comments = data.getComments();
        notifyDataSetChanged();
    }

    public void addData(List<MusicComment.CommentsBean> comments) {
        this.comments.addAll(comments);
        notifyItemRangeInserted(getItemCount() - 1, comments.size());
    }

    class MusicHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        NiceImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;

        public MusicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        NiceImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.ll_user_info)
        LinearLayout llUserInfo;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.iv_thumb)
        ImageView ivThumb;

        public CommentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
