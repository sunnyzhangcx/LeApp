package com.example.star.leapp.Application;

import com.example.star.leapp.Application.LeappApplication;
import com.example.star.leapp.Application.testrecord;

import java.util.ArrayList;
import java.util.List;

import data4mooc.Data4Mooc;

/*
 推荐算法和学习路线模块的说明（外部接口）
 class intelrecommend类：       //  智能推荐（k个元素的列表）
    static List<Object> recommend(int k, List<Object> path) {
        //  求path列表中各个案例和知识点的优先级最高的k个元素（知识点或案例）的列表
    static List<Integer> getFrom(int k, int topicid) {
        //  获得topicid指定知识点的优先级最高的k个相关案例的索引列表
 class learningpath类:         //  求学习路线（一组知识点或案例）
    static List<Object>    byExample();
        //  获得采用案例驱动方式的学习路线
    static List<Objcet>    byTopic();
        //  获得传统方式（按照知识点）的学习路线
 class learnigrecord类：        //  学习记录，支持智能推荐
    static void setTopicSelect(int topicid, int idx, int select) {
        //      为topicid指定的知识点的第idx小节设置反馈选项
    static void setTopicTime(int id, int idx, double tm) {
        //      为id指定的知识点的第idx小节设置阅读时间
 class testrecord类：           //  测验结果记录，支持智能推荐
    static void addresult(int testid, List<String> answer) {
        //  为testid标识的测验题添加答案answer
 推荐与学习路线模块的使用：
 （1）首页智能推荐（案例驱动方式）：得到Example对象列表
            List<Object> list = learningpath.byExapmle();
            List<Object> recom = intelcommend(5, list);
 （2）首页智能推荐（传统方式）：得到知识点或案例对象列表
            List<Object> list = learningpath.byTopic();
            List<Object> recom = intelcommend(5, list);
 （3）知识点展示页推荐相关案例：（得到案例对象索引表）
            List<Integer> idxlist = intelcommend(5, 当前知识点索引);

 （4）案例展示页推荐后续内容：得到知识点或案例对象列表
        案例驱动方式
            List<Object> list = learningpath.byExapmle();
            int idx = list.indexof(当前案例对象);
            list = list.sublist(idx+1, list.size()-1);
            List<Object> recom = intelcommend(5, list);
        传统方式
            List<Object> list = learningpath.byTopic();
            int idx = list.indexof(当前案例对象);
            list = list.sublist(idx+1, list.size()-1);
 */

public class intelrecommend {

    static List<Object> recommend(int k, List<Object> path) {
        //  求path列表中各个案例和知识点的优先级最高的k个元素
        int n = path.size();
        List<Double> coeffs = new ArrayList<Double>();
        for(int j=0; j<n; j++) {
            double x = (1.0+n-j)/n;
            coeffs.add(Double.valueOf(x));          //  优先级系数初值
        }
        for(int i=0; i<n; i++) {
            Object obj = path.get(i);               //  当前内容
            Double Pi = coeffs.get(i).doubleValue();    //  取初值
            Pi = Pi * (1 - learnigrecord.getXi(obj));   //  乘以（1-学习系数）
            Pi = Pi * (1 - testrecord.getTi(obj));      //  乘以（1-测试系数）
            coeffs.set(i, Pi);                      //  用计算结果更新优先级序列
        }
        if(k > path.size()) {
            k = path.size();
        }
        for(int m=0; m<k; m++) {
            //  循环k次，按照coeffs序列元素值的对path序列元素进行排序
            for(int i=coeffs.size()-2; i>=0; i--) {
                //      将最大值移到最前面，依次得到降序1，2，3。。。序列
                if(coeffs.get(i).doubleValue()<coeffs.get(i+1).doubleValue()) {
                    Double tmp = coeffs.get(i);
                    coeffs.set(i, coeffs.get(i+1));
                    coeffs.set(i+1, tmp);
                    Object ob = path.get(i);
                    path.set(i, path.get(i+1));
                    path.set(i+1, ob);
                }
            }
        }
        return path.subList(0, k-1);    //  返回前k项
    }

    static List<Integer> getFrom(int k, int topicid) {
        //  获得topicid指定知识点的k个相关案例的索引列表
        List<Data4Mooc.GNode> gNodes = LeappApplication.getMoocDataList().getSetGNodeList();
        List<Data4Mooc.TNode> tNodes = LeappApplication.getMoocDataList().getSetTNodeList();
        if(topicid >= tNodes.size()) {
            return null;
        }
        Data4Mooc.Topic topic = tNodes.get(topicid).getTopic();
        List<Integer> examples = topic.getExamplesList();
        int n = examples.size();
        List<Double> coeffs = new ArrayList<Double>();
        for(int j=0; j<n; j++) {
            double x = (1.0+n-j)/n;
            coeffs.add(Double.valueOf(x));          //  优先级系数初值
        }
        for(int i=0; i<n; i++) {
            Data4Mooc.Example obj = gNodes.get(examples.get(i).intValue()).getExample();               //  当前内容
            Double Pi = coeffs.get(i).doubleValue();    //  取初值
            Pi = Pi * (1 - learnigrecord.getXi(obj));   //  乘以（1-学习系数）
            Pi = Pi * (1 - testrecord.getTi(obj));      //  乘以（1-测试系数）
            Pi = Pi * topic.getWeights(i)/10.0;         //  乘以相关度/10
            coeffs.set(i, Pi);                      //  用计算结果更新优先级序列
        }
        if(k > examples.size()) {
            k = examples.size();
        }
        for(int m=0; m<k; m++) {
            //  循环k次，按照coeffs序列元素值的对path序列元素进行排序
            for(int i=coeffs.size()-2; i>=0; i--) {
                //      将最大值移到最前面，依次得到降序1，2，3。。。序列
                if(coeffs.get(i).doubleValue()<coeffs.get(i+1).doubleValue()) {
                    Double tmp = coeffs.get(i);
                    coeffs.set(i, coeffs.get(i+1));
                    coeffs.set(i+1, tmp);
                    Integer ob = examples.get(i);
                    examples.set(i, examples.get(i+1));
                    examples.set(i+1, ob);
                }
            }
        }
        return examples.subList(0, k-1);    //  返回前k项
    }
}
