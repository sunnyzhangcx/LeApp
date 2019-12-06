package com.example.star.leapp.topicshow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.star.leapp.R;
import com.example.star.leapp.expandwidget.ExpandTextView;

import java.util.List;

import data4mooc.Data4Mooc;

public class Relv_Adapter_TopicShow_TopicItem_Content extends RecyclerView.Adapter<Relv_Adapter_TopicShow_TopicItem_Content.MyViewHolder> {

    private Data4Mooc.Section section;
    private Context mContext;
    private List<Data4Mooc.Item> contentList;

    public Relv_Adapter_TopicShow_TopicItem_Content(Context mContext, Data4Mooc.Section section) {
        this.section = section;
        this.mContext = mContext;
        contentList = section.getItemsList();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_topic_section_contentlv_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.contentTv.setText(contentList.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public ExpandTextView contentTv;

        public MyViewHolder(View view) {
            super(view);
            contentTv = view.findViewById(R.id.contentlv_item_tv);
        }
    }
}
