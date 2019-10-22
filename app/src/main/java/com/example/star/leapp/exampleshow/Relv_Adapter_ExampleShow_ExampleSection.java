package com.example.star.leapp.exampleshow;

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

public class Relv_Adapter_ExampleShow_ExampleSection extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Data4Mooc.Example cExample;


    public Relv_Adapter_ExampleShow_ExampleSection(Data4Mooc.Example cExample, Context context){
        this.mContext = context;
        this.cExample = cExample;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_example_show_content_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
            ((LinearViewHolder) viewHolder).textview.setText(cExample.getSections(i).getTitle());
            ((LinearViewHolder) viewHolder).contentListView.setLayoutManager(new LinearLayoutManager(mContext));
            ((LinearViewHolder) viewHolder).contentListView.setAdapter(new Relv_Adapter_ExampleShow_ExampleItem_Content(mContext,cExample.getSections(i)));

    }

    @Override
    public int getItemCount() {
        return cExample.getSectionsCount();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;
        public RecyclerView contentListView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.example_section_name);
            contentListView = itemView.findViewById(R.id.section_content_lv);
        }
    }



    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
