package com.example.zhux.designlibrarysample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CollapsingToolbarFragment extends Fragment {
    public static CollapsingToolbarFragment newInstance() {
        return new CollapsingToolbarFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collapsing_toolbar, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        collapsingToolbar.setContentScrimColor(Color.WHITE);
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        Context context = getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new TextListAdapter(context));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(
                        v, R.string.snackbar_message, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // do something
                    }
                });
                snackbar.show();
            }
        });
    }
}
