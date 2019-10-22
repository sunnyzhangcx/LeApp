package com.example.star.leapp.homepage.progress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.star.leapp.R;

import java.util.List;

public class Relv_Adapter_ProgressExampleList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mList;

    public Relv_Adapter_ProgressExampleList(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_homepage_progress_example_list_linear_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((LinearViewHolder) viewHolder).textview.setText(mList.get(i));
        ((LinearViewHolder) viewHolder).progressBar.setProgress(60);

    }


    @Override
    public int getItemCount() {
        if(this.mList!=null)
            return this.mList.size();
        return 0;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;
        private ProgressBar progressBar;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.tv_title);
            progressBar = itemView.findViewById(R.id.progress_example);
        }
    }


}
