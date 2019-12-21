package com.example.star.leapp.topiccatalog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.star.leapp.R;
import com.example.star.leapp.topicshow.TopicShowActivity;

import java.util.ArrayList;
import java.util.List;

import data4mooc.Data4Mooc;

public class Exlv_Adapter_TopicCataLogList extends BaseExpandableListAdapter {

    private Context context;
    private List<Data4Mooc.TNode> Group;
    private ArrayList<ArrayList<Data4Mooc.TNode>> Child;

    public Exlv_Adapter_TopicCataLogList(List<Data4Mooc.TNode> group, ArrayList<ArrayList<Data4Mooc.TNode>> Child, Context context) {
        this.context = context;
        this.Group = group;
        this.Child = Child;
    }

    @Override
    public int getGroupCount() {
        if(Group == null){
            return 0;
        }
        return Group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return Group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;     //自己设定的一个简单类，用来存储控件的相关信息
        if (convertView == null) {          //这里的convertView其实是一个起缓冲作用的，工具，因为当一个item从屏幕中滚出，我们把它放到convertView里，这样再滑回来的时候可以直接去取，不用重新创建
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_topiccatalog_parentitem, null);//把界面放到缓冲区
            groupHolder = new GroupHolder();          //实例化我们创建的这个类
            groupHolder.txt = convertView.findViewById(R.id.parentTv);  //实例化类里的TextView
            groupHolder.btn = convertView.findViewById(R.id.topic_content);
            groupHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,TopicShowActivity.class);
                    intent.putExtra("topic",Group.get(groupPosition));
                    context.startActivity(intent);
                }
            });
            convertView.setTag(groupHolder);                                 //给view对象一个标签，告诉计算机我们已经在缓冲区里放了一个view，下回直                                                                               //接来拿就行了
        } else {
            groupHolder = (GroupHolder) convertView.getTag();     //然后他就直接来拿
        }

        groupHolder.txt.setText(Group.get(groupPosition).getTopic().getTitle());//最后在相应的group里设置他相应的Text
        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_topiccatalog_childitem, null);
            itemHolder = new ItemHolder();
            itemHolder.txt = convertView.findViewById(R.id.childTv);
            itemHolder.btn = convertView.findViewById(R.id.topic_content);
            itemHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,TopicShowActivity.class);
                    intent.putExtra("topic",Child.get(groupPosition).get(childPosition));
                    context.startActivity(intent);
                }
            });
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        itemHolder.txt.setText(Child.get(groupPosition).get(childPosition).getTopic().getTitle());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}

class GroupHolder {
    public TextView txt;
    public Button btn;
}

class ItemHolder {
    public TextView txt;
    public Button btn;
}