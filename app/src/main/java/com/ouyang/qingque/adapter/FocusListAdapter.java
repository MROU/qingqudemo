package com.ouyang.qingque.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ouyang.qingque.model.FocusBean;
import com.ouyang.qingque.util.TimeUtils;
import com.ouyang.qingque.view.GlideCircleTransform;
import com.ouyang.testtab.R;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


/**
 * Created by wang on 2016/1/3.
 */
public class FocusListAdapter extends BasePagerAdapter{
    public void setmInfos(List<FocusBean> mInfos) {
        this.mInfos = mInfos;
    }
    private List<FocusBean> mInfos;
    LayoutInflater layoutInflator;
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;
    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    public FocusListAdapter(Context context, List<FocusBean> info){
        super(context,info);
        this.mInfos=info;
    }

    @Override
    public int getItemCount(){
        if(mHeaderView == null && mFooterView == null){
            return mInfos.size();
        }else if(mHeaderView == null && mFooterView != null){
            return mInfos.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return mInfos.size() + 1;
        }else {
            return mInfos.size() + 2;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new ViewHolder(mFooterView);
        }
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_focus,viewGroup,false);
        return new ViewHolder(view);
    }


    /**
     * 获取不同类型的item
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {
        //如果添加mRecyclerView  headerview 需要将pos改为pos-1
        if(getItemViewType(pos) == TYPE_NORMAL){
            if(viewHolder instanceof ViewHolder){
                FocusBean infos = mInfos.get(pos-1);
                Glide.with(mContext).load(infos.getCreatorDetail().getUserIcon()).centerCrop().placeholder(R.mipmap.ic_launcher)
                        .transform(new GlideCircleTransform(mContext,2,mContext.getResources().getColor(android.R.color.white)))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(((FocusListAdapter.ViewHolder)viewHolder).civ_header_item);
                Glide.with(mContext).load(infos.getNoteAttaches().get(0).getAttachCover().getUrl()).into(((FocusListAdapter.ViewHolder)viewHolder).img_item_focus);
                ((FocusListAdapter.ViewHolder)viewHolder).tv_author_item.setText(infos.getCreatorDetail().getUserNick());

                String timeStr=TimeUtils.longToDateStr(infos.getGmtCreateStamp());
                ((FocusListAdapter.ViewHolder)viewHolder).tv_time_item.setText(TimeUtils.formatTime(timeStr));
                ((FocusListAdapter.ViewHolder)viewHolder).tv_content_item.setText(infos.getContent());
                ((FocusListAdapter.ViewHolder)viewHolder).jzVideoPlayer.setUp(
                        infos.getNoteAttaches().get(0).getUrl(),JZVideoPlayer.SCREEN_WINDOW_LIST,
                        infos.getNoteAttaches().get(0).getName());
                Glide.with(((FocusListAdapter.ViewHolder)viewHolder).jzVideoPlayer.getContext()).load(infos.getNoteAttaches().get(0).getAttachCover().getUrl()).into(((FocusListAdapter.ViewHolder)viewHolder).jzVideoPlayer.thumbImageView);
                return;
            }
            return;
        }else if(getItemViewType(pos) == TYPE_HEADER){
            return;
        }else{
            return;
        }

    }


    public  class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView civ_header_item,img_item_focus;
        TextView tv_author_item,tv_time_item,tv_content_item;
        JZVideoPlayerStandard jzVideoPlayer;
        public ViewHolder(View itemView) {
            super(itemView);
            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView){
                return;
            }
            if (itemView == mFooterView){
                return;
            }
            civ_header_item=itemView.findViewById(R.id.civ_header_item);
            tv_author_item=itemView.findViewById(R.id.tv_author_item);
            tv_time_item=itemView.findViewById(R.id.tv_time_item);
            img_item_focus=itemView.findViewById(R.id.img_item_focus);
            tv_content_item=itemView.findViewById(R.id.tv_content_item);
            jzVideoPlayer = itemView.findViewById(R.id.videoplayer);
        }
    }
}
