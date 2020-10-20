package com.crims.apps.model.state;

public class FaultlevelStatInfoVO {
    private String lineId		= "";		// 线路编号
    private String lineName	= "";		// 线路名称
    private String stationId	= "";		// 车站编号
    private String stationName	= "";		// 车站名称

    private int onelevelNum	= 0;		// 一级告警数
    private int twolevelNum	= 0;		// 二级告警数

    private int threelevelNum	= 0;		// 三级告警数

    private int fourlevelNum	= 0;		// 四级告警数

    private int otherlevelNum	= 0;		// 其它级别告警数


    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getOnelevelNum() {
        return onelevelNum;
    }

    public void setOnelevelNum(int onelevelNum) {
        this.onelevelNum = onelevelNum;
    }

    public int getTwolevelNum() {
        return twolevelNum;
    }

    public void setTwolevelNum(int twolevelNum) {
        this.twolevelNum = twolevelNum;
    }

    public int getThreelevelNum() {
        return threelevelNum;
    }

    public void setThreelevelNum(int threelevelNum) {
        this.threelevelNum = threelevelNum;
    }

    public int getFourlevelNum() {
        return fourlevelNum;
    }

    public void setFourlevelNum(int fourlevelNum) {
        this.fourlevelNum = fourlevelNum;
    }

    public int getOtherlevelNum() {
        return otherlevelNum;
    }

    public void setOtherlevelNum(int otherlevelNum) {
        this.otherlevelNum = otherlevelNum;
    }
}
