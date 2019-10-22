package com.example.star.leapp.topicshow;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
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
import com.example.star.leapp.exampleshow.ExampleShowActivity;

import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getMoocDataList;
import static com.example.star.leapp.Application.LeappApplication.getSecondTopic;
import static com.example.star.leapp.Application.LeappApplication.getTNodeList;
import static com.example.star.leapp.Application.LeappApplication.getThirdTopic;

public class TopicShowActivity extends AppCompatActivity {

    private PopupWindow mPopFeedback;
    private FloatingActionButton mBtnFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_show);
        TextView mTvTopicName = findViewById(R.id.topic_name);
        Button mBtExample = findViewById(R.id.topic_example);
        Button mBtExamine = findViewById(R.id.topic_examine);
        Button mBtnQuestion = findViewById(R.id.topic_question);
        mBtnFeedback = findViewById(R.id.topic_feedback);
        RecyclerView mRvContent = findViewById(R.id.topic_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(TopicShowActivity.this));
        mRvContent.addItemDecoration(new MyDecoration());

        Intent intent = getIntent();
        final int groupPosition = intent.getIntExtra("groupPosition",0);
        final int childPosition = intent.getIntExtra("childPosition",0);
        final int thirdPos = intent.getIntExtra("ThirdPos",0);
        final int BPos = intent.getIntExtra("BPos",0);
        final int CPos = intent.getIntExtra("CPos",0);
        final int tNodepos = intent.getIntExtra("tNodepos",0);
        //更新案例库
        Data4Mooc.MoocData moocDataList = getMoocDataList();
        final List<Data4Mooc.TNode> tNodeList = getTNodeList(moocDataList);
        final List<List<Data4Mooc.TNode>> secondTopicList = getSecondTopic(moocDataList);
        final List<Data4Mooc.TNode> thirdTopicList = getThirdTopic(moocDataList,groupPosition,childPosition);


        if(BPos == 1){
            final Data4Mooc.Topic secondTopic = secondTopicList.get(groupPosition).get(childPosition).getTopic();
            mRvContent.setAdapter(new Relv_Adapter_TopicShow_TopicSection(secondTopic,TopicShowActivity.this));
            mTvTopicName.setText(secondTopic.getTitle());

        }else if(CPos == 1){
            final Data4Mooc.Topic thirdTopic  = thirdTopicList.get(thirdPos).getTopic();
            mRvContent.setAdapter(new Relv_Adapter_TopicShow_TopicSection(thirdTopic,TopicShowActivity.this));
            mTvTopicName.setText(thirdTopic.getTitle());
        }else {
            final Data4Mooc.Topic QAAboutTopic = tNodeList.get(tNodepos).getTopic();
            mRvContent.setAdapter(new Relv_Adapter_TopicShow_TopicSection(QAAboutTopic,TopicShowActivity.this));
            mTvTopicName.setText(QAAboutTopic.getTitle());
        }
        mBtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.layout_topic_show_popup_feedback,null);
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
                        Toast.makeText(TopicShowActivity.this,"click",Toast.LENGTH_SHORT).show();
                    }
                });
                mPopFeedback = new PopupWindow(view,180,ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopFeedback.setOutsideTouchable(true);
                mPopFeedback.setFocusable(true);
                mPopFeedback.showAsDropDown(mBtnFeedback,-100,-600);
            }
        });
        mBtExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopicShowActivity.this,ExampleShowActivity.class);
                startActivity(intent);
            }
        });

    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divideHeight));
        }
    }
}
