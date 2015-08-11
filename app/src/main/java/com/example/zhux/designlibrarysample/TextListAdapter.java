package com.example.zhux.designlibrarysample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextListAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;

    public TextListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(inflater.inflate(R.layout.item_text, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TextView textView = (TextView) viewHolder.itemView;
        textView.setText(String.format("Item %d", position + 1));
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
