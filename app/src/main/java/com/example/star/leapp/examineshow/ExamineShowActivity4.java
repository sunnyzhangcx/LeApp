package com.example.star.leapp.examineshow;

import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.leapp.R;
import com.example.star.leapp.examineresult.ExamineResultActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;
import static com.example.star.leapp.Application.LeappApplication.printTestItem;



//简答题界面
public class ExamineShowActivity4 extends AppCompatActivity {

    private int fourPos = 4;
    Lv_Adapter_ExamineShow_ShortAnswerTest adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_show4);
        final int pos = getIntent().getIntExtra("pos",0);
        //Data4Mooc.Test test = LeappApplication.getMoocDataList().getSetTest(pos);
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        Data4Mooc.Test test = testList.get(pos);
        Button mBtnSubmit = findViewById(R.id.examine_submit);
        Button mBtnAbandon = findViewById(R.id.examine_abandon);
        final ListView mLvExamineInput= findViewById(R.id.examine4_lv);
        FloatingActionButton mBtnTip = findViewById(R.id.examine_tip);
        TextView mTvExamineContent = findViewById(R.id.examine_content4);
        mTvExamineContent.setText(printTestItem(test));
        adapter = new Lv_Adapter_ExamineShow_ShortAnswerTest(test,ExamineShowActivity4.this);
        mLvExamineInput.setAdapter(adapter);
        //点击事件先交给子控件（Edittext）处理,子控件处理不了再由Item自己处理
        mLvExamineInput.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamineShowActivity4.this,ExamineResultActivity.class);
                ArrayList<String> ansList = mapToList(adapter.getContents());
                intent.putStringArrayListExtra("shortanswer",ansList);
                intent.putExtra("testPos",pos);
                intent.putExtra("fourPos",fourPos);
                startActivity(intent);
                //Toast.makeText(ExamineShowActivity4.this,"submit", Toast.LENGTH_LONG).show();
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
                Toast.makeText(ExamineShowActivity4.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> mapToList(HashMap<Integer,String> contents) {
        ArrayList<String> contentList = new ArrayList<>();
        for(Integer index:contents.keySet()){
            contentList.add(index,contents.get(index));
        }
        return contentList;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {  //把操作放在用户点击的时候
            View v = getCurrentFocus();      //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, ev)) { //判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());   //收起键盘
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {  //判断得到的焦点控件是否包含EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }

}
