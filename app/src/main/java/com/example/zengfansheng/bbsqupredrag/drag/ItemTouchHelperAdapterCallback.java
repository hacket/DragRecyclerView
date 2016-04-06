package com.example.zengfansheng.bbsqupredrag.drag;

/**
 * RecyclerView.Adapter实现
 * <br/>
 * ItemTouchHelper.Callback调用
 * <br/>
 * Created by zengfansheng on 2016/4/1.
 */
public interface ItemTouchHelperAdapterCallback {

    /**
     * 当拖拽drag的时候回调</br>
     * 可以在此方法里面实现：拖拽条目刷新动画
     *
     * @param fromPosition 从哪个位置开始拖拽
     * @param toPosition   拖拽到哪个位置
     */
    void onItemMove(int fromPosition, int toPosition);

    /**
     * 当条目被swipe移除的时候应该回调的
     *
     * @param position
     */
    void onItemDismiss(int position);
}
