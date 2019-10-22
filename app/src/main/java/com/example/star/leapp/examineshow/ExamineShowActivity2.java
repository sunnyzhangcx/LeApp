package com.example.star.leapp.examineshow;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.leapp.Application.LeappApplication;
import com.example.star.leapp.MainActivity;
import com.example.star.leapp.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data4mooc.Data4Mooc;
import selectwidget.ReplaceSpan;
import selectwidget.SpansManager;

import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;

//多选题界面
public class ExamineShowActivity2 extends AppCompatActivity implements View.OnClickListener{
    Map<Integer,Integer> selectedMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_show2);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos",0);
        //Data4Mooc.Test test = LeappApplication.getMoocDataList().getSetTest(pos);
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        Data4Mooc.Test test = testList.get(pos);
        Button mBtnSubmit = findViewById(R.id.examine_submit);
        Button mBtnAbandon = findViewById(R.id.examine_abandon);
        FloatingActionButton mBtnTip = findViewById(R.id.examine_tip);
        final ListView mLvExamine = findViewById(R.id.examine2_lv);
        TextView mTvExamineContent = findViewById(R.id.examine_content2);
        mTvExamineContent.setText(test.getProblem());
/*        CheckBox mCheckBoxA = findViewById(R.id.cb_1);
        CheckBox mCheckBoxB = findViewById(R.id.cb_2);
        CheckBox mCheckBoxC = findViewById(R.id.cb_3);
        CheckBox mCheckBoxD = findViewById(R.id.cb_4);
        CheckBox mCheckBoxE = findViewById(R.id.cb_5);
        mCheckBoxA.setText(test.getResults(0).getAltertive());
        mCheckBoxB.setText(test.getResults(1).getAltertive());
        mCheckBoxC.setText(test.getResults(2).getAltertive());
        mCheckBoxD.setText(test.getResults(3).getAltertive());
        mCheckBoxE.setText(test.getResults(4).getAltertive());*/
        mBtnSubmit.setOnClickListener(this);
        mBtnAbandon.setOnClickListener(this);
        mLvExamine.setItemsCanFocus(true);
        mLvExamine.setAdapter(new Lv_Adapter_ExamineShow_MultipleChoiceTest(test,ExamineShowActivity2.this));
        mLvExamine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selectedMap.containsKey(position)){
                    getCheckBox(position,mLvExamine).setChecked(false);
                    selectedMap.remove(position);
                }else{
                    getCheckBox(position,mLvExamine).setChecked(true);
                    selectedMap.put(position,1);
                }
            }
        });
        mBtnTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExamineShowActivity2.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public CheckBox getCheckBox(int pos, ListView listView){
        View itemView = listView.getChildAt(pos);
        CheckBox checkBoxBtn = itemView.findViewById(R.id.cb_select_result);
        return checkBoxBtn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.examine_submit:
                if(selectedMap.size()==0){
                    Toast.makeText(this, "no item selected", Toast.LENGTH_LONG).show();
                }else{
                    Set keySet = selectedMap.keySet();
                    Toast.makeText(this, keySet.toString(), Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.examine_abandon:
/*                Intent intent = new Intent(ExamineShowActivity2.this,MainActivity.class);
                intent.putExtra("fragmentFlag",3);
                startActivity(intent);*/
                finish();
                break;
        }
    }


}
