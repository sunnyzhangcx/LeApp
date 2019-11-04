package com.example.star.leapp.examineshow;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.leapp.R;
import com.example.star.leapp.examineresult.ExamineResultActivity;

import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;

//单选题界面
public class ExamineShowActivity1 extends AppCompatActivity{
    int onePos = 1;
    int curItemPos = -1;
    Data4Mooc.Test test = null;
    Button mBtnSubmit = null,mBtnAbandon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_show1);
        final int pos = getIntent().getIntExtra("pos",0);
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        test = testList.get(pos);
        mBtnSubmit = findViewById(R.id.examine_submit);
        mBtnAbandon = findViewById(R.id.examine_abandon);
        FloatingActionButton mBtnTip = findViewById(R.id.examine_tip);
        final ListView mLvExamine = findViewById(R.id.examine1_lv);
        TextView mTvExamineContent = findViewById(R.id.examine_content1);
        mTvExamineContent.setText(test.getTitle());

        mLvExamine.setAdapter(new Lv_Adapter_ExamineShow_SimpleChoiceTest(test,ExamineShowActivity1.this));
        mLvExamine.setItemsCanFocus(true);
        mLvExamine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(curItemPos!=-1){
                    getRadioBtn(curItemPos,mLvExamine).setChecked(false);
                }else{
                    mBtnSubmit.setEnabled(true);
                }
                getRadioBtn(position,mLvExamine).setChecked(true);
                curItemPos = position;
            }
        });
        mBtnTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExamineShowActivity1.this,"click",Toast.LENGTH_SHORT).show();
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamineShowActivity1.this,ExamineResultActivity.class);
                intent.putExtra("onePos",onePos);
                intent.putExtra("testPos",pos);
                intent.putExtra("resultPos",curItemPos);
                startActivity(intent);
            }
        });

        mBtnAbandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public RadioButton getRadioBtn(int pos,ListView listView){
        View itemView = listView.getChildAt(pos);
        return itemView.findViewById(R.id.rb_select_result);
    }


}
