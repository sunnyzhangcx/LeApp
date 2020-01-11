package com.example.star.leapp.Application;

import android.text.Selection;

import com.example.star.leapp.Application.LeappApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
;import data4mooc.Data4Mooc;

import static java.lang.Math.max;
import static java.lang.Math.min;
/*
        ⦁	学习记录
        Class	RecordSet	{
        ⦁	测验结果
        Class	ResultSet	{
        推荐算法：
        针对按照优先级由高到低排列的一组案例或知识点。
        令 Pi 表示第i项知识点或案例的优先级，n为项的个数。
        优先级的计算
        Pi = (n+1-i)/n * (1- Xi ) * (1- Ti) * Di/10 * Ri
        其中，	Xi为学习记录系数，取值范围(0,1);
        Ti为测验结果系数，取值范围(0,1)
        Di为难度系数，取值[1,10]
        Ri为相关系数
        相关系数的计算
        令第i项知识点或案例有m个相关知识点或案例
        Ri = (Pi1 + Pi2 + … + Pim)/m
        其中：Pi1、Pi2、…Pim是相关知识点或案例的优先级
*/

public class learnigrecord {
    class	record	{
        List<Integer>	select;		        //	每个小节的反馈选项
        //        取值：“掌握”=1、“了解”=0.8、“一知半解”=0.4、“困惑”=0.1、“路过”=-1
        List<Double>	times;		            //	每个小节（section）的阅读时间
        Date lasttime;			            //	最新一次阅读的日期和时间
    }
    static int	mode;			                    //	推荐方式0:顺序阅读,1:案例驱动
    static ArrayList<record>   topicRecord;		//	知识点的学习记录
    static ArrayList<record>	exampleRecord;	    //	案例的学习记录
    //	知识点和案例都采用数组下标来标识

    static void setTopicSelect(int topicid, int idx, int select) {
        //      为topicid指定的知识点的第idx小节设置反馈选项
        if(topicid >= topicRecord.size()) {
            return;
        }
        record rd = topicRecord.get(topicid);
        if(idx < rd.select.size()) {
            rd.select.set(idx, new Integer(select));
            return;
        }
        for(int i=0; i<idx; i++) {
            if(rd.select.get(i) != null) {
                continue;
            }
            rd.select.set(i, new Integer(0));
        }
        rd.select.set(idx, new Integer(select));
    }

    static void setTopicTime(int id, int idx, double tm) {
        //      为id指定的知识点的第idx小节设置阅读时间
        if(id >= topicRecord.size()) {
            return;
        }
        record rd = topicRecord.get(id);
        if(idx < rd.times.size()) {
            rd.times.set(idx, new Double(tm));
            return;
        }
        for(int i=0; i<idx; i++) {
            if(rd.times.get(i) != null) {
                continue;
            }
            rd.times.set(i, new Double(tm));
        }
        rd.times.set(idx, new Double(tm));
    }

    static double getXiTopic(int topicid, List<Integer>words) {
        //  根据各个小节的字数、计算知识点topicid的学习系数
        if(topicid >= topicRecord.size()) {
            return 0.0;
        }
        double xi = 0.0;
        record rd = topicRecord.get(topicid);
        for(int i=0; i<words.size(); i++) {
            switch(rd.select.get(i).intValue()) {

                case 1:     //  掌握
                    xi += 1.0;
                    break;
                case 2:     //  了解
                    xi += 0.8;
                    break;
                case 3:     //  一知半解
                    xi += 0.4;
                    break;
                case 4:     //  困惑
                    xi += 0.1;
                    break;
                default:
                    xi += max(1.0, rd.times.get(i).intValue()/(words.get(i).intValue())/10.0);
                    break;
            }
        }
        Date d0 = new Date();
        Date d1 = rd.lasttime;
        return xi/words.size()*30/min(1.0,30.0/(d0.getDay()-d1.getDay()));
    }

