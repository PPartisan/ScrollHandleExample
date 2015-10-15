package com.werdpressed.partisan.scrollhandleexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerFragment extends Fragment {

    public static final String TAG = "RecyclerFragment";

    private View rootView;

    private ConfigurableRecyclerView mRecyclerView;
    private RecyclerFragmentAdapter mAdapter;
    private RecyclerLayoutManager mLayoutManager;

    private int maxAdapterPosition;

    private TextView mDragTextView;
    private EditText mScrollToEntry;
    private Button mScrollToBtn;

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.rv_fragment, container, false);

        mRecyclerView = (ConfigurableRecyclerView) rootView.findViewById(R.id.rvf_recycler_view);
        mAdapter = new RecyclerFragmentAdapter();
        mLayoutManager = new RecyclerLayoutManager(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager.setAppBarManager((AppBarManager) getActivity());

        maxAdapterPosition = mAdapter.getItemCount() - 1;

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

        String scrollToEntryHintText = getString(R.string.rvf_scroll_to_value_et, maxAdapterPosition);
        mScrollToEntry = (EditText) rootView.findViewById(R.id.rvf_et);
        mScrollToEntry.setHint(scrollToEntryHintText);
        mScrollToEntry.requestFocus();
        mScrollToEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    mScrollToBtn.performClick();
                }
                return false;
            }
        });

        mScrollToBtn = (Button) rootView.findViewById(R.id.rvf_scroll_to_btn);
        mScrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int target;

                try {
                    target = Integer.valueOf(mScrollToEntry.getText().toString());
                } catch (NumberFormatException e) {
                    makeToast("Entry must be of type int");
                    return;
                }

                if ((target < 0) || (target > mAdapter.getItemCount() - 1)) {
                    makeToast("Entry must range from 0 to " + maxAdapterPosition);
                    return;
                }

                mRecyclerView.smoothScrollToPosition(target);
            }
        });

        return rootView;
    }

    public ConfigurableRecyclerView getConfigurableRecyclerView() {
        return mRecyclerView;
    }

    private void makeToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }
}
