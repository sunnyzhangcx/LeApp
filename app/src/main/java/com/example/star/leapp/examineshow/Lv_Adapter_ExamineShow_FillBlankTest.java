package com.example.star.leapp.examineshow;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.star.leapp.R;

import java.util.HashMap;

import data4mooc.Data4Mooc;

public class Lv_Adapter_ExamineShow_FillBlankTest extends BaseAdapter {

    private LayoutInflater inflater;
    private Data4Mooc.Test test;
    private HashMap<Integer,String> contents = new HashMap<>();

    public Lv_Adapter_ExamineShow_FillBlankTest(Data4Mooc.Test test, Context context){
        this.inflater = LayoutInflater.from(context);
        this.test = test;
    }

    @Override
    public int getCount() {
        return test.getResultsCount();
    }

    @Override
    public Object getItem(int position) {
        return test.getResults(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*//加载布局为一个视图
        View view = inflater.inflate(R.layout.layout_examine_test_result_fillblank_item,null);

        //在view视图中查找组件
        TextView alternativeContent = view.findViewById(R.id.tv_alternative_content);
        EditText inputContent = view.findViewById(R.id.fillblank_input_content);
        //为Item里面的组件设置相应的数据
        alternativeContent.setText(test.getResults(position).getAltertive());*/

        ListViewViewHolder holder = null;

        if(convertView == null){
            holder = new ListViewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_examine_test_result_fillblank_item,parent,false));
            convertView = holder.itemView;
            convertView.setTag(holder);
            holder.editText.addTextChangedListener(new MyTextChangedListener(holder,contents));
            holder.textView.setText(test.getResults(position).getAltertive());
        }else {
            holder = (ListViewViewHolder) convertView.getTag();
        }

        holder.editText.setTag(position);

        if(!TextUtils.isEmpty(contents.get(position))){
            holder.editText.setText(contents.get(position));
        }else {
            holder.editText.getEditableText().clear();
        }

        return convertView;
    }

    public class MyTextChangedListener implements TextWatcher {

        ListViewViewHolder holder;
        HashMap<Integer, String> contents;

        MyTextChangedListener(ListViewViewHolder holder, HashMap<Integer, String> contents){
            this.holder = holder;
            this.contents = contents;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(holder != null && contents != null){
                int position = (int) holder.editText.getTag();
                contents.put(position,s.toString());
            }
        }

    }


    public static class ListViewViewHolder{
        public View itemView;
        public TextView textView;
        public EditText editText;

        public ListViewViewHolder(View v){
            itemView = v;
            textView = v.findViewById(R.id.tv_alternative_content);
            editText = v.findViewById(R.id.fillblank_input_content);
        }
    }
}
