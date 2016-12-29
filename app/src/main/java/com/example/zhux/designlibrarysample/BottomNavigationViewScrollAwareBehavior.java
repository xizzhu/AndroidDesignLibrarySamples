package com.example.zhux.designlibrarysample;

import android.content.Context;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

public class BottomNavigationViewScrollAwareBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {
    private final Set<View> hiding = new HashSet<>();
    private final Set<View> showing = new HashSet<>();

    public BottomNavigationViewScrollAwareBehavior() {
    }

    public BottomNavigationViewScrollAwareBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
            BottomNavigationView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child,
            View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        if (dy > 0) {
            hide(child);
        } else if (dy < 0) {
            show(child);
        }
    }

    private void hide(BottomNavigationView bottomNavigationView) {
        if (hiding.contains(bottomNavigationView)) {
            return;
        }
        hiding.add(bottomNavigationView);

        ViewPropertyAnimatorCompat animator = ViewCompat.animate(bottomNavigationView);
        animator.cancel();
        animator.translationY(bottomNavigationView.getHeight()).setListener(new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(View view) {
                hiding.remove(view);
                ViewCompat.animate(view).setListener(null);
            }

            @Override
            public void onAnimationCancel(View view) {
                hiding.remove(view);
                ViewCompat.animate(view).setListener(null);
            }
        });
    }

    private void show(BottomNavigationView bottomNavigationView) {
        if (showing.contains(bottomNavigationView)) {
            return;
        }
        showing.add(bottomNavigationView);

        ViewPropertyAnimatorCompat animator = ViewCompat.animate(bottomNavigationView);
        animator.cancel();
        animator.translationY(0.0F).setListener(new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(View view) {
                showing.remove(view);
                ViewCompat.animate(view).setListener(null);
            }

            @Override
            public void onAnimationCancel(View view) {
                showing.remove(view);
                ViewCompat.animate(view).setListener(null);
            }
        });
    }
}
