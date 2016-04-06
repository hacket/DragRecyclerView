package com.example.zengfansheng.bbsqupredrag.decoration;

import com.example.zengfansheng.bbsqupredrag.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * Grid RecyclerView 的ItemDecoration
 * Created by zengfansheng on 2016/4/5.
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Drawable mDividerBg;

    public DividerGridItemDecoration(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        mDividerBg = ContextCompat.getDrawable(mContext, R.drawable.divider_bg2);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        drawHorizontal(c, parent, state);
        drawVertical(c, parent, state);
    }

    /**
     * 水平方向
     *
     * @param c      Canvas
     * @param parent RecyclerView
     * @param state  RecyclerView.State
     */
    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int leftMargin = params.leftMargin;
            int rightMargin = params.rightMargin;
            int bottomMargin = params.bottomMargin;

            int left = leftMargin + childView.getPaddingLeft();
            int right = childView.getRight() + rightMargin + mDividerBg.getIntrinsicWidth();
            int top = childView.getBottom() + bottomMargin;
            int bottom = top + mDividerBg.getIntrinsicHeight();

            mDividerBg.setBounds(left, top, right, bottom);
            mDividerBg.draw(c);
        }
    }

    /**
     * 竖直方向
     *
     * @param c      Canvas
     * @param parent RecyclerView
     * @param state  RecyclerView.State
     */
    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int rightMargin = params.rightMargin;
            int topMargin = params.topMargin;

            int left = childView.getRight() + rightMargin;
            int right = left + mDividerBg.getIntrinsicWidth();
            int top = childView.getTop() - topMargin;
            int bottom = childView.getBottom() + topMargin;
            mDividerBg.setBounds(left, top, right, bottom);
            mDividerBg.draw(c);

        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int spanCount = getSpanCount(parent);
        int itemCount = parent.getAdapter().getItemCount();

        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        Log.d("hacket", "getItemOffsets: itemPosition=" + itemPosition);

        boolean isLastRaw = isLastRaw(parent, itemPosition, spanCount, itemCount);
        boolean isLastCol = isLastCol(parent, itemPosition, spanCount, itemCount);

        if (isLastCol) { // 如果是最后一列，则不需要绘制右边
            outRect.set(0, 0, 0, mDividerBg.getIntrinsicHeight());
        } else if (isLastRaw) { // 如果是最后一行，则不需要绘制底部
            outRect.set(0, 0, mDividerBg.getIntrinsicWidth(), 0);
        } else {
            outRect.set(0, 0, mDividerBg.getIntrinsicWidth(), mDividerBg.getIntrinsicHeight());
        }

    }

    /**
     * 最后一列
     *
     * @param parent       RecyclerView
     * @param itemPosition itemPosition
     * @param spanCount    行数
     * @param itemCount    item总个数
     *
     * @return true是
     */
    private boolean isLastCol(RecyclerView parent, int itemPosition, int spanCount, int itemCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((itemPosition + 1) % spanCount == 0) {// 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((itemPosition + 1) % spanCount == 0) {// 如果是最后一列，则不需要绘制右边
                    return true;
                }
            } else {
                int item = itemCount - itemCount % spanCount;
                if (item >= itemCount)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 最后一行
     *
     * @param parent       RecyclerView
     * @param itemPosition itemPosition item的位置
     * @param spanCount    行数
     * @param itemCount    item总个数
     *
     * @return true是
     */
    private boolean isLastRaw(RecyclerView parent, int itemPosition, int spanCount, int itemCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int item = itemCount - itemCount % spanCount;  // itemCount- [0~spanCount-1之间]
            if (itemPosition >= item) { // 最后一行
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == RecyclerView.HORIZONTAL) {
                if ((itemPosition + 1) % spanCount == 0) {
                    return true;
                }
            } else if (orientation == RecyclerView.VERTICAL) {
                int item = itemCount % spanCount;
                if (itemPosition >= item) { // 最后一行
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回列数
     *
     * @param parent
     *
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

}