package com.crims.apps.model.maintainlog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecMaintainlog implements Serializable {
    private static final long serialVersionUID = 1L;
    private String indexno;
    private String title;
    private String deviceid;
    private String devicename;
    private String devicetype;
    private String faulttype;
    private String faultdsp;
    private String faultreason;
    private String solution;
    private Integer hasDoc;
    private String maintainuserid;
    private String maintainuser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maintaintime;
    private String reviewuserid;
    private String reviewuser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewtime;
    private String reviewdsp;
    private Float score;
    //返回的设备编号
    private String device;
    //返回的告警类型
    private String item;
    private String itemChild;
    //返回的路径名
    private String documentpath;
    //返回的文件名
    private String documentname;
    private String devicetypename;

    //区域
    private String region;
    //线路
    private String line;
    //车站
    private String station;
    //查询时候的 开始时间 结束时间 用户名 节点
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;
    private String nodeid;

    private Integer counts;
    private Float avgscore;

    //统计的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private String userid;

}
