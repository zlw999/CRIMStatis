package com.crims.apps.service.device;

import com.crims.apps.model.device.DeviceInfo;

import java.util.List;

public interface DeviceInfoService {

  	/**
  	 * 查询所有设备信息
     * @return List<DeviceInfo>
     */
	List<DeviceInfo> getDeviceInfoList();

    /**
     * 根据设备查询设备厂商
     * @param deviceID
     * @return DeviceInfo
     */
    DeviceInfo getDeviceInfo(String deviceID);

}
