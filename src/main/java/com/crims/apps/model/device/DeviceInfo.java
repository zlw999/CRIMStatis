package com.crims.apps.model.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deviceid; //设备ID

    private String devicename; //设备名称

    private String groupname; //分组名称

    private String mappingdeviceid; //映射设备ID，预留

    private String deviceType; //设备辅类型，预留

    private String devicemtype; //设备主类型，预留

    private String version; //版本

    private String factoryid; //厂家ID，数据字典M017

    private String location; //安装位置

    private Integer posex; //x坐标

    private Integer posey; //y坐标

    private String longitude; //经度

    private String latitude; //维度

    private String altitude; //海拔高度

    private Integer isLockedXY; //是否锁定位置

    private String mac; //MAC地址

    private String ipaddress; //IP地址

    private String subnetmask; //子网掩码

    private String gateway; //网关

    private Integer lisport; //端口

    private Integer channelnum; //通道数量

    private String dsp; //描述

    private String loginuser; //登录用户

    private String password; //登录密码

    private String softwarever; //软件版本

    private String hardwarever; //硬件版本

    private String managerprotocol; //管理协议

    private Integer snmpversion; //SNMP版本

    private Integer snmpport; //SNMP端口

    private String readcommit; //读共同体

    private String writecommit; // 写共同体

    private String alarmrate; //告警概率

    private Integer isenablesnmpproxy; //是否启用SNMP代理

    private String snmpproxyip; //SNMP代理IP

    private Integer systemid; //预留

    private String systemoid; //系统OID信息

    private String systemdesc; //系统描述

    private String snmpv3username; //SNMP V3协议用户名

    private Integer snmpv3securitylevel; //SNMP V3协议安全等级

    private String snmpv3authprotocol; //SNMP V3协议认证(&加密)协议

    private String snmpv3contextname; //SNMP V3协议上下文引擎

    private String srcnodeid; //管理服务ID,预留

    private Integer mainstreamframerate; //预留

    private Integer mainstreamgop; //预留

    private Integer usestate; //使用状态，数据字典

    private Integer operateaction; //更新类型，数据字典

    private String operateuserid; //更新用户ID

    private String operateusername; //更新用户名称

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModifyTime; //最后更新时间

    private Integer startcno; //起始通道号

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public Integer getSystemid() {
        return systemid;
    }

    public void setSystemid(Integer systemid) {
        this.systemid = systemid;
    }

    public String getMappingdeviceid() {
        return mappingdeviceid;
    }

    public void setMappingdeviceid(String mappingdeviceid) {
        this.mappingdeviceid = mappingdeviceid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevicemtype() {
        return devicemtype;
    }

    public void setDevicemtype(String devicemtype) {
        this.devicemtype = devicemtype;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getSubnetmask() {
        return subnetmask;
    }

    public void setSubnetmask(String subnetmask) {
        this.subnetmask = subnetmask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public Integer getLisport() {
        return lisport;
    }

    public void setLisport(Integer lisport) {
        this.lisport = lisport;
    }

    public Integer getChannelnum() {
        return channelnum;
    }

    public void setChannelnum(Integer channelnum) {
        this.channelnum = channelnum;
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp;
    }

    public String getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(String loginuser) {
        this.loginuser = loginuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSoftwarever() {
        return softwarever;
    }

    public void setSoftwarever(String softwarever) {
        this.softwarever = softwarever;
    }

    public String getHardwarever() {
        return hardwarever;
    }

    public void setHardwarever(String hardwarever) {
        this.hardwarever = hardwarever;
    }

    public String getSrcnodeid() {
        return srcnodeid;
    }

    public void setSrcnodeid(String srcnodeid) {
        this.srcnodeid = srcnodeid;
    }

    public Integer getMainstreamframerate() {
        return mainstreamframerate;
    }

    public void setMainstreamframerate(Integer mainstreamframerate) {
        this.mainstreamframerate = mainstreamframerate;
    }

    public Integer getMainstreamgop() {
        return mainstreamgop;
    }

    public void setMainstreamgop(Integer mainstreamgop) {
        this.mainstreamgop = mainstreamgop;
    }

    public Integer getUsestate() {
        return usestate;
    }

    public void setUsestate(Integer usestate) {
        this.usestate = usestate;
    }

    public Integer getOperateaction() {
        return operateaction;
    }

    public void setOperateaction(Integer operateaction) {
        this.operateaction = operateaction;
    }

    public String getOperateuserid() {
        return operateuserid;
    }

    public void setOperateuserid(String operateuserid) {
        this.operateuserid = operateuserid;
    }

    public String getOperateusername() {
        return operateusername;
    }

    public void setOperateusername(String operateusername) {
        this.operateusername = operateusername;
    }

    public Integer getStartcno() {
        return startcno;
    }

    public void setStartcno(Integer startcno) {
        this.startcno = startcno;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

}