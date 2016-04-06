package com.example.zengfansheng.bbsqupredrag;

import java.util.ArrayList;

import com.example.zengfansheng.bbsqupredrag.model.SiteModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by zengfansheng on 2016/4/5.
 */
public class SquareDataManagerTest {

    private static ArrayList<SiteModel> mSiteModels;
    private static Context mContext;

    public static ArrayList<SiteModel> getDatas(Context context) {
        mContext = context;
        initDatas();

        return mSiteModels;
    }

    private static void initDatas() {
        mSiteModels = new ArrayList<SiteModel>();
        SiteModel siteModel0 = new SiteModel(getDrawable1(R.mipmap.ic_launcher), "网址导航", 1);
        SiteModel siteModel1 = new SiteModel(getDrawable1(R.drawable.home_icon_163), "163");
        SiteModel siteModel2 = new SiteModel(getDrawable1(R.drawable.home_icon_amazon), "amazon");
        SiteModel siteModel3 = new SiteModel(getDrawable1(R.drawable.home_icon_ask), "ask");
        SiteModel siteModel4 = new SiteModel(getDrawable1(R.drawable.home_icon_baidu), "baidu");
        SiteModel siteModel5 = new SiteModel(getDrawable1(R.drawable.home_icon_facebook), "facebook");
        SiteModel siteModel6 = new SiteModel(getDrawable1(R.drawable.home_icon_google), "google");
        SiteModel siteModel7 = new SiteModel(getDrawable1(R.drawable.home_icon_iqiyi), "iqiyi");
        SiteModel siteModel8 = new SiteModel(getDrawable1(R.drawable.home_icon_jd), "jd");
        SiteModel siteModel9 = new SiteModel(getDrawable1(R.drawable.home_icon_qq), "qq");
        SiteModel siteModel10 = new SiteModel(getDrawable1(R.drawable.home_icon_uol), "uol");
        SiteModel siteModel11 = new SiteModel(getDrawable1(R.drawable.home_icon_bbc), "bbc");
        SiteModel siteModel12 = new SiteModel(getDrawable1(R.drawable.home_icon_espn), "espn");
        SiteModel siteModel13 = new SiteModel(getDrawable1(R.drawable.home_icon_globo), "globo");
        SiteModel siteModel14 = new SiteModel(getDrawable1(R.drawable.home_icon_horoscope), "horoscope");
        SiteModel siteModel15 = new SiteModel(getDrawable1(R.drawable.home_icon_kapook), "kapook");
        SiteModel siteModel16 = new SiteModel(getDrawable1(R.drawable.home_icon_liputan6), "iputan6");
        mSiteModels.add(siteModel0);
        mSiteModels.add(siteModel1);
        mSiteModels.add(siteModel2);
        mSiteModels.add(siteModel3);
        mSiteModels.add(siteModel4);
        mSiteModels.add(siteModel5);
        mSiteModels.add(siteModel6);
        mSiteModels.add(siteModel7);
        mSiteModels.add(siteModel8);
        mSiteModels.add(siteModel9);
        mSiteModels.add(siteModel10);
        mSiteModels.add(siteModel11);
        mSiteModels.add(siteModel12);
        mSiteModels.add(siteModel13);
        mSiteModels.add(siteModel14);
        mSiteModels.add(siteModel15);
        mSiteModels.add(siteModel16);
    }

    static Drawable getDrawable1(int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }

}
