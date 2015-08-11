package com.example.zhux.designlibrarysample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabLayoutFragment extends Fragment {
    public static TabLayoutFragment newInstance() {
        return new TabLayoutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabLayoutPagerAdapter(getActivity()));

        // as a work-around for: https://code.google.com/p/android/issues/detail?id=180462
        view.post(new Runnable() {
            @Override
            public void run() {
                ((TabLayout) view.findViewById(R.id.sliding_tabs)).setupWithViewPager(viewPager);
            }
        });
    }
}
