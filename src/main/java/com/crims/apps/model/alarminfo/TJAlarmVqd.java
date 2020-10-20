package com.crims.apps.model.alarminfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class TJAlarmVqd implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String vqdsrvid;

    private Integer planvqdnum;

    private Integer factvqdnum;

    private String alarmtype;

    private Integer alarmnum;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date vqddate;

    private String areaid;

    private String areaname;

    private String lineid;

    private String linename;

    private String stationid;

    private String stationname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVqdsrvid() {
        return vqdsrvid;
    }

    public void setVqdsrvid(String vqdsrvid) {
        this.vqdsrvid = vqdsrvid == null ? null : vqdsrvid.trim();
    }

    public Integer getPlanvqdnum() {
        return planvqdnum;
    }

    public void setPlanvqdnum(Integer planvqdnum) {
        this.planvqdnum = planvqdnum;
    }

    public Integer getFactvqdnum() {
        return factvqdnum;
    }

    public void setFactvqdnum(Integer factvqdnum) {
        this.factvqdnum = factvqdnum;
    }

    public String getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype == null ? null : alarmtype.trim();
    }

    public Integer getAlarmnum() {
        return alarmnum;
    }

    public void setAlarmnum(Integer alarmnum) {
        this.alarmnum = alarmnum;
    }

    public Date getVqddate() {
        return vqddate;
    }

    public void setVqddate(Date vqddate) {
        this.vqddate = vqddate;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid == null ? null : lineid.trim();
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename == null ? null : linename.trim();
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid == null ? null : stationid.trim();
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname == null ? null : stationname.trim();
    }
}