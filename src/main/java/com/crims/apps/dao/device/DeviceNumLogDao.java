package com.crims.apps.dao.device;

import com.crims.apps.model.device.DeviceNumLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DeviceNumLogDao {

    List<DeviceNumLog> getDeviceNumLogByDate(Date date);

    int insert(DeviceNumLog deviceNumLog);

    int update(DeviceNumLog deviceNumLog);

}
