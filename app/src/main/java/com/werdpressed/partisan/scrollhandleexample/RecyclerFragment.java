package com.werdpressed.partisan.scrollhandleexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerFragment extends Fragment {

    public static final String TAG = "RecyclerFragment";

    private View rootView;

    private ConfigurableRecyclerView mRecyclerView;
    private RecyclerFragmentAdapter mAdapter;

    private TextView mDragTextView;

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.rv_fragment, container, false);

        mRecyclerView = (ConfigurableRecyclerView) rootView.findViewById(R.id.rvf_recycler_view);
        mAdapter = new RecyclerFragmentAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDragTextView = (TextView) rootView.findViewById(R.id.rvf_drag_text_view);
        mDragTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView tv = (TextView) v;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tv.setBackgroundColor(Color.LTGRAY);
                        tv.setText(getString(R.string.drag_message_active));
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setBackgroundColor(0);
                        tv.setText(getString(R.string.drag_message_idle));
                        break;
                }
                return mRecyclerView.dispatchHandlerScroll(event);
            }
        });

        return rootView;
    }

    public ConfigurableRecyclerView getConfigurableRecyclerView() {
        return mRecyclerView;
    }
}
