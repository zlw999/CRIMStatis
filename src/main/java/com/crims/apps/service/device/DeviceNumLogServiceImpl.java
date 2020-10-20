package com.crims.apps.service.device;

import com.crims.apps.dao.device.DeviceNumLogDao;
import com.crims.apps.model.device.DeviceNumLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceNumLogServiceImpl implements DeviceNumLogService{

    @Autowired
    private DeviceNumLogDao deviceNumLogDao;

    @Override
    public List<DeviceNumLog> getDeviceNumLogByDate(Date date) {
        return deviceNumLogDao.getDeviceNumLogByDate(date);
    }

    @Override
    public int insert(DeviceNumLog deviceNumLog) {
        return deviceNumLogDao.insert(deviceNumLog);
    }

    @Override
    public int update(DeviceNumLog deviceNumLog) {
        return deviceNumLogDao.update(deviceNumLog);
    }
}
