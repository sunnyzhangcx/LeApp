package com.example.star.leapp.examineshow;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.star.leapp.examineresult.ExamineResultActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;
import static com.example.star.leapp.Application.LeappApplication.printTestItem;

//多选题界面
public class ExamineShowActivity2 extends AppCompatActivity {
    private Map<Integer,Integer> selectedMap = new HashMap<>();
    private int twoPos = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_show2);
        Intent intent = getIntent();
        final int pos = intent.getIntExtra("pos",0);
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        Data4Mooc.Test test = testList.get(pos);
        Button mBtnSubmit = findViewById(R.id.examine_submit);
        Button mBtnAbandon = findViewById(R.id.examine_abandon);
        FloatingActionButton mBtnTip = findViewById(R.id.examine_tip);
        final ListView mLvExamine = findViewById(R.id.examine2_lv);
        TextView mTvExamineContent = findViewById(R.id.examine_content2);
        mTvExamineContent.setText(printTestItem(test));

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

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ExamineShowActivity2.this,ExamineResultActivity.class);
                    Set<Integer> keySet = selectedMap.keySet();
                    ArrayList<Integer> multipleChoicePos = new ArrayList<>(keySet);
                    intent.putIntegerArrayListExtra("multiplechoicepos", multipleChoicePos);//用户给出的多选答案
                    intent.putExtra("testPos",pos);//题目索引
                    intent.putExtra("twoPos",twoPos);//代表多选题界面
                    startActivity(intent);

                    //Toast.makeText(ExamineShowActivity2.this,keySet.toString(), Toast.LENGTH_LONG).show();
            }
        });

        mBtnAbandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        return itemView.findViewById(R.id.cb_select_result);
    }



}
