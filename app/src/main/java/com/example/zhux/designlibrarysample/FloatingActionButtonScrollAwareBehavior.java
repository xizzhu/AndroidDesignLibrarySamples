package com.example.zhux.designlibrarysample;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

public class FloatingActionButtonScrollAwareBehavior extends FloatingActionButton.Behavior {
    private final Set<View> hidingFABs = new HashSet<>();

    public FloatingActionButtonScrollAwareBehavior() {
    }

    public FloatingActionButtonScrollAwareBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
            FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
            View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        if (dy > 0) {
            hide(child);
        } else if (dy < 0) {
            show(child);
        }
    }

    private void hide(FloatingActionButton fab) {
        if (hidingFABs.contains(fab)) {
            return;
        }
        hidingFABs.add(fab);

        ViewPropertyAnimatorCompat animator = ViewCompat.animate(fab);
        animator.cancel();
        animator.scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setListener(new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(View view) {
                hidingFABs.remove(view);
                view.setVisibility(View.INVISIBLE);
                ViewCompat.animate(view).setListener(null);
            }

            @Override
            public void onAnimationCancel(View view) {
                hidingFABs.remove(view);
                ViewCompat.animate(view).setListener(null);
            }
        });
    }

    private void show(FloatingActionButton fab) {
        if (fab.getVisibility() == View.VISIBLE) {
            return;
        }
        fab.setVisibility(View.VISIBLE);

        ViewPropertyAnimatorCompat animator = ViewCompat.animate(fab);
        animator.cancel();
        animator.scaleX(1.0F).scaleY(1.0F).alpha(1.0F);
    }
}
