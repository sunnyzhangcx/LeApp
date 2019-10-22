package com.example.star.leapp.examinelibrary;

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

public class Relv_Adapter_ExamineList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    private Data4Mooc.MoocData moocDataList;
    private List<Data4Mooc.Test> testList;

    public Relv_Adapter_ExamineList(Data4Mooc.MoocData moocDataList, Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.moocDataList = moocDataList;
    }

    public Relv_Adapter_ExamineList(List<Data4Mooc.Test> testList, Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.testList = testList;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_examinelibrary_examine_list_linear_item,viewGroup,false));
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
            ((LinearViewHolder) viewHolder).textview.setText(testList.get(i).getTitle());
//(LinearViewHolder) viewHolder).textview.setText(moocDataList.getSetTest(i).getTitle()
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
/*        if(null==moocDataList)
            return 0;
        return moocDataList.getSetTestCount();*/
        if(null==testList)
            return 0;
        return testList.size();
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
