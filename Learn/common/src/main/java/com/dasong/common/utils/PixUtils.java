package com.dasong.common.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.dasong.common.context.BaseApp;

public class PixUtils {

    public static int px2dp(int px){
        final float scale = BaseApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                BaseApp.getContext().getResources().getDisplayMetrics());
    }

    public static int sp2px(int sp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                BaseApp.getContext().getResources().getDisplayMetrics());
    }

    public static int getScreenHeight(){
        DisplayMetrics dm = BaseApp.getContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getScreenWidth(){
        DisplayMetrics dm = BaseApp.getContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取状态栏高度
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        // 获得状态栏高度
        int resourceId = BaseApp.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return BaseApp.getContext().getResources().getDimensionPixelSize(resourceId);
    }

}
