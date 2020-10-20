package com.crims.apps.dao.device;

import com.crims.apps.model.device.DeviceFault;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DeviceFaultDao {

    List<DeviceFault> getDeviceFaultByDate(Date date);

    DeviceFault getDeviceFaultInfo(DeviceFault deviceFault);

    int insertDeviceFault(DeviceFault deviceFault);

    int updateDeviceFault(DeviceFault deviceFault);
}