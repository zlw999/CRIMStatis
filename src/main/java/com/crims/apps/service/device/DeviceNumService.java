package com.crims.apps.service.device;

import com.crims.apps.model.device.DeviceNum;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface DeviceNumService {

    PageInfo<DeviceNum> getDeviceNumByDate(Date date, int currentPage, int pageSize,String statisflag);

    PageInfo<DeviceNum> getDeviceNumSelect(int currentPage,int pageSize,DeviceNum deviceNum);

    List<DeviceNum> getDeviceNumInfo(DeviceNum deviceNum);

    List<DeviceNum> getDeviceNumSelect(DeviceNum deviceNum);

    List<DeviceNum> getAll();

    int insertDeviceNum(DeviceNum deviceNum);

    int updateDeviceNum(DeviceNum deviceNum);

    int deleteDeviceNum(Date date,String statisflag);
}
