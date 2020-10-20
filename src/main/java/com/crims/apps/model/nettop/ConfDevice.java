package com.crims.apps.model.nettop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfDevice implements Serializable {
    private static final long serialVersionUID = 1L;
    private String deviceid;
    private String devicename;
    private String groupname;
    private String mappingdeviceid;
    private String deviceType;
    private String devicemtype;
    private String version;
    private String factoryid;
    private String location;
    private Integer posex;
    private Integer posey;
    private String longitude;
    private String latitude;
    private String altitude;
    private Integer isLockedXY;
    private String mac;
    private String ipaddress;
    private String dsp;
}
