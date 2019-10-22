package com.example.star.leapp.examineresult;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.star.leapp.R;

public class ExamineResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_result);
        TextView mTvJudge = findViewById(R.id.examine_result_judge);
        TextView mTvStandardAnswer =findViewById(R.id.examine_result_standard_answer);
        TextView mTvUserAnswer =findViewById(R.id.examine_result_user_answer);
        TextView mTvResultExplain =findViewById(R.id.examine_result_explain);


    }
}
