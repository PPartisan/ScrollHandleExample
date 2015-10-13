package com.werdpressed.partisan.scrollhandleexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerFragmentAdapter extends RecyclerView.Adapter<RecyclerFragmentAdapter.ViewHolder> {

    private final String[] data;

    public RecyclerFragmentAdapter() {
        data = Cheeses.CHEESES;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(data[i]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.rvr_text);
        }
    }
}
