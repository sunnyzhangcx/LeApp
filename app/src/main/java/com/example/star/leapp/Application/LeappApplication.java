package com.example.star.leapp.Application;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if(null==moocDataList)
            return null;
        return moocDataList.getSetQAList();
    }


    //获取测试题列表
    public static List<Data4Mooc.Test> getTestList(Data4Mooc.MoocData moocDataList){
        if(null==moocDataList)
            return null;
        return moocDataList.getSetTestList();
    }

    //获取知识点列表
    public static List<Data4Mooc.TNode> getTNodeList(Data4Mooc.MoocData moocDataList){
        if(null==moocDataList)
            return null;
        return moocDataList.getSetTNodeList();
    }

    //获取案例列表
    public static List<Data4Mooc.GNode> getGNodeList(Data4Mooc.MoocData moocDataList){
        if(null==moocDataList)
            return null;
        return moocDataList.getSetGNodeList();
    }

    public static Data4Mooc.Layout getLayout(Data4Mooc.MoocData moocDataList){
        if(null==moocDataList)
            return null;
        return moocDataList.getLayout();
    }

    //获取知识点中的孩子列表
    public static List<Integer> getTopicChildList(List<Data4Mooc.TNode> tNodeList, int i){
        return tNodeList.get(i).getChildList();
    }

    //获取知识点中的孩子列表
    public static List<Integer> getTopicChildList(List<List<Data4Mooc.TNode>> tNodeList, int i,int j){
        return tNodeList.get(i).get(j).getChildList();
    }

    //获取知识点中的孩子列表
    public static List<Integer> getTopicChildList(Data4Mooc.TNode tNode){
        return tNode.getChildList();
    }

    //获取一级知识点列表
    public static List<Data4Mooc.TNode> getFirstTopic(Data4Mooc.MoocData moocDataList) {
        if(null==moocDataList)
            return null;
        int length = moocDataList.getSetTNode(0).getChildCount();//一级知识点的个数
        List<Data4Mooc.TNode> FirstTopic = new ArrayList<>();
        Data4Mooc.TNode RootTNode = moocDataList.getSetTNode(0);//树根节点

        if (length != 0) {
            if (RootTNode.getChildList() == null){
                return null;
            }
            //之前是int i = 0 因为我自己写的测试数据 root的childlist不包含自己
            for (int i = 1;i<length;i++){
                int tNodeChild=RootTNode.getChild(i);
                FirstTopic.add(moocDataList.getSetTNode(tNodeChild));
            }

            return FirstTopic;
        }else {
            return null;
        }

    }

    //获取二级知识点列表
    public static ArrayList<ArrayList<Data4Mooc.TNode>> getSecondTopic(List<Data4Mooc.TNode> FirstTopic) {
        if(null==moocDataList)
            return null;
        ArrayList<ArrayList<Data4Mooc.TNode>> SecondTopic = new ArrayList<>();
        int FirstTopicLength = FirstTopic.size();//一级知识点的长度

        for (int i = 0; i < FirstTopicLength; i++) {
            //获取一级知识点列表里知识点的孩子列表的索引
            List<Integer> childList = getTopicChildList(FirstTopic,i);
            //List<Integer> childList = FirstTopic.get(i).getChildList();
            //存当前知识点孩子列表的对应知识点
            ArrayList<Data4Mooc.TNode> tempSecond = new ArrayList<>();
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


    //获取二级知识点列表
    public static List<List<Data4Mooc.TNode>> getSecondTopic(Data4Mooc.MoocData moocDataList) {
        if(null==moocDataList)
            return null;
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
    public static List<Data4Mooc.TNode> getThirdTopic(List<List<Data4Mooc.TNode>> SecondTopic,int groupPosition,int childPosition) {
        if(null==moocDataList)
            return null;
        List<Data4Mooc.TNode> ThirdTopic = new ArrayList<>();

        List<Integer> childList = getTopicChildList(SecondTopic,groupPosition,childPosition);

        for(int i = 0;i < childList.size();i ++){
            ThirdTopic.add(moocDataList.getSetTNode(childList.get(i)));
        }

        return ThirdTopic;
    }

    //获取三级知识点列表(当前的三级知识点列表 不是全部的)
    public static List<Data4Mooc.TNode> getThirdTopic(Data4Mooc.MoocData moocDataList,int groupPosition,int childPosition) {
        if(null==moocDataList)
            return null;
        List<Data4Mooc.TNode> ThirdTopic = new ArrayList<>();
        List<List<Data4Mooc.TNode>> SecondTopic = getSecondTopic(moocDataList);

        List<Integer> childList = SecondTopic.get(groupPosition).get(childPosition).getChildList();

        for(int i = 0;i < childList.size();i ++){
            ThirdTopic.add(moocDataList.getSetTNode(childList.get(i)));
        }

        return ThirdTopic;
    }

    public static ArrayList<Data4Mooc.TNode> getTNodeChildList(Data4Mooc.TNode tNode){
        ArrayList<Data4Mooc.TNode> ChildList = new ArrayList<>();
        if(tNode.getChildCount() == 0){
            return null;
        }else {
            for (int i = 0; i < tNode.getChildCount(); i ++){
                ChildList.add(moocDataList.getSetTNode(tNode.getChild(i)));
            }
        }
        return ChildList;
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

    //get单选题正确答案选项
    public static int getSimpleChioceCurrentResult(Data4Mooc.Test test){
        int result = -1;
        List<Data4Mooc.Result> resultList = test.getResultsList();
        for(int i = 0;i <resultList.size(); i ++){
            if(resultList.get(i).getResult().equals("0")){
                result = i;
            }
        }
        return result;
    }

    //get多选题正确答案选项
    public static Set<Integer> getMultipleChoiceCurrentResult(Data4Mooc.Test test){
        Set<Integer> result = new HashSet<>();
        List<Data4Mooc.Result> resultList = test.getResultsList();
        for(int i = 0;i <resultList.size(); i ++){
            if(resultList.get(i).getResult().equals("true")){
                result.add(i);
            }
        }
        return result;
    }

    //get填空题所有答案
    public static ArrayList<String> getFillBlankCurrentResult(Data4Mooc.Test test){
        ArrayList<String> result = new ArrayList<>();
        List<Data4Mooc.Result> resultList = test.getResultsList();
        for(int i = 0; i <resultList.size(); i ++){
            result.add(test.getResults(i).getResult());
        }
        return result;
    }

    //比较填空题或简答题用户的答案和正确答案是否一样
    public static boolean equalsFillBlankOrShortAnswer(ArrayList<String> list1,ArrayList<String> list2){
        if(null != list1 && null != list2){
            if(list1.containsAll(list2) && list2.containsAll(list1)){
                return true;
            }
            return false;
        }
        return true;
    }

    //比较多选题用户的选项和正确选项是否一样
    public static boolean equalsMultipleChoice(Set<?> set1, Set<?> set2){
        if(set1 == null || set2 ==null){//null就直接不比了
            return false;
        }
        if(set1.size()!=set2.size()){//大小不同也不用比了
            return false;
        }
        return set1.containsAll(set2);//最后比containsAll
    }

    //显示多选题正确选项的选项内容
    public static String printMultipleChoiceAlternative(List<Integer> result, Data4Mooc.Test test){
        List<String> printMultipleAlternative = new ArrayList<>();
        String print = new String();
        for(int i = 0; i < result.size(); i ++){
            printMultipleAlternative.add(test.getResults(result.get(i)).getAltertive() + "\n");

        }

        for(int i = 0; i < printMultipleAlternative.size(); i ++){
            print = print.concat(printMultipleAlternative.get(i));
        }

        return print;

    }

    //显示填空题用户答案的内容
    public static String printFillBlankResult(ArrayList<String> result){
        String print = new String();
        for (int i = 0; i< result.size(); i ++){
            int j = i+1;
            print = print.concat(j + "." + result.get(i) + "\n");
        }
        return print;

    }

    //显示填空题正确答案的内容
    public static String printFillBlankResult(Data4Mooc.Test test){
        ArrayList<String> printFillBlank = new ArrayList<>();
        String print = new String();

        for (int i = 0; i < test.getResultsList().size() ; i ++){
            printFillBlank.add(test.getResults(i).getAltertive());
            printFillBlank.add(test.getResults(i).getResult() + "\n");
        }
        for (int i = 0; i< printFillBlank.size(); i ++){
            print = print.concat(printFillBlank.get(i));
        }
        return print;

    }

    //显示简答题正确答案的内容
    public static String printShortAnswerResult(Data4Mooc.Test test){
        String print = new String();

        for (int i = 0; i< test.getResultsList().size(); i ++){
            int j = i + 1;
            print = print.concat(j + "." + test.getResults(i).getResult() + "\n");
        }
        return print;

    }

    //显示测试题的解释内容
    public static String pirntComment(Data4Mooc.Test test){
        List<String> printComment = new ArrayList<>();
        String print = new String();
        for(int i = 0; i < test.getResultsList().size(); i ++){
            printComment.add(test.getResults(i).getComment() + "\n");

        }

        for(int i = 0; i < printComment.size(); i ++){
            print = print.concat(printComment.get(i));
        }

        return print;

    }

    //显示测试题的相关知识点
    public static String printChoiceTopic(Data4Mooc.Test test, Data4Mooc.Topic topic){
        List<String> printTopic = new ArrayList<>();
        String print = new String();
        for(int i = 0; i <test.getResultsList().size(); i ++){
            if(test.getResults(i).hasTopic()){
                //printTopic.add(test.getResults(i).getTopic())
            }

        }

        return print;
    }

    //显示测验题的问题陈述（多条内容） 问题+图片+文本等形式
    public static String printTestItem(Data4Mooc.Test test){
        String print = new String();

        for(int i = 0; i < test.getItemsCount(); i ++){
            print = test.getItems(i).getContent();
        }

        return print;
    }

}
