package com.example.star.leapp.examineresult;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.star.leapp.R;

import java.util.List;

import data4mooc.Data4Mooc;

public class Relv_Adapter_ExamineResult_FBandSAStandardAnswer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    private List<Data4Mooc.Result> mResultList;
    private Data4Mooc.MoocData moocData;

    public Relv_Adapter_ExamineResult_FBandSAStandardAnswer(Data4Mooc.MoocData moocData, List<Data4Mooc.Result> resultList, Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mResultList = resultList;
        this.mListener = listener;
        this.moocData = moocData;
    }

    //创建viewholder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_examine_test_result_fbandsa_standardanswer_item,viewGroup,false));
    }

    //填充视图
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        ((LinearViewHolder) viewHolder).resultNumber.setText(mResultList.get(i).getAltertive());
        ((LinearViewHolder) viewHolder).textview.setText(mResultList.get(i).getResult());
        ((LinearViewHolder) viewHolder).rComment.setImageResource(R.drawable.frage);
        ((LinearViewHolder) viewHolder).rComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mListener.onClick(v,i);
            }
        });

    }

    //返回item个数
    @Override
    public int getItemCount() {
        if(mResultList!=null)
            return mResultList.size();
        return 0;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textview,resultNumber;
        private ImageView rComment;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.result_result);
            resultNumber = itemView.findViewById(R.id.result_number);
            rComment = itemView.findViewById(R.id.result_comment);
        }
    }

    public interface OnItemClickListener{
        void onClick(View v, int pos);
    }
}
