package com.example.star.leapp.Application;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data4mooc.Data4Mooc;
import data4mooc.MoocCreator;
import data4mooc.MoocWriter;

public class LeappApplication extends Application {

    private static String qaFilepath = MoocWriter.FilePath;
    public static Data4Mooc.MoocData moocDataList = LeappApplication.getQueryMoocDataList(qaFilepath);


    public static Data4Mooc.MoocData getQueryMoocDataList(String fileName){
        File qaFile = new File(fileName);
        if(!qaFile.exists()||qaFile.length()==0){
            Log.e("error","file not exist/no sample");
            //moocDataList = null;
            return null;
        }
        FileInputStream input = null;
        try {
            input = new FileInputStream(fileName);
                moocDataList = Data4Mooc.MoocData.parseFrom(input);
            return moocDataList;
        }catch  (FileNotFoundException e){
            System.out.println("file not exist!");
        } catch (IOException e) {
            System.out.println("write failed");
        }
        return null;
    }

    /**
     * 点击某个案例列表，跳转时只需直接返回，无需查询
     * @return
     */
    public static Data4Mooc.MoocData getMoocDataList() {
        return moocDataList;
    }

    //获取常见问题列表
    public static List<Data4Mooc.QandA> getQandAList(Data4Mooc.MoocData moocDataList){
        return moocDataList.getSetQAList();
    }


    //获取测试题列表
    public static List<Data4Mooc.Test> getTestList(Data4Mooc.MoocData moocDataList){
        return moocDataList.getSetTestList();
    }

    //获取知识点列表
    public static List<Data4Mooc.TNode> getTNodeList(Data4Mooc.MoocData moocDataList){
        return moocDataList.getSetTNodeList();
    }

    //获取案例列表
    public static List<Data4Mooc.GNode> getGNodeList(Data4Mooc.MoocData moocDataList){
        return moocDataList.getSetGNodeList();
    }

    //获取一级知识点列表
    public static List<Data4Mooc.TNode> getFirstTopic(Data4Mooc.MoocData moocDataList) {
        int length = moocDataList.getSetTNode(0).getChildCount();//一级知识点的个数
        List<Data4Mooc.TNode> FirstTopic = new ArrayList<>();
        Data4Mooc.TNode RootTNode = moocDataList.getSetTNode(0);//树根节点

        if (length != 0) {
            if (RootTNode.getChildList() == null){
                return null;
            }
            for (int i = 0;i<length;i++){
                int tNodeChild=RootTNode.getChild(i);
                FirstTopic.add(moocDataList.getSetTNode(tNodeChild));
            }

            return FirstTopic;
        }else {
            return null;
        }

    }

    //获取二级知识点列表
    public static List<List<Data4Mooc.TNode>> getSecondTopic(Data4Mooc.MoocData moocDataList) {
        List<List<Data4Mooc.TNode>> SecondTopic = new ArrayList<>();
        List<Data4Mooc.TNode> FirstTopic = getFirstTopic(moocDataList);
        int FirstTopicLength = FirstTopic.size();

        for (int i = 0; i < FirstTopicLength; i++) {
            List<Integer> childList = FirstTopic.get(i).getChildList();
            List<Data4Mooc.TNode> tempSecond = new ArrayList<>();
            if (childList == null) {
                tempSecond = null;
            }
            else{
                for (int j = 0; j < childList.size(); j++) {
                    tempSecond.add(moocDataList.getSetTNode(childList.get(j)));
                }
            }
            SecondTopic.add(tempSecond);
        }
        return SecondTopic;
    }

    //获取三级知识点列表(当前的三级知识点列表 不是全部的)
    public static List<Data4Mooc.TNode> getThirdTopic(Data4Mooc.MoocData moocDataList,int groupPosition,int childPosition) {
        List<Data4Mooc.TNode> ThirdTopic = new ArrayList<>();
        List<List<Data4Mooc.TNode>> SecondTopic = getSecondTopic(moocDataList);

        List<Integer> childList = SecondTopic.get(groupPosition).get(childPosition).getChildList();

        for(int i = 0;i < childList.size();i ++){
            ThirdTopic.add(moocDataList.getSetTNode(childList.get(i)));
        }

        return ThirdTopic;
    }

    //获取Q&A中相关知识点的列表
    public static List<Data4Mooc.TNode> getQAtopicList(List<Integer> list, List<Data4Mooc.TNode> tNodeList){
        List<Data4Mooc.TNode> AboutTopicList = new ArrayList<>();
        int length = list.size();
        for(int i = 0;i < length;i ++){
            AboutTopicList.add(tNodeList.get(list.get(i)));
        }

        return AboutTopicList;
    }
}