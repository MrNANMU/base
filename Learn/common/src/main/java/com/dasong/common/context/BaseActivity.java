package com.dasong.common.context;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dasong.common.R;
import com.dasong.common.utils.PixUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

abstract public class BaseActivity extends AppCompatActivity {

    private ImageView iv_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置全屏
        beginInit(savedInstanceState);
    }

    abstract protected int getContentId();

    protected void beginInit(@Nullable Bundle savedInstanceState){
        ViewGroup root = initStatusBar();
        View content = LayoutInflater.from(this).inflate(getContentId(),null);
        root.addView(content);
        setContentView(root);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    protected ViewGroup initStatusBar(){
        ViewGroup root = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base,null);
        iv_status = root.findViewById(R.id.iv_status);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)iv_status.getLayoutParams();
        lp.height = PixUtils.getStatusBarHeight();
        iv_status.setLayoutParams(lp);
        return root;
    }

    abstract protected void initView(@Nullable Bundle savedInstanceState);

    abstract protected void initData(@Nullable Bundle savedInstanceState);

    @Override
    protected void onResume() {
        super.onResume();
        resumeView();
        resumeData();
    }

    abstract protected void resumeView();

    abstract protected void resumeData();

    public void setStatusColor(int color){
        setStatusColor(color,false);
    }

    public void setStatusColor(int color,boolean darkText){
        iv_status.setBackgroundColor(color);
        View decor = getWindow().getDecorView();
        if (darkText) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public void setStatusBitmap(Bitmap shape){
        iv_status.setImageBitmap(shape);
    }

    public void hideStatusBar(boolean darkText){
        iv_status.setVisibility(View.GONE);
        View decor = getWindow().getDecorView();
        if (darkText) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public void showStatusBar(){
        iv_status.setVisibility(View.VISIBLE);
    }
}

