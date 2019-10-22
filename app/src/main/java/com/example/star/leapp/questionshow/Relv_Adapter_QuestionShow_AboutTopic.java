package com.example.star.leapp.questionshow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.star.leapp.R;

import java.util.List;

import data4mooc.Data4Mooc;

public class Relv_Adapter_QuestionShow_AboutTopic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    private List<Data4Mooc.TNode> AboutTopicList;

    public Relv_Adapter_QuestionShow_AboutTopic(List<Data4Mooc.TNode> AboutTopicList, Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.AboutTopicList = AboutTopicList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_homepage_core_topic_list_linear_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        ((LinearViewHolder) viewHolder).textview.setText(AboutTopicList.get(i).getTopic().getTitle());

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
        return AboutTopicList.size();
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
