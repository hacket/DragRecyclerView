package com.example.zengfansheng.bbsqupredrag.adapter;

import java.util.List;

import com.example.zengfansheng.bbsqupredrag.fragment.SquareFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zengfansheng on 2016/4/5.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<SquareFragment> squareFragments;

    public ViewPagerAdapter(FragmentManager fm, List<SquareFragment> squareFragments) {
        super(fm);
        this.squareFragments = squareFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return squareFragments.get(position);
    }

    @Override
    public int getCount() {
        return squareFragments.size();
    }
}
