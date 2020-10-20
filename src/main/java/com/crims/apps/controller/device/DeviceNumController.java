package com.crims.apps.controller.device;

import com.crims.apps.model.device.DeviceInfo;
import com.crims.apps.model.device.DeviceNum;
import com.crims.apps.model.device.DeviceNumLog;
import com.crims.apps.service.ddman.DDManService;
import com.crims.apps.service.device.DeviceInfoService;
import com.crims.apps.service.device.DeviceNumLogService;
import com.crims.apps.service.device.DeviceNumService;
import com.crims.apps.service.node.NodeConfService;
import com.crims.common.constants.BaseConst;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.PageHelper;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/devicenum")
public class DeviceNumController {

    public static Logger logger = LoggerFactory.getLogger(DeviceNumController.class);

    @Autowired
    private DeviceNumService deviceNumService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private DDManService ddManService;

    @Autowired
    private NodeConfService nodeConfService;

    @Autowired
    private DeviceNumLogService deviceNumLogService;

    /**
     * 页面初始化统计查询设备数量
     * @param currentPage
     * @param pageSize
     * @param date
     * @return PageInfo<DeviceNum>
     */
    @PostMapping("/getDeviceNumList")
    @ResponseBody
    @ApiOperation(value = "统计查询设备数量(页面初始化加载)", notes = "devicenum_info")
    public PageInfo<DeviceNum> getDeviceNumList(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                                String loginUserID,String loginUserName) {
        try {

            deviceNumService.deleteDeviceNum(date,"1");

            DataSourceContextHolder.setKey("crimsdbs");

            List<DeviceInfo> deviceInfoList = deviceInfoService.getDeviceInfoList();

            DataSourceContextHolder.clearKey();

            if(deviceInfoList != null && !deviceInfoList.isEmpty()){

                for (int i = 0; i < deviceInfoList.size(); i++) {

                    DataSourceContextHolder.setKey("crimsdbs");

                    String areaid = deviceInfoList.get(i).getDeviceid().substring(0,2);//区域编号
                    String areaName = ddManService.getItemName(BaseConst.AREANODEID,areaid);//区域名称
                    String lineid = deviceInfoList.get(i).getDeviceid().substring(0,4);//线路编号
                    String lineName = nodeConfService.getNodeName(lineid);//线路名称
                    String stationid = deviceInfoList.get(i).getDeviceid().substring(0,6);//车站编号
                    String stationName = nodeConfService.getNodeName(stationid);//车站名称
                    String dmtype = deviceInfoList.get(i).getDeviceid().substring(6,8);//设备主类型
                    String dstype = deviceInfoList.get(i).getDeviceid().substring(8,10);//设备子类型
                    String factoryid = deviceInfoList.get(i).getFactoryid();//厂家编号
                    String factoryName = ddManService.getItemName(BaseConst.FACTROYNODEID,factoryid);//厂商名称

                    DataSourceContextHolder.clearKey();

                    DeviceNum deviceNum = new DeviceNum();
                    deviceNum.setAreaid(areaid);
                    deviceNum.setAreaname(areaName);
                    deviceNum.setLineid(lineid);
                    deviceNum.setLinename(lineName);
                    deviceNum.setStationid(stationid);
                    deviceNum.setStationname(stationName);
                    deviceNum.setDmtype(dmtype);
                    deviceNum.setDstype(dstype);
                    deviceNum.setFactoryid(factoryid);
                    deviceNum.setFactoryname(factoryName);
                    deviceNum.setTjdate(date);
                    deviceNum.setStatisflag("1");
                    List<DeviceNum> deviceNumLists = deviceNumService.getDeviceNumInfo(deviceNum);
                    int num = 1;
                    if(deviceNumLists != null && !deviceNumLists.isEmpty()){
                        DeviceNum deviceNumInfo = deviceNumLists.get(0);
                        num = deviceNumInfo.getDevicenum();
                        num ++;
                        deviceNumInfo.setDevicenum(num);
                        deviceNumInfo.setTjdate(date);
                        deviceNumService.updateDeviceNum(deviceNumInfo);
                    }else{
                        deviceNum.setDevicenum(num);
                        deviceNumService.insertDeviceNum(deviceNum);
                    }
                }

                List<DeviceNumLog> list = deviceNumLogService.getDeviceNumLogByDate(date);
                if(list == null || list.isEmpty()){
                    DeviceNumLog deviceNumLog = new DeviceNumLog();
                    deviceNumLog.setDsp("");
                    deviceNumLog.setDate(date);
                    deviceNumLog.setUserid(loginUserID);
                    deviceNumLog.setUsername(loginUserName);
                    deviceNumLogService.insert(deviceNumLog);
                }
            }

            PageInfo<DeviceNum> pageList = deviceNumService.getDeviceNumByDate(date,currentPage,pageSize,"1");
            return pageList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 按条件统计设备数量
     * @param currentPage
     * @param pageSize
     * @param deviceNum
     * @return PageInfo<DeviceNum>
     */
    @PostMapping("/getDeviceNumInfo")
    @ResponseBody
    @ApiOperation(value = "按条件统计设备数量（下拉列表控制列的显隐）", notes = "devicenum_info")
    public PageInfo<DeviceNum> getDeviceNumInfo(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                DeviceNum deviceNum) {
        try {

            boolean flag = true;

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            deviceNumService.deleteDeviceNum(date,"2");

            PageHelper.startPage(currentPage, pageSize);

            String areaID = deviceNum.getAreaid();

            String lineID = deviceNum.getLineid();

            String stationID = deviceNum.getStationid();

            String factoryID = deviceNum.getFactoryid();

            String dmtype = deviceNum.getDmtype();

            String dstype = deviceNum.getDstype();

            DataSourceContextHolder.setKey("crimsdbs");

            List<DeviceInfo> deviceInfoList = deviceInfoService.getDeviceInfoList();

            DataSourceContextHolder.clearKey();

            for (int i = 0; i < deviceInfoList.size(); i++) {

                DataSourceContextHolder.setKey("crimsdbs");

                DeviceNum dn = new DeviceNum();

                if("QY".equals(areaID)){
                    String areaid = deviceInfoList.get(i).getDeviceid().substring(0,2);//区域编号
                    String areaName = ddManService.getItemName(BaseConst.AREANODEID,areaid);//区域名称
                    dn.setAreaid(areaid);
                    dn.setAreaname(areaName);
                }
                if("XL".equals(lineID)){
                    String lineid = deviceInfoList.get(i).getDeviceid().substring(0,4);//线路编号
                    String lineName = nodeConfService.getNodeName(lineid);//线路名称
                    dn.setLineid(lineid);
                    dn.setLinename(lineName);
                }
                if("CZ".equals(stationID)){
                    String stationid = deviceInfoList.get(i).getDeviceid().substring(0,6);//车站编号
                    String stationName = nodeConfService.getNodeName(stationid);//车站名称
                    dn.setStationid(stationid);
                    dn.setStationname(stationName);
                }
                if("CS".equals(factoryID)){
                    String factoryid = deviceInfoList.get(i).getFactoryid();//厂家编号
                    String factoryName = ddManService.getItemName(BaseConst.FACTROYNODEID,factoryid);//厂商名称
                    dn.setFactoryid(factoryid);
                    dn.setFactoryname(factoryName);
                }
                if("ZLX".equals(dmtype)){
                    String dmt = deviceInfoList.get(i).getDeviceid().substring(6,8);//设备主类型
                    dn.setDmtype(dmt);
                }
                if("ZLS".equals(dstype)){
                    String dst = deviceInfoList.get(i).getDeviceid().substring(8,10);//设备子类型
                    dn.setDstype(dst);
                }

                dn.setTjdate(date);
                dn.setStatisflag("2");

                DataSourceContextHolder.clearKey();

                List<DeviceNum> deviceNumList = deviceNumService.getDeviceNumSelect(dn);

                int num = 1;

                if(dn.getAreaid() != null && dn.getLineid() != null && dn.getStationid() != null
                        && dn.getFactoryid() != null && dn.getDstype() != null && dn.getDmtype() != null){
                    System.out.println("初始化已做统计");
                    flag = false;
                }else{
                    if(deviceNumList != null && !deviceNumList.isEmpty()){
                        DeviceNum deviceNumInfo = deviceNumList.get(0);
                        num = deviceNumInfo.getDevicenum();
                        num++;
                        deviceNumInfo.setDevicenum(num);
                        deviceNumInfo.setTjdate(date);
                        deviceNumService.updateDeviceNum(deviceNumInfo);
                    }else{
                        dn.setDevicenum(num);
                        deviceNumService.insertDeviceNum(dn);
                    }
                }
            }

            PageInfo<DeviceNum> pageList = null;
            if(!flag){
                pageList = deviceNumService.getDeviceNumByDate(date,currentPage,pageSize,"1");
            }else{
                pageList = deviceNumService.getDeviceNumByDate(date,currentPage,pageSize,"2");
            }

            return pageList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 按条件查询设备数量
     * @param currentPage
     * @param pageSize
     * @param deviceNum
     * @return PageInfo<DeviceNum>
     */
    @PostMapping("/getDeviceNumSelect")
    @ResponseBody
    @ApiOperation(value = "按条件查询设备数量（条件控制）", notes = "devicenum_info")
    public PageInfo<DeviceNum> getDeviceNumSelect(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                DeviceNum deviceNum) {
        try {
            PageInfo<DeviceNum> pageList = deviceNumService.getDeviceNumSelect(currentPage,pageSize,deviceNum);
            return pageList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 导出设备数据
     * @return List<DeviceNum>
     */
    @PostMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "导出设备数据", notes = "devicenum_info")
    public List<DeviceNum> getAll() {
        try {
            List<DeviceNum> list = deviceNumService.getAll();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
