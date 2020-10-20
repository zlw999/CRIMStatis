package com.crims.apps.service.device;

import com.crims.apps.dao.device.DeviceFaultDao;
import com.crims.apps.model.device.DeviceFault;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceFaultServiceImpl implements DeviceFaultService{

    @Autowired
    private DeviceFaultDao deviceFaultDao;

    @Override
    public PageInfo<DeviceFault> getDeviceFaultByDate(Date date, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<DeviceFault> pageList = deviceFaultDao.getDeviceFaultByDate(date);
        PageInfo<DeviceFault> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public DeviceFault getDeviceFaultInfo(DeviceFault deviceFault) {
        return deviceFaultDao.getDeviceFaultInfo(deviceFault);
    }

    @Override
    public int insertDeviceFault(DeviceFault deviceFault) {
        return deviceFaultDao.insertDeviceFault(deviceFault);
    }

    @Override
    public int updateDeviceFault(DeviceFault deviceFault) {
        return deviceFaultDao.updateDeviceFault(deviceFault);
    }
}
