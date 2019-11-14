package com.example.star.leapp.topicshow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.star.leapp.R;
import com.example.star.leapp.examinelibrary.Relv_Adapter_ExamineList;

import java.util.List;

import data4mooc.Data4Mooc;

public class Relv_Adapter_FirstTopicShow_FirstTopic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Data4Mooc.TNode> SecondTopicList;
    private OnItemClickListener mListener;

    public Relv_Adapter_FirstTopicShow_FirstTopic(List<Data4Mooc.TNode> SecondTopicList, Context context, OnItemClickListener listener){
        this.mContext = context;
        this.SecondTopicList = SecondTopicList;
        this.mListener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_firsttopicshow_topic_list_linear_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        ((LinearViewHolder) viewHolder).textview.setText(SecondTopicList.get(i).getTopic().getTitle());



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,"click..."+i,Toast.LENGTH_SHORT).show();
                mListener.onClick(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        return SecondTopicList.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.tv_title);

        }
    }


    public interface OnItemClickListener{
        void onClick(int pos);
    }

}
