package com.crims.apps.model.nettop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfNettop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 拓扑图ID
     */

    private int topid;

    /**
     * 拓扑图名称
     */
    private String topname;

    /**
     * XML格式数据
     */
    private byte[] topdata;

    /**
     * 使用状态，数据字典(*)
     */
    private Integer usestate;

    /**
     * 更新类型，数据字典(*)
     */
    private Integer operateaction;

    /**
     * 更新用户ID(*)
     */
    private String operateuserid;

    /**
     * 更新用户名称(*)
     */
    private String operateusername;

    /**
     * 最后更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifyTime;
    /**
     * 父id(*)
     */
    private int parentid;
    /**
     * 排序id(*)
     */
    private int sortid;
    private List<ConfNettop> children;
    //查询要变得节点id
    private int id;
    private String xml;


}
