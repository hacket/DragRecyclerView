package com.example.zengfansheng.bbsqupredrag.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * 兼容性工具包
 * Created by zengfansheng on 2016/3/30.
 */
public class CompatUtil {

    @SuppressWarnings("deprecation")
    public static void setBackground(View view, Drawable drawable) {
        if (view != null) {
            // android4.1
            if (Build.VERSION.SDK_INT < 16) {
                view.setBackgroundDrawable(drawable);
            } else {
                view.setBackground(drawable);
            }
        }
    }

    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        return ContextCompat.getDrawable(context, id);
    }

}
