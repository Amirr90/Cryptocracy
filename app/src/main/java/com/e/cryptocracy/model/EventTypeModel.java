package com.e.cryptocracy.model;

import java.util.List;

public class EventTypeModel {
    int count;
    List<String> data;

    @Override
    public String toString() {
        return "EventModel{" +
                "count=" + count +
                ", data=" + data +
                '}';
    }

    public int getCount() {
        return count;
    }

    public List<String> getData() {
        return data;
    }
}
