package com.example.zengfansheng.bbsqupredrag.adapter;

import java.util.Collections;
import java.util.List;

import com.example.zengfansheng.bbsqupredrag.R;
import com.example.zengfansheng.bbsqupredrag.drag.ItemTouchHelperAdapterCallback;
import com.example.zengfansheng.bbsqupredrag.model.SiteModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerView.Adapter
 * Created by zengfansheng on 2016/4/1.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
        implements ItemTouchHelperAdapterCallback {

    private Context mContext;

    private OnItemTouchListener mOnItemTouchListener;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private final List<SiteModel> mSiteModels;

    public RecyclerViewAdapter(Context context, List<SiteModel> siteModels) {
        this.mContext = context;
        this.mSiteModels = siteModels;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(mContext, R.layout.drag_item, null);
        final RecyclerViewHolder holder = new RecyclerViewHolder(view);

        return holder;
    }

    @Override
    public int getItemCount() {
        return mSiteModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE.WEB_NAVI.ordinal();
        }
        return TYPE.NORMAL.ordinal();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        bindViewHolderListener(holder, position);

        SiteModel siteModel = mSiteModels.get(position);

        holder.iconIv.setImageDrawable(siteModel.icon);
        holder.nameTv.setText(siteModel.name);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mSiteModels, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mSiteModels.remove(position);
        notifyItemChanged(position);
    }

    // ==== listener ====

    private void bindViewHolderListener(final RecyclerViewHolder holder, int position) {
        Log.d("hacket", "bindViewHolderListener: " + holder.getAdapterPosition());

        holder.itemView.setTag(mSiteModels.get(position));

        if (mOnItemTouchListener != null) {
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return mOnItemTouchListener.onItemTouch(holder, event);
                }
            });
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder, holder.itemView.getTag());
                }
            });
        }

        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onItemLongClick(holder, holder.itemView.getTag());
                }
            });
        }
    }

    public interface OnItemTouchListener {
        boolean onItemTouch(RecyclerView.ViewHolder viewHolder, MotionEvent event);
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, Object obj);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(/*RecyclerView recyclerView, */RecyclerView.ViewHolder viewHolder, Object obj);
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListener = onItemTouchListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemClickLongListener) {
        this.mOnItemLongClickListener = onItemClickLongListener;
    }

    // ==== listener ====

    enum TYPE {
        NORMAL,
        WEB_NAVI
    }

}
