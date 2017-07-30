package com.yogeesh.datastructures.common;

/**
 * Created by yogeesh.srkvs@gmail.com on 7/29/17.
 */
public class Data {
    int info;

    // Constructor
    public Data() {
        info = -1;
    }

    // Overrided Constructor
    public Data(int info) {
        this.info = info;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Data{" +
                "info='" + info + '\'' +
                '}';
    }

}
