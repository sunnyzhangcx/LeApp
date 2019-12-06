package com.example.star.leapp.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.star.leapp.R;
import com.example.star.leapp.examinelibrary.Relv_Adapter_ExamineList;
import com.example.star.leapp.examineshow.ExamineShowActivity1;
import com.example.star.leapp.examineshow.ExamineShowActivity2;
import com.example.star.leapp.examineshow.ExamineShowActivity3;
import com.example.star.leapp.examineshow.ExamineShowActivity4;

import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getLayout;
import static com.example.star.leapp.Application.LeappApplication.getMoocDataList;
import static com.example.star.leapp.Application.LeappApplication.getTestList;

public class ExamineLibrary_Fragment extends Fragment {

    private int testType;
    private List<Data4Mooc.Test> testList =null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.examinelibrary_fragment, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText mEtSearch = view.findViewById(R.id.example_search);
        RecyclerView mRvMain = view.findViewById(R.id.rv_main);

        //更新案例库
        Data4Mooc.MoocData moocDataList = getMoocDataList();
        testList = getTestList(moocDataList);
        Data4Mooc.Layout layout = getLayout(moocDataList);
        if (layout.getTestMode() == 1) {
            mRvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        }else if(layout.getTestMode() == 2){
            mRvMain.setLayoutManager(new GridLayoutManager(getContext(),2));
        }

        mRvMain.addItemDecoration(new MyDecoration());


        mRvMain.setAdapter(new Relv_Adapter_ExamineList(testList,getActivity(), new Relv_Adapter_ExamineList.OnItemClickListener() {
            Intent intent = null;
            @Override
            public void onClick(int pos) {
                testType =testList.get(pos).getType();
                //testType = moocDataList.getSetTest(pos).getType();
                switch (testType){
                    case 1:
                        intent = new Intent(getActivity(),ExamineShowActivity1.class);
                        intent.putExtra("pos",pos);
                        break;
                    case 2:
                        intent = new Intent(getActivity(),ExamineShowActivity2.class);
                        intent.putExtra("pos",pos);
                        break;
                    case 3:
                        intent = new Intent(getActivity(),ExamineShowActivity3.class);
                        intent.putExtra("pos",pos);
                        break;
                    case 4:
                        intent = new Intent(getActivity(),ExamineShowActivity4.class);
                        intent.putExtra("pos",pos);
                        break;
                    default:
                        break;
                }
                if(null!=intent)
                    startActivity(intent);

            }
        }));


    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,getResources().getDimensionPixelOffset(R.dimen.divideHeight),getResources().getDimensionPixelOffset(R.dimen.divideHeight));
        }
    }
}
