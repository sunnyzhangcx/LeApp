package com.example.star.leapp.examineresult;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.PopupWindow;

import com.example.star.leapp.R;
import com.example.star.leapp.topicshow.TopicShowActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.equalsFillBlankOrShortAnswer;
import static com.example.star.leapp.Application.LeappApplication.equalsMultipleChoice;
import static com.example.star.leapp.Application.LeappApplication.getFillBlankCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getMultipleChoiceCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getSimpleChioceCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getTNode;
import static com.example.star.leapp.Application.LeappApplication.getTNodeList;
import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;
import static com.example.star.leapp.Application.LeappApplication.pirntComment;
import static com.example.star.leapp.Application.LeappApplication.printFillBlankResult;
import static com.example.star.leapp.Application.LeappApplication.printMultipleChoiceAlternative;
import static com.example.star.leapp.Application.LeappApplication.printShortAnswerResult;

public class ExamineResultActivity extends AppCompatActivity {

    PopupWindow mPop;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_result);
        TextView mTvJudge = findViewById(R.id.examine_result_judge);
        TextView mTvUserAnswerName =findViewById(R.id.examine_result_user_answer_name);
        TextView mTvUserAnswer =findViewById(R.id.examine_result_user_answer);
        TextView mTvStandardAnswerName =findViewById(R.id.examine_result_standard_answer_name);
        RecyclerView mRvStandardAnswer = findViewById(R.id.examine_result_standard_answer_lv);




        mRvStandardAnswer.setLayoutManager(new LinearLayoutManager(ExamineResultActivity.this));

        final int onePos = getIntent().getIntExtra("onePos",0);
        final int twoPos = getIntent().getIntExtra("twoPos",0);
        final int threePos = getIntent().getIntExtra("threePos",0);
        final int fourPos = getIntent().getIntExtra("fourPos",0);
        final int testPos = getIntent().getIntExtra("testPos",0);
        final int simpleChoiceResultPos = getIntent().getIntExtra("resultPos",0);
        final ArrayList<Integer> multipleChoiceResult = getIntent().getIntegerArrayListExtra("multiplechoicepos");
        final ArrayList<String> fillBlankResultUser = getIntent().getStringArrayListExtra("fillblank");
        final ArrayList<String> shortAnswerResultUser = getIntent().getStringArrayListExtra("shortanswer");

        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        final Data4Mooc.Test test =testList.get(testPos);

        if(onePos == 1){//单选题
            if(simpleChoiceResultPos == -1){
                mTvJudge.setText("您没有进行选择！\n");
                mTvUserAnswer.setText("您的答案：您没有进行选择！\n");
            }else{
                if(simpleChoiceResultPos == getSimpleChioceCurrentResult(test)){
                    mTvJudge.setText("恭喜你答对了！\n");
                    mTvUserAnswer.setText(test.getResults(simpleChoiceResultPos).getAltertive() + "\n");
                }else{
                    mTvJudge.setText("答案错误\n");
                    mTvUserAnswer.setText(test.getResults(simpleChoiceResultPos).getAltertive() + "\n");
                }
/*                if(test.getResults(getSimpleChioceCurrentResult(test)).getAltertive() == null){
                    mTvStandardAnswer.setText("没有内容！");
                }else{
                    //这是标准答案输出的地方，要改
                    //mRvStandardAnswer.setAdapter(new Relv_Adapter_ExamineResult_ChoiceStandardAnswer(test.getResultsList(),ExamineResultActivity.this));
                    //mTvStandardAnswer.setText(test.getResults(getSimpleChioceCurrentResult(test)).getAltertive() + "\n");
                }*/
                //标准答案的显示，包括候选项、是否选择、解释、知识点
                mRvStandardAnswer.setAdapter(new Relv_Adapter_ExamineResult_ChoiceStandardAnswer(moocDataList,test.getResultsList(), ExamineResultActivity.this, new Relv_Adapter_ExamineResult_ChoiceStandardAnswer.OnItemClickListener() {
                    @Override
                    public void onClick(View v, final int pos) {
                        View vPupupWindow = getLayoutInflater().inflate(R.layout.layout_examine_test_result_choice_standardanswer_explain_item,null);
                        TextView explain = vPupupWindow.findViewById(R.id.result_explain);
                        TextView topic = vPupupWindow.findViewById(R.id.result_topic1);
                        TextView topicName = vPupupWindow.findViewById(R.id.examine_result_topic_name);
                        explain.setText(test.getResults(pos).getComment());
                        if(test.getResults(pos).getTopic() != -1){
                            topic.setText(getTNodeList(moocDataList).get(test.getResults(pos).getTopic()).getTopic().getTitle());
                            topic.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //找到相对应的知识点展示界面
                                    Data4Mooc.TNode CtNode = getTNode(moocDataList,test.getResults(pos).getTopic());
                                    Intent intent = new Intent(ExamineResultActivity.this,TopicShowActivity.class);
                                    intent.putExtra("topic",CtNode);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            topic.setVisibility(View.INVISIBLE);
                            topicName.setVisibility(View.INVISIBLE);
                        }
                        mPop = new PopupWindow(vPupupWindow,ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);
                        mPop.setOutsideTouchable(true);
                        mPop.setFocusable(true);
                        mPop.showAtLocation(vPupupWindow,Gravity.CENTER,0,0);

                    }
                }));
                //mTvResultExplain.setText(pirntComment(test));
            }

        }else if(twoPos == 2){//多选题
            Set<Integer> multipleChoiceResultPos = new HashSet<>(multipleChoiceResult);
            if(equalsMultipleChoice(multipleChoiceResultPos, getMultipleChoiceCurrentResult(test))){
                mTvJudge.setText("恭喜您的多选题答对了！\n");
                mTvUserAnswer.setText(printMultipleChoiceAlternative(multipleChoiceResult,test));
            }else if(multipleChoiceResultPos.size() == 0){
                mTvJudge.setText("您的多选题没有进行选择！\n");
                mTvUserAnswer.setText("您的答案：您没有进行选择！\n");
            }else {
                mTvJudge.setText("答案错误\n");
                mTvUserAnswer.setText(printMultipleChoiceAlternative(multipleChoiceResult,test));
            }
            mRvStandardAnswer.setAdapter(new Relv_Adapter_ExamineResult_ChoiceStandardAnswer(moocDataList,test.getResultsList(), ExamineResultActivity.this, new Relv_Adapter_ExamineResult_ChoiceStandardAnswer.OnItemClickListener() {
                @Override
                public void onClick(View v, final int pos) {
                    View vPupupWindow = getLayoutInflater().inflate(R.layout.layout_examine_test_result_choice_standardanswer_explain_item,null);
                    TextView explain = vPupupWindow.findViewById(R.id.result_explain);
                    TextView topic = vPupupWindow.findViewById(R.id.result_topic1);
                    TextView topicName = vPupupWindow.findViewById(R.id.examine_result_topic_name);
                    explain.setText(test.getResults(pos).getComment());
                    if(test.getResults(pos).getTopic() != -1){
                        topic.setText(getTNodeList(moocDataList).get(test.getResults(pos).getTopic()).getTopic().getTitle());
                        topic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //找到相对应的知识点展示界面
                                Data4Mooc.TNode CtNode = getTNode(moocDataList,test.getResults(pos).getTopic());
                                Intent intent = new Intent(ExamineResultActivity.this,TopicShowActivity.class);
                                intent.putExtra("topic",CtNode);
                                startActivity(intent);
                            }
                        });
                    }else {
                        topic.setVisibility(View.INVISIBLE);
                        topicName.setVisibility(View.INVISIBLE);
                    }
                    mPop = new PopupWindow(vPupupWindow,ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);
                    mPop.setOutsideTouchable(true);
                    mPop.setFocusable(true);
                    mPop.showAtLocation(vPupupWindow,Gravity.CENTER,0,0);

                }
            }));
            //mTvStandardAnswer.setText(printMultipleChoiceAlternative(new ArrayList<Integer>(getMultipleChoiceCurrentResult(test)),test));
            //mTvResultExplain.setText(pirntComment(test));

        }else if(threePos == 3){//填空题
            //ArrayList<String> result = getFillBlankCurrentResult(test);
            if(equalsFillBlankOrShortAnswer(getFillBlankCurrentResult(test),fillBlankResultUser)){
                mTvJudge.setText("恭喜您的填空题全部答对！\n");
            } else {
                mTvJudge.setText("很遗憾，您的填空题答案有误。\n");
            }

            mTvUserAnswer.setText(printFillBlankResult(fillBlankResultUser));
            mRvStandardAnswer.setAdapter(new Relv_Adapter_ExamineResult_FBandSAStandardAnswer(moocDataList, test.getResultsList(), ExamineResultActivity.this, new Relv_Adapter_ExamineResult_FBandSAStandardAnswer.OnItemClickListener() {
                @Override
                public void onClick(View v, final int pos) {
                    View vPupupWindow = getLayoutInflater().inflate(R.layout.layout_examine_test_result_fbandsa_standardanswer_explain_item,null);
                    TextView explain = vPupupWindow.findViewById(R.id.result_explain);
                    TextView topic = vPupupWindow.findViewById(R.id.result_topic1);
                    TextView topicName = vPupupWindow.findViewById(R.id.examine_result_topic_name);
                    explain.setText(test.getResults(pos).getComment());
                    if(test.getResults(pos).getTopic() != -1){
                        topic.setText(getTNodeList(moocDataList).get(test.getResults(pos).getTopic()).getTopic().getTitle());
                        topic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //找到相对应的知识点展示界面
                                Data4Mooc.TNode CtNode = getTNode(moocDataList,test.getResults(pos).getTopic());
                                Intent intent = new Intent(ExamineResultActivity.this,TopicShowActivity.class);
                                intent.putExtra("topic",CtNode);
                                startActivity(intent);
                            }
                        });
                    }else {
                        topic.setVisibility(View.INVISIBLE);
                        topicName.setVisibility(View.INVISIBLE);
                    }
                    mPop = new PopupWindow(vPupupWindow,ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);
                    mPop.setOutsideTouchable(true);
                    mPop.setFocusable(true);
                    mPop.showAtLocation(vPupupWindow,Gravity.CENTER,0,0);
                }
            }));
            //mTvStandardAnswer.setText(printFillBlankResult(test));
            //mTvResultExplain.setText(pirntComment(test));


        }else if(fourPos == 4){///简答题
            if(equalsFillBlankOrShortAnswer(getFillBlankCurrentResult(test),shortAnswerResultUser)){
                mTvJudge.setText("恭喜您的简答题全部答对！\n");
            }else {
                mTvJudge.setText("很遗憾，您的简答题答案有误。\n");
            }

            mTvUserAnswer.setText(printFillBlankResult(shortAnswerResultUser));
            mRvStandardAnswer.setAdapter(new Relv_Adapter_ExamineResult_FBandSAStandardAnswer(moocDataList, test.getResultsList(), ExamineResultActivity.this, new Relv_Adapter_ExamineResult_FBandSAStandardAnswer.OnItemClickListener() {
                @Override
                public void onClick(View v, final int pos) {
                    View vPupupWindow = getLayoutInflater().inflate(R.layout.layout_examine_test_result_fbandsa_standardanswer_explain_item,null);
                    TextView explain = vPupupWindow.findViewById(R.id.result_explain);
                    TextView topic = vPupupWindow.findViewById(R.id.result_topic1);
                    TextView topicName = vPupupWindow.findViewById(R.id.examine_result_topic_name);
                    explain.setText(test.getResults(pos).getComment());
                    if(test.getResults(pos).getTopic() != -1){
                        topic.setText(getTNodeList(moocDataList).get(test.getResults(pos).getTopic()).getTopic().getTitle());
                        topic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //找到相对应的知识点展示界面
                                Data4Mooc.TNode CtNode = getTNode(moocDataList,test.getResults(pos).getTopic());
                                Intent intent = new Intent(ExamineResultActivity.this,TopicShowActivity.class);
                                intent.putExtra("topic",CtNode);
                                startActivity(intent);
                            }
                        });
                    }else {
                        topic.setVisibility(View.INVISIBLE);
                        topicName.setVisibility(View.INVISIBLE);
                    }
                    mPop = new PopupWindow(vPupupWindow,ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);
                    mPop.setOutsideTouchable(true);
                    mPop.setFocusable(true);
                    mPop.showAtLocation(vPupupWindow,Gravity.CENTER,0,0);
                }
            }));
            //mTvStandardAnswer.setText(printShortAnswerResult(test));
            //mTvResultExplain.setText(pirntComment(test));

        }

    }

}
