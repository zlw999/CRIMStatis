package com.crims.apps.dao.device;

import com.crims.apps.model.device.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceInfoDao {

    /**
     * 查询所有设备信息
     *
     * @return List<DeviceInfo>
     */
    List<DeviceInfo> getDeviceInfoList();

    /**
     * 根据设备查询设备厂商
    *  @param deviceID
     * @return DeviceInfo
     */
    DeviceInfo getDeviceInfo(String deviceID);

}

