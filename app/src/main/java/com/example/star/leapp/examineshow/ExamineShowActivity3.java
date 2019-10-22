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

//填空题界面implements View.OnClickListener, ReplaceSpan.OnClickListener
public class ExamineShowActivity3 extends AppCompatActivity implements View.OnClickListener{

/*    private String mTestStr = "3.我是个________学生,我有一个梦想，我要成为像____，____一样的人.";
    private TextView mTvContent;
    private EditText mEtInput;
    private SpansManager mSpansManager;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_show3);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos",0);
        //Data4Mooc.Test test = LeappApplication.getMoocDataList().getSetTest(pos);
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        Data4Mooc.Test test = testList.get(pos);
        Button mBtnSubmit = findViewById(R.id.examine_submit);
        Button mBtnAbandon = findViewById(R.id.examine_abandon);
        FloatingActionButton mBtnTip = findViewById(R.id.examine_tip);
        TextView mTvExamineContent = findViewById(R.id.examine_content3);
        mTvExamineContent.setText(test.getProblem());
        EditText mEtInput = findViewById(R.id.et_input);
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
/*        mTvContent = (TextView) findViewById(R.id.tv_content);
        mEtInput = (EditText) findViewById(R.id.et_input);
        mBtnSubmit = (Button) findViewById(R.id.examine_submit);
        mBtnSubmit.setOnClickListener(this);
        mSpansManager = new SpansManager(this,mTvContent,mEtInput);
        mSpansManager.doFillBlank(mTestStr);*/
        mBtnTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExamineShowActivity3.this,"click",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.examine_submit:
                //Toast.makeText(this, getMyAnswerStr(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, "SUBMIT", Toast.LENGTH_LONG).show();
                break;
            case R.id.examine_abandon:
/*                Intent intent = new Intent(ExamineShowActivity3.this,MainActivity.class);
                intent.putExtra("fragmentFlag",3);
                startActivity(intent);*/
                finish();
                break;
        }
    }
/*
    //填空题点击响应事件
    @Override
    public void OnClick(TextView v, int id, ReplaceSpan span) {
        mSpansManager.setData(mEtInput.getText().toString(),null, mSpansManager.mOldSpan);
        mSpansManager.mOldSpan = id;
        //如果当前span身上有值，先赋值给et身上
        mEtInput.setText(TextUtils.isEmpty(span.mText)?"":span.mText);
        mEtInput.setSelection(span.mText.length());
        span.mText = "";
        //通过rf计算出et当前应该显示的位置
        RectF rf = mSpansManager.drawSpanRect(span);
        //设置EditText填空题中的相对位置
        mSpansManager.setEtXY(rf);
        mSpansManager.setSpanChecked(id);
    }

    private String getMyAnswerStr(){
        mSpansManager.setLastCheckedSpanText(mEtInput.getText().toString());
        return mSpansManager.getMyAnswer().toString();
    }*/
}
