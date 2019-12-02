package com.demo.swiperefreshlayoutdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.swiperefreshlayoutdemo.R;
import com.demo.swiperefreshlayoutdemo.bean.Tag;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * FileName:   DemoAdapter
 * Author:     nizonglong
 * CreateTime: 2019/12/2 14:32
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
    private List<Tag> mTags;

    public DemoAdapter(List<Tag> tags) {
        mTags = tags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTagId.setText(mTags.get(position).getTagId());
        holder.tvTagName.setText(mTags.get(position).getTagName());
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTagId;
        TextView tvTagName;

        private ViewHolder(View view) {
            super(view);
            tvTagId = view.findViewById(R.id.tv_tagid);
            tvTagName = view.findViewById(R.id.tv_tagName);
        }
    }
}
