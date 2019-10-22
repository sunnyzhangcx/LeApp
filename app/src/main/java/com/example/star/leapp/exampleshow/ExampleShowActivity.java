package com.example.star.leapp.exampleshow;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.leapp.R;
import com.example.star.leapp.expandwidget.ExpandTextView;

import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getGNodeList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;

public class ExampleShowActivity extends AppCompatActivity {

    private PopupWindow mPopFeedback;
    private FloatingActionButton mBtnFeedback;
    private ExpandTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_show);
        TextView mTvExampleName = findViewById(R.id.example_name);
        mBtnFeedback = findViewById(R.id.example_fab);
        Button mBtExample = findViewById(R.id.example_example);
        Button mBtTopic = findViewById(R.id.example_topic);
        RecyclerView mRvContent = findViewById(R.id.example_content);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos",0);
        List<Data4Mooc.GNode> gNodeList = getGNodeList(moocDataList);//案例总列表
        Data4Mooc.Example cExample = gNodeList.get(pos).getExample();

        mTvExampleName.setText(cExample.getTitle());
        //案例展示列表，设置适配器前要加布局管理器，否则不会执行适配器内部适配代码
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.setAdapter(new Relv_Adapter_ExampleShow_ExampleSection(cExample,ExampleShowActivity.this));

        mBtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.layout_example_show_popup_feedback,null);
                TextView tv1 = view.findViewById(R.id.tv_a);
                TextView tv2 = view.findViewById(R.id.tv_b);
                TextView tv3 = view.findViewById(R.id.tv_c);
                TextView tv4 = view.findViewById(R.id.tv_d);
                TextView tv5 = view.findViewById(R.id.tv_e);
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopFeedback.dismiss();
                        //do something...
                        Toast.makeText(ExampleShowActivity.this,"click",Toast.LENGTH_SHORT).show();
                    }
                });
                mPopFeedback = new PopupWindow(view,180,ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopFeedback.setOutsideTouchable(true);
                mPopFeedback.setFocusable(true);
                mPopFeedback.showAsDropDown(mBtnFeedback,-100,-600);
            }
        });
    }


}
