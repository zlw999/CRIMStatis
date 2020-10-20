package com.crims.apps.model.confinfo;

import java.io.Serializable;

public class ConfEnum implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id; //标识

    private String userId; //用户编码

    private String userName; //用户名称

    private String name; //属性名称

    private String item_value; //项目值

    private String item_name; //项目名称

    private double count_trainee; //排序

    private String abbreviate; //字典项缩写名

    private String dsp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getCount_trainee() {
        return count_trainee;
    }

    public void setCount_trainee(double count_trainee) {
        this.count_trainee = count_trainee;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp;
    }
}
