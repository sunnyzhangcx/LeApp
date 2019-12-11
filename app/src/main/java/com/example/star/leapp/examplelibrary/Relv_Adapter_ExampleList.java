package com.example.star.leapp.examplelibrary;

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

public class Relv_Adapter_ExampleList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    private List<Data4Mooc.GNode> gNodeList;

    public Relv_Adapter_ExampleList(List<Data4Mooc.GNode> gNodeList, Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.gNodeList = gNodeList;
    }

    //创建viewholder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_examplelibrary_example_list_linear_item,viewGroup,false));
    }

    //填充视图
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        ((LinearViewHolder) viewHolder).textview.setText(gNodeList.get(i).getExample().getTitle());
        ((LinearViewHolder) viewHolder).tv1.setText("位置1...");
        ((LinearViewHolder) viewHolder).tv2.setText("位置2...");
        ((LinearViewHolder) viewHolder).tv3.setText("位置3...");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(i);
            }
        });
    }

    //返回item个数
    @Override
    public int getItemCount() {
        if(gNodeList!=null)
            return gNodeList.size();
        return 0;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textview,tv1,tv2,tv3;
        private ImageView imageView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.iv_example);
            tv1 = itemView.findViewById(R.id.tv_1);
            tv2 = itemView.findViewById(R.id.tv_2);
            tv3 = itemView.findViewById(R.id.tv_3);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
