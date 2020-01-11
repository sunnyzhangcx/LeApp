package com.example.star.leapp.Application;

import com.example.star.leapp.Application.LeappApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data4mooc.Data4Mooc;

import static java.lang.Double.min;

class	result	{	        //	用户对某道题的一次回答
    List<String> answer;	//	用户对各小问的回答
    Date lasttime;			//	本次回答的日期和时间
    public result(List<String> ans, Date date) {
        answer = ans;
        lasttime = date;
    }
    double compare(List<String> std) {
        int n = std.size();
        int m = 0;
        for(int i=0; i<n; i++) {
            answer.get(i).trim();
            if( answer.get(i).equalsIgnoreCase(std.get(i))) {
                m++;
            }
        }
        return 0.0*m/n;
    }
    Date getLasttime() {
        return lasttime;
    }
}

public class testrecord {
    static ArrayList<ArrayList<result>>	results;
            //	记录每道题的每次回答的答案；测验题采用数组下标来标识

    public static void addresult(int testid, List<String> answer) {
        //  为testid标识的测验题添加答案answer
        if(results.size() > testid) {       //  已经存在答案时；
            ArrayList<result> rs = results.get(testid);
            result r = new result(answer, new Date());
            rs.add(r);
            return;
        }
        for(int i=0; i<testid; i++) {       //  不存在答案时，初始化其他测验题的答案数组
            if(i < results.size()) {
                continue;
            }
            results.add(new ArrayList());
        }
        ArrayList<result> rs = new ArrayList();
        results.add(rs);                ;   //  构建答案记录数组
        result r = new result(answer, new Date());
        rs.add(r);                          //  构建答案对象，添加到记录数组
        return;
    }

    public static double getTi(int testid, List<String> std) {
        //      获得testid标识的测验结果系数；std是标准答案
        if(testid >= results.size() ) {
            return 0.0;
        }
        List<result> rs = results.get(testid);
        List<Double> fs = new ArrayList<Double>();  //  正确率数组
        for(int i=0; i<rs.size(); i++) {
            double f = rs.get(i).compare(std);
            fs.add(new Double(f));
        }
        if(rs.size() == 1) {
            return fs.get(0).doubleValue();
        }
        double k = 1.0;
        for(int j=1; j<rs.size(); j++) {
            if(fs.get(j-1) > fs.get(j)) {
                k = 0.2;
            }
        }
        if(k == 1.0) {
            if(fs.size() > 0) {
                k = fs.get(fs.size() - 1);
            } else {
                k = 0.0;
            }
        }
        Date d0 = new Date();
        Date d1 = rs.get(rs.size()-1).getLasttime();
        return fs.get(rs.size()-1)*k*min(1.0, 30/(d0.getDay()-d1.getDay()));
    }

    static double getTi(Object obj) {
        //  获得指定知识点或案例的测验系数
        if(obj instanceof Data4Mooc.Topic) {
            List<Data4Mooc.TNode> tNodes = LeappApplication.getMoocDataList().getSetTNodeList();
            Data4Mooc.Topic tpc = (Data4Mooc.Topic)obj;
            int idx = tNodes.indexOf(tpc);
            int n = 0;              //  测试题个数
            double x = 0.0;         //  测试系数总和
            List<Data4Mooc.Test> tests = LeappApplication.getMoocDataList().getSetTestList();
            for(int i=0; i<tests.size(); i++) {         //  在所有测验题中找本知识点的相关测验
                Data4Mooc.Test test = tests.get(i);
                List<String> std = new ArrayList<String>();
                for(Data4Mooc.Result result : test.getResultsList()) {
                    std.add(result.getResult());        //  求标准答案集合
                }
                for(Data4Mooc.Result result : test.getResultsList()) {
                    if(idx == result.getTopic()) {
                        n++;                            //  统计相关测试题个数
                        x += getTi(i, std);             //  计算并累加测试系数
                        x *= test.getDifficulty()/10.0; //  乘以难度系数
                    }
                }
            }
            return x / n;
        } else if(obj instanceof Data4Mooc.Example) {
            return 1.0;
        }
        return 0.0;
    }
}
/*
测验结果系数的计算
        令 yn为各提问正确率，m为回答次数
        如果正确率单调上升，k=1；否则k=0.2
        Ti = 最近yn*k*min(1, 30/ (当前时间-lasttime天数)
*/