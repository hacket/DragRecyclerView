package com.example.zengfansheng.bbsqupredrag.fragment;

import java.util.ArrayList;

import com.example.zengfansheng.bbsqupredrag.R;
import com.example.zengfansheng.bbsqupredrag.adapter.RecyclerViewAdapter;
import com.example.zengfansheng.bbsqupredrag.decoration.DividerGridItemDecoration;
import com.example.zengfansheng.bbsqupredrag.drag.ItemTouchHelperCallBack;
import com.example.zengfansheng.bbsqupredrag.model.SiteModel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by zengfansheng on 2016/4/5.
 */
public class SquareFragment extends Fragment implements RecyclerViewAdapter.OnItemTouchListener,
        RecyclerViewAdapter.OnItemClickListener, RecyclerViewAdapter.OnItemLongClickListener {

    private static final String TAG = "hacket";

    //    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    private ArrayList<SiteModel> mSiteModels;
    private ItemTouchHelper itemTouchHelper;
    private Context mContext;
    private RecyclerViewAdapter recyclerViewAdapter;

    public static SquareFragment newInstance(ArrayList<SiteModel> siteModels) {
        SquareFragment squareFragment = new SquareFragment();
        Bundle args = new Bundle();
        args.putSerializable("data", siteModels);
        squareFragment.setArguments(args);
        return squareFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = activity.getApplicationContext();

        Bundle arguments = getArguments();
        mSiteModels = (ArrayList<SiteModel>) arguments.getSerializable("data");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 5));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));

        recyclerViewAdapter = new RecyclerViewAdapter(mContext, mSiteModels);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(this);
        recyclerViewAdapter.setOnItemLongClickListener(this);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallBack(recyclerViewAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onItemTouch(RecyclerView.ViewHolder viewHolder, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            itemTouchHelper.startDrag(viewHolder);
            Log.d(TAG, "onItemTouch: ACTION_DOWN start Drag:" + viewHolder.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, Object obj) {
        Log.d(TAG, "onItemClick: " + viewHolder.getAdapterPosition());

        SiteModel siteModel = (SiteModel) obj;

        Toast.makeText(mContext, "go " + siteModel.name, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, Object obj) {
        Log.d(TAG, "onItemLongClick: " + viewHolder.getAdapterPosition());

        Toast.makeText(mContext, "进入编辑模式", Toast.LENGTH_LONG).show();

        recyclerViewAdapter.setOnItemTouchListener(this);
        recyclerViewAdapter.notifyDataSetChanged();
        //        recyclerViewAdapter.setOnItemClickListener(null);
        //        recyclerViewAdapter.setOnItemLongClickListener(null);
        return false;
    }

}