package com.example.star.leapp.examineresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.star.leapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.equalsMultipleChoice;
import static com.example.star.leapp.Application.LeappApplication.getMoocDataList;
import static com.example.star.leapp.Application.LeappApplication.getMultipleChoiceCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getSimpleChioceCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.pirntMultipleChoiceAlternative;
import static com.example.star.leapp.Application.LeappApplication.pirntMultipleChoiceComment;

public class ExamineResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_result);
        TextView mTvJudge = findViewById(R.id.examine_result_judge);
        TextView mTvStandardAnswerName =findViewById(R.id.examine_result_standard_answer_name);
        TextView mTvStandardAnswer =findViewById(R.id.examine_result_standard_answer);
        TextView mTvUserAnswerName =findViewById(R.id.examine_result_user_answer_name);
        TextView mTvUserAnswer =findViewById(R.id.examine_result_user_answer);
        TextView mTvResultExplainName =findViewById(R.id.examine_result_explain_name);
        TextView mTvResultExplain =findViewById(R.id.examine_result_explain);


        Intent intent = getIntent();
        final int onePos = intent.getIntExtra("onePos",0);
        final int twoPos = intent.getIntExtra("twoPos",0);
        final int testPos = intent.getIntExtra("testPos",0);
        final int simpleChoiceResultPos = intent.getIntExtra("resultPos",0);
        final ArrayList<Integer> multipleChoiceResult = getIntent().getIntegerArrayListExtra("multiplechoicepos");

        //更新案例库
        Data4Mooc.MoocData moocDataList = getMoocDataList();
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        Data4Mooc.Test test =testList.get(testPos);

        if(onePos == 1){
            if(simpleChoiceResultPos == getSimpleChioceCurrentResult(test)){
                mTvJudge.setText("       恭喜你答对了！");
                mTvUserAnswer.setText(test.getResults(simpleChoiceResultPos).getAltertive() + "\n");
            }else if(simpleChoiceResultPos == -1){
                mTvJudge.setText("您没有进行选择");
                mTvUserAnswer.setText("您的答案：您没有进行选择！");
            }
            else{
                mTvJudge.setText("       答案错误，此选择题正确选项为第"+getSimpleChioceCurrentResult(test) +"个选项");
                mTvUserAnswer.setText(test.getResults(simpleChoiceResultPos).getAltertive() + "\n");
            }
            mTvStandardAnswer.setText(test.getResults(getSimpleChioceCurrentResult(test)).getAltertive() + "\n");
            mTvResultExplain.setText(test.getResults(getSimpleChioceCurrentResult(test)).getComment());
        }else if(twoPos == 2){
            Set<Integer> multipleChoiceResultPos = new HashSet<>(multipleChoiceResult);
            if(equalsMultipleChoice(multipleChoiceResultPos, getMultipleChoiceCurrentResult(test))){
                mTvJudge.setText("恭喜您的多选题答对了！");
            }else if(multipleChoiceResultPos.size() == 0){
                mTvJudge.setText("您的多选题没有进行选择！");
            }else {
                mTvJudge.setText("答案错误，此多选题正确选项为第"+getMultipleChoiceCurrentResult(test) + "个选项");
            }

            mTvStandardAnswer.setText(pirntMultipleChoiceAlternative(new ArrayList<Integer>(getMultipleChoiceCurrentResult(test)),test));
            mTvUserAnswer.setText(pirntMultipleChoiceAlternative(multipleChoiceResult,test));
            mTvResultExplain.setText(pirntMultipleChoiceComment(new ArrayList<Integer>(getMultipleChoiceCurrentResult(test))  ,test));

        }else if(test.getType() == 3){

        }else if(test.getType() == 4){

        }

    }

}
