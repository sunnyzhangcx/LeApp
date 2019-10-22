package com.example.star.leapp.examineshow;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.leapp.Application.LeappApplication;
import com.example.star.leapp.MainActivity;
import com.example.star.leapp.R;

import java.util.List;

import data4mooc.Data4Mooc;
import selectwidget.ReplaceSpan;
import selectwidget.SpansManager;

import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;

//简答题界面
public class ExamineShowActivity4 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_show4);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos",0);
        //Data4Mooc.Test test = LeappApplication.getMoocDataList().getSetTest(pos);
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        Data4Mooc.Test test = testList.get(pos);
        Button mBtnSubmit = findViewById(R.id.examine_submit);
        Button mBtnAbandon = findViewById(R.id.examine_abandon);
        FloatingActionButton mBtnTip = findViewById(R.id.examine_tip);
        TextView mTvExamineContent = findViewById(R.id.examine_content4);
        mTvExamineContent.setText(test.getProblem());
        TextView mEtInput = findViewById(R.id.et_input);
        //设置EditText的显示方式为多行文本输入
        mEtInput.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //文本显示的位置在EditText的最上方
        mEtInput.setGravity(Gravity.TOP);
        //改变默认的单行模式
        mEtInput.setSingleLine(false);
        //水平滚动设置为False
        mEtInput.setHorizontallyScrolling(false);
        mBtnSubmit.setOnClickListener(this);
        mBtnAbandon.setOnClickListener(this);


        mBtnTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExamineShowActivity4.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.examine_submit:
                Toast.makeText(this,"submit", Toast.LENGTH_LONG).show();
                break;
            case R.id.examine_abandon:
/*                Intent intent = new Intent(ExamineShowActivity4.this,MainActivity.class);
                intent.putExtra("fragmentFlag",3);
                startActivity(intent);*/
                finish();
                break;
        }
    }
}
