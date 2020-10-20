package com.crims.apps.service.device;

import com.crims.apps.model.device.DeviceFault;
import com.github.pagehelper.PageInfo;

import java.util.Date;

public interface DeviceFaultService {

    PageInfo<DeviceFault> getDeviceFaultByDate(Date date, int currentPage, int pageSize);

    DeviceFault getDeviceFaultInfo(DeviceFault deviceFault);

    int insertDeviceFault(DeviceFault deviceFault);

    int updateDeviceFault(DeviceFault deviceFault);

}
