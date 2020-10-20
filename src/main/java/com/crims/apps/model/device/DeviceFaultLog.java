package com.crims.apps.model.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class DeviceFaultLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date date;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startdate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date enddate;

    private String userid;

    private String username;

    private String dsp;

    private String periodflag;

    private String periodinfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp == null ? null : dsp.trim();
    }

    public String getPeriodflag() {
        return periodflag;
    }

    public void setPeriodflag(String periodflag) {
        this.periodflag = periodflag == null ? null : periodflag.trim();
    }

    public String getPeriodinfo() {
        return periodinfo;
    }

    public void setPeriodinfo(String periodinfo) {
        this.periodinfo = periodinfo == null ? null : periodinfo.trim();
    }
}