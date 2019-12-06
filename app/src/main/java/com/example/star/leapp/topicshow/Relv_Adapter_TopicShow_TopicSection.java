package com.example.star.leapp.topicshow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.star.leapp.R;

import data4mooc.Data4Mooc;

public class Relv_Adapter_TopicShow_TopicSection extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Data4Mooc.Topic cTopic;//当前三级知识点


    Relv_Adapter_TopicShow_TopicSection(Data4Mooc.Topic cTopic, Context context){
        this.mContext = context;
        this.cTopic = cTopic;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_topic_show_content_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
            ((LinearViewHolder) viewHolder).textview.setText(cTopic.getSections(i).getTitle());
            ((LinearViewHolder) viewHolder).contentListView.setLayoutManager(new LinearLayoutManager(mContext));
            ((LinearViewHolder) viewHolder).contentListView.setAdapter(new Relv_Adapter_TopicShow_TopicItem_Content(mContext,cTopic.getSections(i)));
            //setListViewHeightBasedOnChildren(((LinearViewHolder) viewHolder).contentListView);
            //((LinearViewHolder) viewHolder).contentListView.setFastScrollEnabled(false);
/*        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,"click..."+i,Toast.LENGTH_SHORT).show();
                mListener.onClick(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return cTopic.getSectionsCount();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;
        RecyclerView contentListView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.topic_section_name);
            contentListView = itemView.findViewById(R.id.section_content_lv);
        }
    }



    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
