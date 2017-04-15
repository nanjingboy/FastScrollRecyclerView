package me.tom.fastscrollrecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class FastScrollRecyclerView extends RelativeLayout {

    private RecyclerView mRecyclerView;
    private TextView mDialogTextView;
    private FastScrollRecyclerViewSliderBar mSliderBar;
    private FastScrollRecyclerViewAdapter mAdapter;

    public FastScrollRecyclerView(Context context) {
        this(context, null);
    }

    public FastScrollRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastScrollRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.fast_scroll_recycler_view, this);
        mRecyclerView = (RecyclerView) getChildAt(0);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mSliderBar = (FastScrollRecyclerViewSliderBar) getChildAt(1);
        mSliderBar.setListener(new FastScrollRecyclerViewSliderBar.IIndexChangedListener() {
            @Override
            public void onIndexChanged(int position, String indexTitle) {
                if (indexTitle == null) {
                    mDialogTextView.setVisibility(GONE);
                } else {
                    mDialogTextView.setVisibility(VISIBLE);
                    mDialogTextView.setText(indexTitle);
                    ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(
                            mAdapter.groupHeaderAbsolutePosition(position), 0
                    );
                }
            }
        });
        mDialogTextView = (TextView) getChildAt(2);
    }

    public void setAdapter(FastScrollRecyclerViewAdapter adapter) {
        mAdapter = adapter;
        mRecyclerView.setAdapter(adapter);
    }

    public void setIndexTitles(List<String> titles) {
        mSliderBar.setIndexTitles(titles);
    }

    static abstract class DefaultViewHolder extends RecyclerView.ViewHolder {
        DefaultViewHolder(View view) {
            super(view);
        }
    }

    public static class GroupHeaderViewHolder extends DefaultViewHolder {
        public GroupHeaderViewHolder(View view) {
            super(view);
        }
    }

    public static class GroupItemViewHolder extends DefaultViewHolder {

        public GroupItemViewHolder(View view) {
            super(view);
        }
    }
}
