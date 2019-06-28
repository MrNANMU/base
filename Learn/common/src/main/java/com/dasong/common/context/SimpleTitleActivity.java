package com.dasong.common.context;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dasong.common.R;
import com.dasong.common.utils.PixUtils;

import androidx.annotation.Nullable;

abstract public class SimpleTitleActivity extends BaseActivity {

    private RelativeLayout rl_titlebar;
    private FrameLayout fl_left_button;
    private FrameLayout fl_right_button;
    private ImageView iv_left_button;
    private ImageView iv_right_button;
    private TextView tv_title;

    private TitleBarListener titleBarListener;

    @Override
    protected ViewGroup initStatusBar() {
        ViewGroup base = super.initStatusBar();
        View root = initBar();
        base.addView(root);
        return base;
    }

    private View initBar(){
        View titleBar = LayoutInflater.from(this).inflate(R.layout.activity_simple_title,null);
        rl_titlebar = titleBar.findViewById(R.id.rl_titlebar);
        fl_left_button = titleBar.findViewById(R.id.fl_left_button);
        fl_right_button = titleBar.findViewById(R.id.fl_right_button);
        iv_left_button = titleBar.findViewById(R.id.iv_left_button);
        iv_right_button = titleBar.findViewById(R.id.iv_right_button);
        tv_title = titleBar.findViewById(R.id.tv_title);

        titleBarListener = new TitleBarListener();
        fl_left_button.setOnClickListener(titleBarListener);
        fl_right_button.setOnClickListener(titleBarListener);

        final TitleBarOptions options = getTitleBarOptions();
        if(options != null){
            tv_title.setText(options.title);
            tv_title.setTextSize(options.titleSize);
            tv_title.setTextColor(options.titleColor);
            rl_titlebar.setBackgroundColor(options.titleBarColor);
            iv_left_button.setBackgroundResource(options.leftImgResId);
            iv_right_button.setBackgroundResource(options.rightImgResId);
            setStatusColor(options.statusBarColor);
        }
        return titleBar;
    }

    /*abstract protected int getTitleBarColor();

    abstract protected String getBarTitle();

    abstract protected int getLeftImageResId();

    abstract protected int getRightImageResId();*/

    abstract protected TitleBarOptions getTitleBarOptions();

    abstract protected void onLeftButtonClick();

    abstract protected void onRightButtonClick();

    public void setTitleBarColor(int color,boolean useToStatusBar){
        rl_titlebar.setBackgroundColor(color);
        if(useToStatusBar) setStatusColor(color);
    }

    public void setTitleBarColor(int color){
        setTitleBarColor(color,false);
    }

    private class TitleBarListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.fl_left_button) {
                onLeftButtonClick();
            }else if(i == R.id.fl_right_button){
                onRightButtonClick();
            }
        }
    }

    public static class TitleBarOptions{
        private int statusBarColor = Color.parseColor("#ffffff");
        public int titleColor = Color.parseColor("#000000");
        public int titleBarColor = Color.parseColor("#ffffff");
        public int titleSize = PixUtils.sp2px(16);
        public String title;
        public int leftImgResId;
        public int rightImgResId;
    }

    public static class TitleBarOptionsBuilder{
        private int statusBarColor = Color.parseColor("#ffffff");
        private int titleColor = Color.parseColor("#000000");
        private int titleBarColor = Color.parseColor("#ffffff");
        private int titleSize = PixUtils.sp2px(16);
        private String title;
        private int leftImgResId;
        private int rightImgResId;

        public TitleBarOptionsBuilder setStatusBarColor(int color){
            this.statusBarColor = color;
            return this;
        }

        public TitleBarOptionsBuilder setTitleBarColor(int titleBarColor){
            this.titleBarColor = titleBarColor;
            return this;
        }

        public TitleBarOptionsBuilder setTitleSize(int titleSize){
            this.titleSize = titleSize;
            return this;
        }

        public TitleBarOptionsBuilder setTitle(String title){
            this.title = title;
            return this;
        }

        public TitleBarOptionsBuilder setLeftImgResId(int leftImgResId){
            this.leftImgResId = leftImgResId;
            return this;
        }

        public TitleBarOptionsBuilder setRightImgResId(int rightImgResId){
            this.rightImgResId = rightImgResId;
            return this;
        }

        public TitleBarOptionsBuilder setTitleColor(int color){
            this.titleColor = color;
            return this;
        }

        public TitleBarOptions build(){
            TitleBarOptions options = new TitleBarOptions();
            options.leftImgResId = leftImgResId;
            options.rightImgResId = rightImgResId;
            options.title = title;
            options.titleBarColor = titleBarColor;
            options.titleSize = titleSize;
            options.titleColor = titleColor;
            options.statusBarColor = statusBarColor;
            return options;
        }
    }
}
