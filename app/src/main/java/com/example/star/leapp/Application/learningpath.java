package com.example.star.leapp.Application;
import com.example.star.leapp.Application.LeappApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import data4mooc.Data4Mooc;

public class learningpath {

    static List<Data4Mooc.GNode> setGNode;         //  案例图结点集合
    static List<Data4Mooc.TNode> setTNode;         //  知识点树节点集合

    static List<Object>    byExample() {
        //  获得案例驱动的学习路线
        List<Object> sorted = new ArrayList<Object>();    //  已排序的列表
        Map<String, Integer> marked = new HashMap<String, Integer>();

        Data4Mooc.MoocData data = LeappApplication.moocDataList;
        setGNode = data.getSetGNodeList();
        List<Data4Mooc.GNode> set = new ArrayList<Data4Mooc.GNode>();
        set.addAll(setGNode);                           //  复制所有图结点到队列set
        Iterator<Data4Mooc.GNode> it = set.iterator();
        while(it.hasNext()) {
            Data4Mooc.GNode n = it.next();              //  从set依次取图结点n
            if( contain(marked, n.getFromList()) ) {    //  若n所有前驱已经排序
                sorted.add(n.getExample());             //  则添加n到sorted
                marked.put(n.getExample().getTitle(), Integer.valueOf(0));
            } else {
                set.add(n);                             //  添加n到set队列尾
            }
        }
        return sorted;
    }

    static boolean contain(Map<String, Integer> map, List<Integer> froms) {
        for(Integer x :froms) {
            String s = setGNode.get(x.intValue()).getExample().getTitle();
            if(!map.containsKey(s)) {
                return false;
            }
        }
        return true;
    }

    static List<Object>    byTopic() {
        //  获得传统学习路线（基于知识点）
        Data4Mooc.MoocData data = LeappApplication.moocDataList;
        setTNode = data.getSetTNodeList();      //  知识点树节点集合

        List<Object> examples = byExample();
        List<Integer> maxs = new ArrayList<Integer>();
        for(Object ex : examples) {
            maxs.add(Integer.valueOf(-1));      //  每个案例引用的所有知识点的索引最大值max
        }
        for(int i=0; i<setTNode.size(); i++) {  //  检查第i个知识点
            Data4Mooc.TNode tnode = setTNode.get(i);
            for(Integer x : tnode.getTopic().getExamplesList()) {
                Integer max = maxs.get(x.intValue());
                if(i > max) {                   //  检查它的相关案例，用i更新其max值
                    maxs.set(x.intValue(), new Integer(i));
                }
            }
            for(int j=0; j<setGNode.size(); j++) {      //  检查所有案例
                Data4Mooc.GNode gnode = setGNode.get(j);
                if(refered(tnode.getTopic().getTitle(), gnode.getExample())) {
                    Integer max = maxs.get(j);
                    if(i > max) {               //  若案例内容中引用了当前知识点，用i更新其max
                        maxs.set(j, new Integer(i));
                    }
                }
            }
        }
        int next = 0;   //  当前案例索引
        List<Object> sorted = new ArrayList<Object>();
        for(int k=0; k<setTNode.size(); k++) {      //  当前知识点索引
            Data4Mooc.TNode tNode = setTNode.get(k);
            while(next < setGNode.size()) {
                if(k <= maxs.get(next).intValue()) {
                    sorted.add(tNode.getTopic());
                    break;          //  当前知识点索引小于当前案例max值时，输出知识点
                }
                sorted.add(setGNode.get(next).getExample());
                next++;             //  否则，输出案例
            }
        }
        return sorted;
    }

    static boolean refered(String topic, Data4Mooc.Example example) {
        //      知识点topic在案例example中是否被引用？
        for(Data4Mooc.Section section : example.getSectionsList()) {
            for(Data4Mooc.Item item : section.getItemsList()) {
                if(item.getType() == 3) {
                    //      知识点引用
                    if(topic.compareTo(topic) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
