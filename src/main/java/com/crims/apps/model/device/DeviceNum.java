package com.crims.apps.model.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class DeviceNum implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String areaid;

    private String areaname;

    private String lineid;

    private String linename;

    private String stationid;

    private String stationname;

    private String factoryid;

    private String factoryname;

    private String dmtype;

    private String dstype;

    private Integer devicenum;

    private String dmtypeName;

    private String dstypeName;

    private String statisflag;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date tjdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid == null ? null : factoryid.trim();
    }

    public String getFactoryname() {
        return factoryname;
    }

    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname == null ? null : factoryname.trim();
    }

    public String getDmtype() {
        return dmtype;
    }

    public void setDmtype(String dmtype) {
        this.dmtype = dmtype == null ? null : dmtype.trim();
    }

    public String getDstype() {
        return dstype;
    }

    public void setDstype(String dstype) {
        this.dstype = dstype == null ? null : dstype.trim();
    }

    public Integer getDevicenum() {
        return devicenum;
    }

    public void setDevicenum(Integer devicenum) {
        this.devicenum = devicenum;
    }

    public Date getTjdate() {
        return tjdate;
    }

    public void setTjdate(Date tjdate) {
        this.tjdate = tjdate;
    }

    public String getDmtypeName() {
        return dmtypeName;
    }

    public void setDmtypeName(String dmtypeName) {
        this.dmtypeName = dmtypeName;
    }

    public String getDstypeName() {
        return dstypeName;
    }

    public void setDstypeName(String dstypeName) {
        this.dstypeName = dstypeName;
    }

    public String getStatisflag() {
        return statisflag;
    }

    public void setStatisflag(String statisflag) {
        this.statisflag = statisflag;
    }
}