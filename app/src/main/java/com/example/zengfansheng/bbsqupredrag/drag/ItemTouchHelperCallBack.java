package com.example.zengfansheng.bbsqupredrag.drag;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by zengfansheng on 2016/4/1.
 */
public class ItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapterCallback mOnItemTouchHelperAdapterCallback;

    public ItemTouchHelperCallBack(
            ItemTouchHelperAdapterCallback onItemTouchHelperAdapterCallback) {
        this.mOnItemTouchHelperAdapterCallback = onItemTouchHelperAdapterCallback;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlags = 0;
        int swipeFlags = 0;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            // 指定可以支持的拖放和滑动的方向
            dragFlags =
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; //
            // 上下左右drag
            swipeFlags = 0;
        } else {
            dragFlags = 0;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END; // 左右swipe
        }
        return makeMovementFlags(dragFlags, swipeFlags); // 启用了上下左右两种方向。注：上下为拖动
    }

    // 拖拽的时候回调
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder srcViewHolder,
                          RecyclerView.ViewHolder targetViewHolder) {
        // 类型不一样不能交换
        if (srcViewHolder.getItemViewType() != targetViewHolder.getItemViewType()) {
            return false;
        }

        if (mOnItemTouchHelperAdapterCallback != null) {
            mOnItemTouchHelperAdapterCallback
                    .onItemMove(srcViewHolder.getAdapterPosition(), targetViewHolder.getAdapterPosition());
        }

        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mOnItemTouchHelperAdapterCallback != null) {
            // 回调适配器里面的方法，让其刷新数据及界面
            mOnItemTouchHelperAdapterCallback.onItemDismiss(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        //        return super.isLongPressDragEnabled();
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        // return super.isItemViewSwipeEnabled();
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
