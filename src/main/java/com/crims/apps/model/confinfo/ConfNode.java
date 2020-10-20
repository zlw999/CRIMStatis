package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nodeid; //节点编码

    private String nodename; //节点名称

    private int usestate; //使用状态

    private int operateaction; //当前操作

    private String operateuserid; //操作用户Id

    private String operateusername; //操作用户名称

    private String nodeidarea; //区域节点

    private String nodeidone; //一类节点

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime;//最后修改时间

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public int getUsestate() {
        return usestate;
    }

    public void setUsestate(int usestate) {
        this.usestate = usestate;
    }

    public int getOperateaction() {
        return operateaction;
    }

    public void setOperateaction(int operateaction) {
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

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getNodeidarea() {
        return nodeidarea;
    }

    public void setNodeidarea(String nodeidarea) {
        this.nodeidarea = nodeidarea;
    }

    public String getNodeidone() {
        return nodeidone;
    }

    public void setNodeidone(String nodeidone) {
        this.nodeidone = nodeidone;
    }
}
