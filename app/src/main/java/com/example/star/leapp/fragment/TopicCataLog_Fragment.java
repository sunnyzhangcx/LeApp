package com.example.star.leapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.star.leapp.R;
import com.example.star.leapp.topiccatalog.ChildTopicCataLogActivity;
import com.example.star.leapp.topiccatalog.Exlv_Adapter_TopicCataLogList;
import com.example.star.leapp.topicshow.TopicShowActivity;

import java.util.ArrayList;
import java.util.List;

import data4mooc.Data4Mooc;

import static com.example.star.leapp.Application.LeappApplication.getFirstTopic;
import static com.example.star.leapp.Application.LeappApplication.getSecondTopic;
import static com.example.star.leapp.Application.LeappApplication.moocDataList;

public class TopicCataLog_Fragment extends android.support.v4.app.Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.topiccatalog_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final List<Data4Mooc.TNode> Group = getFirstTopic(moocDataList);
        final ArrayList<ArrayList<Data4Mooc.TNode>> Child = getSecondTopic(Group);

        ExpandableListView expandableListView = view.findViewById(R.id.myexlist);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Data4Mooc.TNode clickItem = Child.get(groupPosition).get(childPosition);
                Intent intent = null;
                if(clickItem.getChildCount()!=0)
                {
                    intent = new Intent(getActivity(),ChildTopicCataLogActivity.class);
                    intent.putExtra("childItem",clickItem);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(),TopicShowActivity.class);
                    intent.putExtra("topic",clickItem);
                    startActivity(intent);
                }

                return true;
            }

        });

        expandableListView.setAdapter(new Exlv_Adapter_TopicCataLogList(Group, Child, getActivity()));
    }


}