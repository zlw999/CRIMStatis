package com.crims.apps.controller.device;

import com.crims.apps.model.alarminfo.AlarmInfoHis;
import com.crims.apps.model.device.DeviceFault;
import com.crims.apps.model.device.DeviceInfo;
import com.crims.apps.service.alarminfo.AlarmInfoHisService;
import com.crims.apps.service.ddman.DDManService;
import com.crims.apps.service.device.DeviceFaultService;
import com.crims.apps.service.device.DeviceInfoService;
import com.crims.apps.service.node.NodeConfService;
import com.crims.common.constants.BaseConst;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/devicefault")
public class DeviceFaultController {

    public static Logger logger = LoggerFactory.getLogger(DeviceFaultController.class);

    @Autowired
    private DeviceFaultService deviceFaultService;

    @Autowired
    private DDManService ddManService;

    @Autowired
    private NodeConfService nodeConfService;

    @Autowired
    private AlarmInfoHisService alarmInfoHisService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    /**
     * 统计查询设备故障数量
     * @param currentPage
     * @param pageSize
     * @param date
     * @return PageInfo<DeviceFault>
     */
    @PostMapping("/getDeviceFaultList")
    @ResponseBody
    @ApiOperation(value = "统计查询设备故障数量", notes = "devicefault_info")
    public PageInfo<DeviceFault> getDeviceFaultList(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                  @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                                  String loginUserID, String loginUserName) {
        try {

            PageInfo<DeviceFault> pageList = deviceFaultService.getDeviceFaultByDate(date,currentPage,pageSize);

            List<DeviceFault> deviceFaultList = pageList.getList();

            if(deviceFaultList == null || deviceFaultList.isEmpty()){
                Date now = new Date(); //当前时间
                Date befnow = new Date();
                Calendar calendar = Calendar.getInstance();//得到日历
                calendar.setTime(now);//把当前时间赋给日历
                calendar.add(Calendar.DAY_OF_MONTH, -1);//设置为前一天
                befnow = calendar.getTime();//得到前一天的时间
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//设置时间格式
                String startDateStr = sdf.format(befnow);//格式化前一天
                startDateStr = startDateStr+" 00:00:00";
                String endDateStr = startDateStr.substring(0,10)+" 23:59:59";
                Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateStr);
                Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateStr);

                List<AlarmInfoHis> alarmInfoHisList = alarmInfoHisService.getAlarmInfoHisList(startDate,endDate);
                if(alarmInfoHisList !=null && !alarmInfoHisList.isEmpty()){
                    for (int i = 0; i < alarmInfoHisList.size(); i++) {

                        DataSourceContextHolder.setKey("crimsdbs");

                        String areaid = alarmInfoHisList.get(i).getDeviceid().substring(0,2);//区域编号
                        String areaName = ddManService.getItemName(BaseConst.AREANODEID,areaid);//区域名称
                        String lineid = alarmInfoHisList.get(i).getDeviceid().substring(0,4);//线路编号
                        String lineName = nodeConfService.getNodeName(lineid);//线路名称
                        String stationid = alarmInfoHisList.get(i).getDeviceid().substring(0,6);//车站编号
                        String stationName = nodeConfService.getNodeName(stationid);//车站名称
                        String dmtype = alarmInfoHisList.get(i).getDeviceid().substring(6,8);//设备主类型
                        String dstype = alarmInfoHisList.get(i).getDeviceid().substring(8,10);//设备子类型
                        String alarmMtype = alarmInfoHisList.get(i).getAlarmtype().substring(0,3);//告警主类型
                        String alarmStype = alarmInfoHisList.get(i).getAlarmtype().substring(3,5);//告警子类型
                        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(alarmInfoHisList.get(i).getDeviceid()) ;
                        String factoryid = deviceInfo.getFactoryid();//厂家编号
                        String factoryName = ddManService.getItemName(BaseConst.FACTROYNODEID,factoryid);//厂商名称

                        DataSourceContextHolder.clearKey();

                        DeviceFault deviceFault = new DeviceFault();
                        deviceFault.setAreaid(areaid);
                        deviceFault.setAreaname(areaName);
                        deviceFault.setLineid(lineid);
                        deviceFault.setLinename(lineName);
                        deviceFault.setStationid(stationid);
                        deviceFault.setStationname(stationName);
                        deviceFault.setFactoryid(factoryid);
                        deviceFault.setFactoryname(factoryName);
                        deviceFault.setDmtype(dmtype);
                        deviceFault.setDstype(dstype);
                        deviceFault.setAlarmstype(alarmStype);
                        deviceFault.setAlarmmtype(alarmMtype);
                        deviceFault.setTjdate(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));

                        DeviceFault deviceFaultInfo = deviceFaultService.getDeviceFaultInfo(deviceFault);
                        int num = 1;
                        if(deviceFaultInfo != null){
                            num = deviceFaultInfo.getDevicenum();
                            deviceFaultInfo.setDevicenum(num++);
                            deviceFaultInfo.setTjdate(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
                            deviceFaultList.add(deviceFaultInfo);
                            deviceFaultService.updateDeviceFault(deviceFaultInfo);
                        }else{
                            deviceFault.setDevicenum(num);
                            deviceFaultList.add(deviceFault);
                            deviceFaultService.insertDeviceFault(deviceFault);
                        }

                    }
                }
            }

            return pageList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
