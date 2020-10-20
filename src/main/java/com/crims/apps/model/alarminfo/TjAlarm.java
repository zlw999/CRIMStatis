package com.crims.apps.model.alarminfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TjAlarm implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String areaid;
    private String areaname;
    private String lineid;
    private String linename;
    private String stationid;
    private String stationname;
    private String deviceid;
    private String devicename;
    private String alarmmaintype;
    private String alarmsubtype;
    private String alarmlevel;
    private Integer devicenum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date TJdate;
    private String PeriodFlag;
    private String periodinfo;

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
    private String dsp;
    private String typename;
    private String AlarmType;
    private Integer countsg;
}
