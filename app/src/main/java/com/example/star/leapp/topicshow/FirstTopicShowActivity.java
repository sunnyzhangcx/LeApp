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
import com.example.star.leapp.examineshow.ExamineShowActivity1;
import com.example.star.leapp.topiccatalog.ThirdTopicActivity;

import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getFirstTopic;
import static com.example.star.leapp.Application.LeappApplication.getMoocDataList;
import static com.example.star.leapp.Application.LeappApplication.getSecondTopic;
import static com.example.star.leapp.Application.LeappApplication.getTNodeList;

public class FirstTopicShowActivity extends AppCompatActivity {

    int APos = 1;
    private PopupWindow mPopFeedback;
    private FloatingActionButton mBtnFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_topic_show);
        TextView mTvTopicName = findViewById(R.id.topic_name);
        RecyclerView mRvChildTopic = findViewById(R.id.child_topic);
        final TextView mTvSecondTopic = findViewById(R.id.second_topic_name);
        Button mBtExample = findViewById(R.id.topic_example);
        Button mBtExamine = findViewById(R.id.topic_examine);
        Button mBtnQuestion = findViewById(R.id.topic_question);
        mBtnFeedback = findViewById(R.id.topic_feedback);
        RecyclerView mRvContent = findViewById(R.id.topic_content);
        mRvChildTopic.setLayoutManager(new LinearLayoutManager(FirstTopicShowActivity.this));
        mRvContent.setLayoutManager(new LinearLayoutManager(FirstTopicShowActivity.this));
        mRvContent.addItemDecoration(new MyDecoration());


        final int groupPosition = getIntent().getIntExtra("groupPos",0);
        //System.out.print("-------------------------------------"+groupPosition);
        //更新案例库
        Data4Mooc.MoocData moocDataList = getMoocDataList();
        final List<Data4Mooc.TNode> firstTopicList = getFirstTopic(moocDataList);
        final List<Data4Mooc.TNode> secondTopicList = getSecondTopic(moocDataList).get(groupPosition);
        final Data4Mooc.Topic firstTopic = firstTopicList.get(groupPosition).getTopic();
        mRvContent.setAdapter(new Relv_Adapter_TopicShow_TopicSection(firstTopic,FirstTopicShowActivity.this));
        mTvTopicName.setText(firstTopic.getTitle());
        if(firstTopicList.get(groupPosition).getChildCount()!=0){
            mTvSecondTopic.setText("二级知识点：");
        }else {
            mTvSecondTopic.setVisibility(View.GONE);
        }

        mRvChildTopic.setAdapter(new Relv_Adapter_FirstTopicShow_FirstTopic(secondTopicList, FirstTopicShowActivity.this, new Relv_Adapter_FirstTopicShow_FirstTopic.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Data4Mooc.TNode clickItem = secondTopicList.get(pos);
                if(clickItem.getChildCount()!= 0 ){
                    Intent intent = new Intent(FirstTopicShowActivity.this,ThirdTopicActivity.class);
                    intent.putExtra("groupPosition", groupPosition);
                    intent.putExtra("childPosition", pos);
                    startActivity(intent);
                }else {
                    Toast toast = Toast.makeText(FirstTopicShowActivity.this,"此项无下级知识点~",Toast.LENGTH_SHORT);
                    toast.show();
                    //intent = new Intent(getActivity(),TopicShowActivity.class);
                }



            }
        }, new Relv_Adapter_FirstTopicShow_FirstTopic.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(FirstTopicShowActivity.this, TopicShowActivity.class);
                intent.putExtra("APos", APos);
                intent.putExtra("groupPosition", groupPosition);
                intent.putExtra("childPosition", pos);
                startActivity(intent);
            }
        }));


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
                        Toast.makeText(FirstTopicShowActivity.this,"click",Toast.LENGTH_SHORT).show();
                    }
                });
                mPopFeedback = new PopupWindow(view,180,ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopFeedback.setOutsideTouchable(true);
                mPopFeedback.setFocusable(true);
                mPopFeedback.showAsDropDown(mBtnFeedback,-100,-600);
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