    static void setExampleSelect(int id, int idx, int select) {
        //      为id指定案例的第idx小节设置反馈选项
        if(id >= topicRecord.size()) {
            return;
        }
        record rd = exampleRecord.get(id);
        if(idx < rd.select.size()) {
            rd.select.set(idx, new Integer(select));
            return;
        }
        for(int i=0; i<idx; i++) {
            if(rd.select.get(i) != null) {
                continue;
            }
            rd.select.set(i, new Integer(0));
        }
        rd.select.set(idx, new Integer(select));
    }

    static void setExampleTime(int id, int idx, double tm) {
        //      为id指定的案例的第idx小节设置阅读时间
        if(id >= exampleRecord.size()) {
            return;
        }
        record rd = exampleRecord.get(id);
        if(idx < rd.times.size()) {
            rd.times.set(idx, new Double(tm));
            return;
        }
        for(int i=0; i<idx; i++) {
            if(rd.times.get(i) != null) {
                continue;
            }
            rd.times.set(i, new Double(tm));
        }
        rd.times.set(idx, new Double(tm));
    }

    static double getXiExample(int exampleid, List<Integer>words) {
        //  根据各个小节的字数、计算案例exampleid的学习系数
        if(exampleid >= exampleRecord.size()) {
            return 0.0;
        }
        double xi = 0.0;
        record rd = exampleRecord.get(exampleid);
        for(int i=0; i<words.size(); i++) {
            switch(rd.select.get(i).intValue()) {

                case 1:     //  掌握
                    xi += 1.0;
                    break;
                case 2:     //  了解
                    xi += 0.8;
                    break;
                case 3:     //  一知半解
                    xi += 0.4;
                    break;
                case 4:     //  困惑
                    xi += 0.1;
                    break;
                default:
                    xi += max(1.0, rd.times.get(i).intValue()/(words.get(i).intValue()/10.0));
                    break;
            }
        }
        Date d0 = new Date();
        Date d1 = rd.lasttime;
        return xi/words.size()*30/min(1.0,30.0/(d0.getDay()-d1.getDay()));
    }
/*
       学习系数的计算
        令 Wij为小节字数，应用时间Wij/10秒，times[j]为实用时间，m为小节个数
        Xij = max(1, times[Tij] / (Wij/10))		无反馈时 j=1,...,m
        Xij = select[j]					有反馈时 j=1,...,m 	路过=无反馈
        合计	Xi = (Xi1 + ... + Xim)/m * min(1, 30/ (当前时间-lasttime天数)-
 */

    static double getXi(Object obj) {
        //  获得知识点或案例的学习系数
        if (obj instanceof Data4Mooc.Topic) {
            List<Data4Mooc.TNode> tNodes = LeappApplication.getMoocDataList().getSetTNodeList();
            Data4Mooc.Topic tpc = (Data4Mooc.Topic) obj;
            int idx = tNodes.indexOf(tpc);
            List<Integer> words = new ArrayList<Integer>();
            for (Data4Mooc.Section section : tpc.getSectionsList()) {
                int x = 0;
                for (Data4Mooc.Item item : section.getItemsList()) {
                    switch (item.getType()) {
                        case 0:             //  文本
                            x += item.getContent().length();
                            break;
                        case 3:
                            x += 10;        //  知识点链接
                            break;
                        default:            //  内外资源
                            x += 40;
                    }
                }
                words.add(Integer.valueOf(x));
            }
            return getXiTopic(idx, words);
        } else if (obj instanceof Data4Mooc.Example) {
            List<Data4Mooc.GNode> gNodes = LeappApplication.getMoocDataList().getSetGNodeList();
            Data4Mooc.Example exm = (Data4Mooc.Example) obj;
            int idx = gNodes.indexOf(exm);
            List<Integer> words = new ArrayList<Integer>();
            for (Data4Mooc.Section section : exm.getSectionsList()) {
                int x = 0;
                for (Data4Mooc.Item item : section.getItemsList()) {
                    switch (item.getType()) {
                        case 0:             //  文本
                            x += item.getContent().length();
                            break;
                        case 3:
                            x += 10;        //  知识点链接
                            break;
                        default:            //  内外资源
                            x += 40;
                    }
                }
                words.add(Integer.valueOf(x));
            }
            return getXiExample(idx, words);
        }
        return 0.0;
    }
}

