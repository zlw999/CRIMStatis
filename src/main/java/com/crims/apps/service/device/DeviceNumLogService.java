package com.crims.apps.service.device;

import com.crims.apps.model.device.DeviceNumLog;

import java.util.Date;
import java.util.List;

public interface DeviceNumLogService {

    int insert(DeviceNumLog deviceNumLog);

    int update(DeviceNumLog deviceNumLog);

    List<DeviceNumLog> getDeviceNumLogByDate(Date date);
}
