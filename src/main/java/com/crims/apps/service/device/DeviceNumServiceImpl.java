package com.crims.apps.service.device;

import com.crims.apps.dao.confinfo.DDManDao;
import com.crims.apps.dao.confinfo.NodeConfDao;
import com.crims.apps.dao.device.DeviceInfoDao;
import com.crims.apps.dao.device.DeviceNumDao;
import com.crims.apps.model.device.DeviceNum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceNumServiceImpl implements DeviceNumService{

    @Autowired
    private DeviceNumDao deviceNumDao;

    @Autowired
    private DDManDao ddManDao;

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Autowired
    private NodeConfDao nodeConfDao;

    @Override
    public PageInfo<DeviceNum> getDeviceNumByDate(Date date,int currentPage,int pageSize,String statisflag) {
        PageHelper.startPage(currentPage, pageSize);
        List<DeviceNum> pageList = deviceNumDao.getDeviceNumByDate(date,statisflag);
        PageInfo<DeviceNum> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<DeviceNum> getDeviceNumSelect(int currentPage, int pageSize, DeviceNum deviceNum) {
        PageHelper.startPage(currentPage, pageSize);
        List<DeviceNum> pageList = deviceNumDao.getDeviceNumList(deviceNum);
        PageInfo<DeviceNum> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public List<DeviceNum> getDeviceNumInfo(DeviceNum deviceNum) {
        return deviceNumDao.getDeviceNumList(deviceNum);
    }

    @Override
    public List<DeviceNum> getDeviceNumSelect(DeviceNum deviceNum) {
        return deviceNumDao.getDeviceNumSelect(deviceNum);
    }

    @Override
    public List<DeviceNum> getAll() {
        return deviceNumDao.getAll();
    }

    @Override
    public int insertDeviceNum(DeviceNum deviceNum) {
        return deviceNumDao.insertDeviceNum(deviceNum);
    }

    @Override
    public int updateDeviceNum(DeviceNum deviceNum) {
        return deviceNumDao.updateDeviceNum(deviceNum);
    }

    @Override
    public int deleteDeviceNum(Date date,String statisflag) {
        return deviceNumDao.deleteDeviceNum(date,statisflag);
    }


}
