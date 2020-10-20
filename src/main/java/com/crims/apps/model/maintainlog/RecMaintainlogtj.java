package com.crims.apps.model.maintainlog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.print.PrinterGraphics;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecMaintainlogtj implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String deviceid;
    private String userid;
    private String username;
    private String devicename;
    private Integer maintainnum;
    private String PeriodFlag;
    private String periodinfo;
    private Float avgscore;
    private String dsp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tjtime;
    //区域
    private String region;
    //线路
    private String line;
    //车站
    private String station;

    //查询的id did
    private String did;

    //日起开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;
    //日期结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;
    private Integer counts;

}
