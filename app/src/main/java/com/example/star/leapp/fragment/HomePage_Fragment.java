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
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.leapp.R;
import com.example.star.leapp.homepage.coretopic.Relv_Adapter_CoreTopicList;
import com.example.star.leapp.homepage.progress.ProgressMainActivity;

public class HomePage_Fragment extends Fragment {

    private Button mBtnTopic;//核心知识点按钮
    private TextView mBtnIntelligent;//智能推荐
    private Button mBtnWay;//学习路线按钮
    private Button mBtnProgress;//学习进度按钮
    private PopupWindow mPopTopic;//核心知识点弹出框
    private RecyclerView mRvTopic;//核心知识点循环列表
    private PopupWindow mPopIntelli;//智能推荐弹出框

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment,null);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnTopic = view.findViewById(R.id.core_topic_list);
        mBtnIntelligent = view.findViewById(R.id.intelligent_recommendation);
        mBtnWay = view.findViewById(R.id.learning_way);
        mBtnProgress = view.findViewById(R.id.learning_progress);
        OnClick onClick = new OnClick();
        mBtnTopic.setOnClickListener(onClick);
        mBtnIntelligent.setOnClickListener(onClick);
        mBtnProgress.setOnClickListener(onClick);

    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.core_topic_list:
                    View view1 = getLayoutInflater().inflate(R.layout.layout_homepage_core_topic_list_linear_recycler_view,null);
                    mRvTopic = view1.findViewById(R.id.rv_main);
                    mRvTopic.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRvTopic.addItemDecoration(new MyDecoration());
                    mRvTopic.setAdapter(new Relv_Adapter_CoreTopicList(getActivity(), new Relv_Adapter_CoreTopicList.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            mPopTopic.dismiss();
                            Toast.makeText(getActivity(),"click"+pos,Toast.LENGTH_SHORT).show();
                        }
                    }));
                    mPopTopic = new PopupWindow(view1,mBtnTopic.getWidth(),ViewGroup.LayoutParams.WRAP_CONTENT);
                    mPopTopic.setOutsideTouchable(true);
                    mPopTopic.setFocusable(true);
                    mPopTopic.showAsDropDown(mBtnTopic,200,-200);
                    break;
                case R.id.intelligent_recommendation:
                    View view2 = getLayoutInflater().inflate(R.layout.layout_homepage_intelligent_recommendation_pop,null);
                    TextView textView1 = view2.findViewById(R.id.tv_1);
                    textView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPopIntelli.dismiss();
                            Toast.makeText(getActivity(),"click1",Toast.LENGTH_SHORT).show();
                        }
                    });
                    TextView textView2 = view2.findViewById(R.id.tv_2);
                    textView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPopIntelli.dismiss();
                            Toast.makeText(getActivity(),"click2",Toast.LENGTH_SHORT).show();
                        }
                    });
                    TextView textView3 = view2.findViewById(R.id.tv_3);
                    textView3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                mPopIntelli.dismiss();
                            Toast.makeText(getActivity(),"click3",Toast.LENGTH_SHORT).show();
                        }
                    });
                    mPopIntelli = new PopupWindow(view2,mBtnTopic.getWidth(),ViewGroup.LayoutParams.WRAP_CONTENT);
                    mPopIntelli.setOutsideTouchable(true);
                    mPopIntelli.setFocusable(true);
                    mPopIntelli.showAsDropDown(mBtnIntelligent,200,-200);
                    break;
                case R.id.learning_progress:
                    Intent intent = new Intent(getActivity(),ProgressMainActivity.class);
                    startActivity(intent);
            }


        }
    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divideHeight));
        }
    }
}
