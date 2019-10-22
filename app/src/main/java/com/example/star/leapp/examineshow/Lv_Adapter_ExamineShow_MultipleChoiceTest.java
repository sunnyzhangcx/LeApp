package com.example.star.leapp.examineshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.star.leapp.R;

import data4mooc.Data4Mooc;

public class Lv_Adapter_ExamineShow_MultipleChoiceTest extends BaseAdapter {

    private LayoutInflater inflater;
    Data4Mooc.Test test;

    public Lv_Adapter_ExamineShow_MultipleChoiceTest(Data4Mooc.Test test, Context context){
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
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.layout_examine_test_result_multiplechoice_item,null);

        //在view视图中查找组件
        CheckBox testResult = view.findViewById(R.id.cb_select_result);

        //为Item里面的组件设置相应的数据
        testResult.setText(test.getResults(position).getAltertive());
        return view;
    }
}
