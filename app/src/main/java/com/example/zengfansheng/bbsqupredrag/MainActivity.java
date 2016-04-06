package com.example.zengfansheng.bbsqupredrag;

import java.util.ArrayList;
import java.util.List;

import com.example.zengfansheng.bbsqupredrag.adapter.ViewPagerAdapter;
import com.example.zengfansheng.bbsqupredrag.fragment.SquareFragment;
import com.example.zengfansheng.bbsqupredrag.model.SiteModel;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "hacket";

    //    @Bind(R.id.vp)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vp);

        Button button = (Button) findViewById(R.id.bind);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                bind(v);
            }
        });
        //        button.setOnLongClickListener(new View.OnLongClickListener() {
        //            @Override
        //            public boolean onLongClick(View v) {
        //                Log.d(TAG, "onLongClick: ");
        //                return false;
        //            }
        //        });
        //        button.setOnTouchListener(new View.OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View v, MotionEvent event) {
        //                Log.d(TAG, "onTouch: " + event.getAction());
        //                return true;
        //            }
        //        });
    }

    //    @OnClick(R.id.bind)
    public void bind(View view) {

        List<SquareFragment> squareFragments = new ArrayList<>();
        ArrayList<SiteModel> mSiteModels = (SquareDataManagerTest.getDatas(getApplicationContext()));
        Log.d(TAG, "bind--mSiteModels.size():" + mSiteModels.size());
        squareFragments.add(SquareFragment.newInstance(mSiteModels));
        squareFragments.add(SquareFragment.newInstance(mSiteModels));

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), squareFragments));
    }

}
