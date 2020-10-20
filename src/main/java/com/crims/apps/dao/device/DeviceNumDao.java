package com.crims.apps.dao.device;

import com.crims.apps.model.device.DeviceNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DeviceNumDao {

    List<DeviceNum> getDeviceNumByDate(Date date,String statisflag);

    List<DeviceNum> getDeviceNumList(DeviceNum deviceNum);

    List<DeviceNum> getDeviceNumSelect(DeviceNum deviceNum);

    List<DeviceNum> getAll();

    int insertDeviceNum(DeviceNum deviceNum);

    int updateDeviceNum(DeviceNum deviceNum);

    int deleteDeviceNum(Date date,String statisflag);
}
