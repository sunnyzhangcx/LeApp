package com.example.star.leapp.topiccatalog;

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

public class Relv_Adapter_Topic_ThirdTopic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    private List<Data4Mooc.TNode> CChild;//当前三级知识点列表

    public Relv_Adapter_Topic_ThirdTopic(List<Data4Mooc.TNode> CChild, Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.CChild = CChild;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_third_topic_linear_item,viewGroup,false));



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
            ((LinearViewHolder) viewHolder).textview.setText(CChild.get(i).getTopic().getTitle());

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
        return CChild.size();
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
