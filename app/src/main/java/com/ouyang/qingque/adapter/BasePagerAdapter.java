package com.ouyang.qingque.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class BasePagerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<T> mDate;
    public Context mContext;
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;
    /**
     * 添加数据
     *
     * @param date
     */
    public void addDatas(List<T> date) {
        mDate.addAll(date);
        notifyDataSetChanged();
    }


    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    /**
     * 升级数据
     */
    public void upDates(List<T> date){
        mDate = date;
        notifyDataSetChanged();
    }

    /**
     * 获得start
     */
    public int getStart(){
        return mDate.size();
    }
    /**
     * 点击长按回调
     */
    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void ItemClickListener(View view, String id, String img);

        void ItemLongClickListener(View view, int postion);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    public BasePagerAdapter(Context context, List<T> date){
        mContext = context;
        mDate = date;
    }
    /**
     * 获取不同类型的item
     */
    @Override
    public int getItemCount(){
        if(mHeaderView == null && mFooterView == null){
            return mDate.size();
        }else if(mHeaderView == null && mFooterView != null){
            return mDate.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return mDate.size() + 1;
        }else {
            return mDate.size() + 2;
        }
    }
    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 解决StaggeredGridLayoutManager布局添加脚布局的问题
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams && holder.getLayoutPosition() == getItemCount() - 1) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 解决GridLayoutManager添加脚布局的问题
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                //这个方法的返回值决定了我们每个position上的item占据的单元格个数
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_FOOTER) {
                        return gridManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }
}
