package com.example.star.leapp.topiccatalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.star.leapp.R;
import com.example.star.leapp.topicshow.TopicShowActivity;

import java.util.ArrayList;
import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getSecondTopic;
import static com.example.star.leapp.Application.LeappApplication.getTNodeChildList;

public class ChildTopicCataLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_topic);
        ExpandableListView mElvChildTopic = findViewById(R.id.myexlist_activity);

        Data4Mooc.TNode childItem = (Data4Mooc.TNode) getIntent().getSerializableExtra("childItem");//第一级知识点的父亲结点
        List<Data4Mooc.TNode> ChildFirstTopic = getTNodeChildList(childItem);//第一级知识点
        ArrayList<ArrayList<Data4Mooc.TNode>> Child = getSecondTopic(ChildFirstTopic);//第二级知识点

        mElvChildTopic.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        final ArrayList<ArrayList<Data4Mooc.TNode>> finalChild = Child;
        mElvChildTopic.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Data4Mooc.TNode clickItem = finalChild.get(groupPosition).get(childPosition);
                Intent intent = null;
                if(clickItem.getChildCount()!=0)
                {
                    intent = new Intent(ChildTopicCataLogActivity.this,ChildTopicCataLogActivity.class);
                    intent.putExtra("childItem",clickItem);
                    startActivity(intent);
                } else {
                    intent = new Intent(ChildTopicCataLogActivity.this,TopicShowActivity.class);
                    intent.putExtra("topic",clickItem);
                    startActivity(intent);
                }
                return true;
            }
        });

        mElvChildTopic.setAdapter(new Exlv_Adapter_Topic_ChildTopicCataLog(ChildFirstTopic,Child,this));
    }
}
