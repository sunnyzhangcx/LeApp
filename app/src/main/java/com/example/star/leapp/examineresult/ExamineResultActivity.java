package com.example.star.leapp.examineresult;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.star.leapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.equalsFillBlankOrShortAnswer;
import static com.example.star.leapp.Application.LeappApplication.equalsMultipleChoice;
import static com.example.star.leapp.Application.LeappApplication.getFillBlankCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getMoocDataList;
import static com.example.star.leapp.Application.LeappApplication.getMultipleChoiceCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getSimpleChioceCurrentResult;
import static com.example.star.leapp.Application.LeappApplication.getTestList;
import static com.example.star.leapp.Application.LeappApplication.pirntComment;
import static com.example.star.leapp.Application.LeappApplication.printFillBlankResult;
import static com.example.star.leapp.Application.LeappApplication.printMultipleChoiceAlternative;
import static com.example.star.leapp.Application.LeappApplication.printShortAnswerResult;

public class ExamineResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
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

        final int onePos = getIntent().getIntExtra("onePos",0);
        final int twoPos = getIntent().getIntExtra("twoPos",0);
        final int threePos = getIntent().getIntExtra("threePos",0);
        final int fourPos = getIntent().getIntExtra("fourPos",0);
        final int testPos = getIntent().getIntExtra("testPos",0);
        final int simpleChoiceResultPos = getIntent().getIntExtra("resultPos",0);
        final ArrayList<Integer> multipleChoiceResult = getIntent().getIntegerArrayListExtra("multiplechoicepos");
        final ArrayList<String> fillBlankResultUser = getIntent().getStringArrayListExtra("fillblank");
        final ArrayList<String> shortAnswerResultUser = getIntent().getStringArrayListExtra("shortanswer");

        //更新案例库
        Data4Mooc.MoocData moocDataList = getMoocDataList();
        List<Data4Mooc.Test> testList = getTestList(moocDataList);
        Data4Mooc.Test test =testList.get(testPos);

        if(onePos == 1){
            if(simpleChoiceResultPos == getSimpleChioceCurrentResult(test)){
                mTvJudge.setText("       恭喜你答对了！\n");
                mTvUserAnswer.setText(test.getResults(simpleChoiceResultPos).getAltertive() + "\n");
            }else if(simpleChoiceResultPos == -1){
                mTvJudge.setText("您没有进行选择！\n");
                mTvUserAnswer.setText("您的答案：您没有进行选择！\n");
            }
            else{
                mTvJudge.setText("       答案错误，此选择题正确选项为第"+getSimpleChioceCurrentResult(test) +"个选项 \n");
                mTvUserAnswer.setText(test.getResults(simpleChoiceResultPos).getAltertive() + "\n");
            }
            //报错未解决
            if(test.getResults(getSimpleChioceCurrentResult(test)).getAltertive() == null){
                mTvStandardAnswer.setText("没有内容！");
            }else{
                mTvStandardAnswer.setText(test.getResults(getSimpleChioceCurrentResult(test)).getAltertive() + "\n");
            }

            mTvResultExplain.setText(pirntComment(test));

            //显示知识点

        }else if(twoPos == 2){
            Set<Integer> multipleChoiceResultPos = new HashSet<>(multipleChoiceResult);
            if(equalsMultipleChoice(multipleChoiceResultPos, getMultipleChoiceCurrentResult(test))){
                mTvJudge.setText("恭喜您的多选题答对了！\n");
                mTvUserAnswer.setText(printMultipleChoiceAlternative(multipleChoiceResult,test));
            }else if(multipleChoiceResultPos.size() == 0){
                mTvJudge.setText("您的多选题没有进行选择！\n");
                mTvUserAnswer.setText("您的答案：您没有进行选择！\n");
            }else {
                mTvJudge.setText("答案错误，此多选题正确选项为第"+getMultipleChoiceCurrentResult(test) + "个选项 \n");
                mTvUserAnswer.setText(printMultipleChoiceAlternative(multipleChoiceResult,test));
            }
            mTvStandardAnswer.setText(printMultipleChoiceAlternative(new ArrayList<Integer>(getMultipleChoiceCurrentResult(test)),test));
            mTvResultExplain.setText(pirntComment(test));

        }else if(threePos == 3){
            if(equalsFillBlankOrShortAnswer(getFillBlankCurrentResult(test),fillBlankResultUser)){
                mTvJudge.setText("恭喜您的填空题全部答对！\n");
            } else {
                mTvJudge.setText("很遗憾，您的填空题答案有误。\n");
            }

            mTvUserAnswer.setText(printFillBlankResult(fillBlankResultUser));
            mTvStandardAnswer.setText(printFillBlankResult(test));
            mTvResultExplain.setText(pirntComment(test));


        }else if(fourPos == 4){
            if(equalsFillBlankOrShortAnswer(getFillBlankCurrentResult(test),shortAnswerResultUser)){
                mTvJudge.setText("恭喜您的简答题全部答对！\n");
            }else {
                mTvJudge.setText("很遗憾，您的简答题答案有误。\n");
            }

            mTvUserAnswer.setText(printFillBlankResult(shortAnswerResultUser));
            mTvStandardAnswer.setText(printShortAnswerResult(test));
            mTvResultExplain.setText(pirntComment(test));

        }

    }

}
