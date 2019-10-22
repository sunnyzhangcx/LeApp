package com.example.star.leapp.topiccatalog;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.star.leapp.R;
import com.example.star.leapp.topicshow.TopicShowActivity;

import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getMoocDataList;
import static com.example.star.leapp.Application.LeappApplication.getThirdTopic;

public class ThirdTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_topic);
        RecyclerView mRvThirdTopic = findViewById(R.id.rv_main);
        mRvThirdTopic.setLayoutManager(new LinearLayoutManager(ThirdTopicActivity.this));
        mRvThirdTopic.addItemDecoration(new MyDecoration());

        Intent intent = getIntent();
        final int groupPosition = intent.getIntExtra("groupPosition",0);
        final int childPosition = intent.getIntExtra("childPosition",0);
        //更新案例库
        Data4Mooc.MoocData moocDataList = getMoocDataList();
        final List<Data4Mooc.TNode> ThirdTopic = getThirdTopic(moocDataList,groupPosition,childPosition);


        mRvThirdTopic.setAdapter(new Relv_Adapter_Topic_ThirdTopic(ThirdTopic,ThirdTopicActivity.this, new Relv_Adapter_Topic_ThirdTopic.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(ThirdTopicActivity.this,TopicShowActivity.class);
                int CPos = 1;//临时数值
                intent.putExtra("CPos",CPos);
                intent.putExtra("ThirdPos",pos);
                intent.putExtra("groupPosition",groupPosition);
                intent.putExtra("childPosition",childPosition);
                startActivity(intent);
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
