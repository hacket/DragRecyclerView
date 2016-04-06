package com.example.zengfansheng.bbsqupredrag.model;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

/**
 * Created by zengfansheng on 2016/4/1.
 */
public class SiteModel implements Serializable {
    public Drawable icon;
    public String name;
    public int type;

    public SiteModel(Drawable icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public SiteModel(Drawable icon, String name, int type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }
}
