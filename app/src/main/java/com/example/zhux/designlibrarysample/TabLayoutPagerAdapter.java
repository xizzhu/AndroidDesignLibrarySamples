package com.example.zhux.designlibrarysample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabLayoutPagerAdapter extends PagerAdapter {
    private final LayoutInflater inflater;

    public TabLayoutPagerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = (TextView) inflater.inflate(R.layout.page_tab_layout, container, false);
        textView.setText(String.format("Item %d", position + 1));
        container.addView(textView);
        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.format("Page %d", position + 1);
    }
}
