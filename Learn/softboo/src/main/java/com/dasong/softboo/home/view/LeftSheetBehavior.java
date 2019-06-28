package com.dasong.softboo.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class LeftSheetBehavior extends CoordinatorLayout.Behavior<ViewGroup> {

    public LeftSheetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ViewGroup child, @NonNull View dependency) {
        return dependency instanceof ViewGroup;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ViewGroup child, @NonNull View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }


}
