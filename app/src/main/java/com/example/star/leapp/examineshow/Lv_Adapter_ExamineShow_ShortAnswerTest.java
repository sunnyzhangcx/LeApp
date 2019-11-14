package com.example.star.leapp.examineshow;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.star.leapp.R;

import java.util.HashMap;

import data4mooc.Data4Mooc;

public class Lv_Adapter_ExamineShow_ShortAnswerTest extends BaseAdapter {

    private LayoutInflater inflater;
    private Data4Mooc.Test test;

    private HashMap<Integer,String> contents = new HashMap<>();

    public Lv_Adapter_ExamineShow_ShortAnswerTest(Data4Mooc.Test test, Context context){
        this.inflater = LayoutInflater.from(context);
        this.test = test;
        init();
    }

    public HashMap<Integer, String> getContents() {
        return contents;
    }

    private void init(){
        contents.clear();
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
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_examine_test_result_shortanswer_item,null);
            holder.textView = convertView.findViewById(R.id.tv_alternative_content);
            holder.editText = convertView.findViewById(R.id.shortanswer_input_content);
            holder.editText.setTag(position);
            holder.editText.addTextChangedListener(new MyTextWatcher(holder,contents));
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            holder.editText.setTag(position);
            holder.editText.addTextChangedListener(new MyTextWatcher(holder,contents));
        }
        holder.editText.setText(null==contents.get(position)?"":contents.get(position));
        holder.textView.setText(test.getResults(position).getAltertive());
        return convertView;
    }

    class MyTextWatcher implements TextWatcher{
        private ViewHolder mHolder;
        HashMap<Integer, String> contents;

        public MyTextWatcher(ViewHolder holder1,HashMap<Integer,String> content){
            contents = content;
            mHolder = holder1;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s != null){
                contents.put((Integer) mHolder.editText.getTag(),s.toString());
            }
        }
    }


    public final class ViewHolder{
        TextView textView;
        EditText editText;
    }
}
