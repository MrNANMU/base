package com.dasong.dinghelper.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ExpandableListView;

import com.dasong.common.utils.LogUtils;

import java.util.List;

public class DingHelpService extends AccessibilityService {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("onCreate");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        LogUtils.e("event -> "+event.toString());
        int eventType = event.getEventType();

    }

    @Override
    public void onInterrupt() {
        LogUtils.e("onInterrupt");
    }
}
