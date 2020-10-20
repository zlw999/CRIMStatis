package com.crims.apps.model.nettop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfNodearea implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nodeid;
    private String nodename;
    private Integer usestate;
    private Integer operateaction;
    private String operateuserid;
    private String operateusername;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastmodifytime;
}
