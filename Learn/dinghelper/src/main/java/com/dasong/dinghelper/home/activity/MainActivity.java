package com.dasong.dinghelper.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dasong.common.context.BaseActivity;
import com.dasong.common.utils.LogUtils;
import com.dasong.dinghelper.R;
import com.dasong.dinghelper.service.DingHelpService;
import com.dasong.dinghelper.utils.AppHelper;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {

    TextView tv_start;

    @Override
    protected int getContentId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        tv_start = $(R.id.tv_start);
        tv_start.setOnClickListener( v -> {
            AppHelper.startApp(this,"com.alibaba.android.rimet");
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void resumeView() {

    }

    @Override
    protected void resumeData() {

    }
}
