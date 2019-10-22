package com.example.star.leapp.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.star.leapp.R;
import com.example.star.leapp.question.Relv_Adapter_QuestionList;
import com.example.star.leapp.questionshow.QuestionShowActivity;


import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getMoocDataList;
import static com.example.star.leapp.Application.LeappApplication.getQandAList;

public class Question_Fragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_fragment, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText mEtSearch = view.findViewById(R.id.question_search);
        RecyclerView mRvMain = view.findViewById(R.id.rv_main);

        //更新案例库
        Data4Mooc.MoocData moocDataList = getMoocDataList();
        List<Data4Mooc.QandA> qandAList = getQandAList(moocDataList);

        mRvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvMain.addItemDecoration(new Question_Fragment.MyDecoration());
        mRvMain.setAdapter(new Relv_Adapter_QuestionList(qandAList,getActivity(), new Relv_Adapter_QuestionList.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(getActivity(),QuestionShowActivity.class);
                intent.putExtra("pos",pos);
                startActivity(intent);
                //Toast.makeText(getActivity(),"click..."+pos,Toast.LENGTH_SHORT).show();
            }
        }));


    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divideHeight));
        }
    }
}
