package com.example.star.leapp.questionshow;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.star.leapp.R;
import com.example.star.leapp.topicshow.FirstTopicShowActivity;

import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getQAtopicList;
import static com.example.star.leapp.Application.LeappApplication.getQandAList;
import static com.example.star.leapp.Application.LeappApplication.getTNodeList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;

public class QuestionShowActivity extends AppCompatActivity {

    private PopupWindow mPopTopic;
    private Button mBtnTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_show);
        TextView mTvQuestionQ = findViewById(R.id.question_q);
        TextView mTvQuestionA = findViewById(R.id.question_a);
        mBtnTopic = findViewById(R.id.question_topic);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos",0);
        List<Data4Mooc.TNode> tNodeList = getTNodeList(moocDataList);//知识点总列表
        List<Data4Mooc.QandA> qandAList = getQandAList(moocDataList);//常见问题总列表
        final List<Integer> topicList = qandAList.get(pos).getTopicListList();//当前常见问题相关知识点索引int列表
        final List<Data4Mooc.TNode> AboutTopic = getQAtopicList(topicList,tNodeList);//相关知识点tnode列表
        Data4Mooc.QandA qandA = qandAList.get(pos);
        mTvQuestionQ.setText(qandA.getQuestion());
        mTvQuestionA.setText(qandA.getAnswer());



        mBtnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.layout_homepage_core_topic_list_linear_recycler_view,null);
                RecyclerView RvAboutTopic = view.findViewById(R.id.rv_main);
                RvAboutTopic.setLayoutManager(new LinearLayoutManager(QuestionShowActivity.this));
                RvAboutTopic.addItemDecoration(new MyDecoration());
                RvAboutTopic.setAdapter(new Relv_Adapter_QuestionShow_AboutTopic(AboutTopic, QuestionShowActivity.this, new Relv_Adapter_QuestionShow_AboutTopic.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) {
                        mPopTopic.dismiss();
                        int tNodepos = topicList.get(pos);//当前选择的知识点在tnodelist中的位置
                        Data4Mooc.TNode currentTopic = getTNodeList(moocDataList).get(tNodepos);
                        Intent intent = new Intent(QuestionShowActivity.this,FirstTopicShowActivity.class);
                        intent.putExtra("topic",currentTopic);
                        startActivity(intent);
                        //Toast.makeText(QuestionShowActivity.this,"click"+pos,Toast.LENGTH_SHORT).show();
                    }
                }));
                mPopTopic = new PopupWindow(view,mBtnTopic.getWidth(),ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopTopic.setOutsideTouchable(true);
                mPopTopic.setFocusable(true);
                mPopTopic.showAtLocation(view,Gravity.BOTTOM,0,0);
                //mPopTopic.showAsDropDown(mBtnTopic,200,-200);
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
