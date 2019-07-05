package com.dasong.dinghelper.utils;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import com.dasong.common.utils.LogUtils;
import com.dasong.dinghelper.service.DingHelpService;

import java.util.List;

public class AppHelper {
    /**
     * 判断AccessibilityService服务是否已经启动
     * @param context
     * @return
     */
    public static boolean isStartAccessibilityService(Context context){
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> serviceInfos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : serviceInfos) {
            String id = info.getId();
            LogUtils.e("all -->" + id);
            if (id.contains(DingHelpService.class.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void startApp(Activity activity, String pkgName){
        PackageManager packageManager = activity.getPackageManager();
        Intent intent=new Intent();
        intent =packageManager.getLaunchIntentForPackage(pkgName);
        if(intent==null){
            Toast.makeText(activity, "未安装", Toast.LENGTH_LONG).show();
        }else {
            activity.startActivity(intent);
        }
    }

}
