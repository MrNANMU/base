package com.dasong.softboo.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.dasong.common.context.BaseActivity;
import com.dasong.common.context.SimpleTitleActivity;
import com.dasong.common.utils.PixUtils;
import com.dasong.softboo.R;

import androidx.annotation.Nullable;

public class HomeActivity extends BaseActivity {

    @Override
    protected int getContentId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        hideStatusBar(true);
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

    /*@Override
    protected TitleBarOptions getTitleBarOptions() {
        return new TitleBarOptionsBuilder()
                .setTitleBarColor(Color.parseColor("#784596"))
                .setTitle("测试标题")
                .setTitleSize(PixUtils.sp2px(8))
                .setTitleColor(Color.parseColor("#ffffff"))
                .build();
    }

    @Override
    protected void onLeftButtonClick() {

    }

    @Override
    protected void onRightButtonClick() {

    }*/
}
