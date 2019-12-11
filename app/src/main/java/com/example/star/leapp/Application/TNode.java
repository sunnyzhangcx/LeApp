package com.example.star.leapp.Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import data4mooc.Data4Mooc;

public class TNode implements Serializable {
    private Data4Mooc.Topic topic;
    private ArrayList<Integer> child;

    public TNode(Data4Mooc.Topic topic,ArrayList<Integer> child){
        this.topic= topic;
        this.child = child;
    }

    public void setTopic(Data4Mooc.Topic topic) {
        this.topic = topic;
    }

    public void setChild(ArrayList<Integer> child) {
        this.child = child;
    }

    public Data4Mooc.Topic getTopic() {
        return topic;
    }

    public ArrayList<Integer> getChild() {
        return child;
    }
}
