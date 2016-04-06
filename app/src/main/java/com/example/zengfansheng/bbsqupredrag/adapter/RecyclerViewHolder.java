package com.example.zengfansheng.bbsqupredrag.adapter;

import com.example.zengfansheng.bbsqupredrag.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zengfansheng on 2016/4/5.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView iconIv;
    TextView nameTv;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        iconIv = (ImageView) itemView.findViewById(R.id.icon);
        nameTv = (TextView) itemView.findViewById(R.id.name);
    }
}
