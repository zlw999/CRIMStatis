package com.crims.apps.model.nettop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfEnum implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id; //标识

    //private String userId; //用户编码

    //private String userName; //用户名称

    private String name; //属性名称

    private String itemValue; //项目值

    private String itemName; //项目名称

    private double countTrainee; //排序

    private String abbreviate; //字典项缩写名

    private String dsp;
}
