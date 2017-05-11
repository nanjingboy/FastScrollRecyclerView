package me.tom.fastscrollrecyclerview.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import me.tom.fastscrollrecyclerview.FastScrollRecyclerView;
import me.tom.fastscrollrecyclerview.FastScrollRecyclerViewAdapter;

public class DemoAdapter extends FastScrollRecyclerViewAdapter<DemoAdapter.DemoGroupHeaderViewHolder, DemoAdapter.DemoGroupItemViewHolder> {

    private LayoutInflater mInflater;

    private ArrayList<HashMap<String, Object>> mData;

    public DemoAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getGroupItemCount(int groupPosition) {
        return ((ArrayList<String>) mData.get(groupPosition).get("records")).size();
    }

    @Override
    public DemoGroupHeaderViewHolder onCreateGroupHeaderViewHolder(ViewGroup parent) {
        return new DemoGroupHeaderViewHolder(mInflater.inflate(R.layout.demo_group_header, parent, false));
    }

    @Override
    public DemoGroupItemViewHolder onCreateGroupItemViewHolder(ViewGroup parent) {
        return new DemoGroupItemViewHolder(mInflater.inflate(R.layout.demo_group_item, parent, false));
    }

    @Override
    public void onBindGroupHeaderViewHolder(DemoGroupHeaderViewHolder holder, int groupPosition) {
        holder.titleView.setText(mData.get(groupPosition).get("key").toString());
    }

    @Override
    public void onBindGroupItemViewHolder(DemoGroupItemViewHolder holder, int groupPosition, int groupItemPosition) {
        ArrayList<String> records = ((ArrayList<String>) mData.get(groupPosition).get("records"));
        holder.titleView.setText(records.get(groupItemPosition));
    }

    public static class DemoGroupHeaderViewHolder extends FastScrollRecyclerView.GroupHeaderViewHolder {

        public TextView titleView;

        public DemoGroupHeaderViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.titleView);
        }
    }

    public static class DemoGroupItemViewHolder extends FastScrollRecyclerView.GroupItemViewHolder {

        public TextView titleView;

        public DemoGroupItemViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.titleView);
        }
    }
}