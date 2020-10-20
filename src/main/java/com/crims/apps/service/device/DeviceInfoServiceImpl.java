package com.crims.apps.service.device;

import com.crims.apps.dao.device.DeviceInfoDao;
import com.crims.apps.model.device.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

	@Autowired
	private DeviceInfoDao deviceInfoDao;

    /**
     * 查询所有设备信息
     * @return List<DeviceInfo>
     */
	@Override
	public List<DeviceInfo> getDeviceInfoList() {
		return deviceInfoDao.getDeviceInfoList();
	}

    @Override
    public DeviceInfo getDeviceInfo(String deviceID) {
        return deviceInfoDao.getDeviceInfo(deviceID);
    }
}
