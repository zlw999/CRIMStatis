package com.crims.apps.controller.state;

import com.crims.apps.config.CodeMsg;
import com.crims.apps.config.Result;
import com.crims.apps.model.alarminfo.Alarminfo;
import com.crims.apps.model.state.AlarmInfoVO;
import com.crims.apps.model.state.DeviceStatStateInfoVO;
import com.crims.apps.model.state.FaultlevelStatInfoVO;
import com.crims.apps.model.state.StationInfoVO;
import com.crims.apps.service.state.StateStatisticsService;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stateStatInfo")
public class StateStatisticsController {
    public static Logger logger = LoggerFactory.getLogger(StateStatisticsController.class);

    @Autowired
    private StateStatisticsService stateStatisticsService;
    @GetMapping("/getDevState")
    @ApiOperation(value = "以线路+车站分类，获取当前的设备状态(在线数和故障数)")
    public List<DeviceStatStateInfoVO> FindDevGroupStat(@RequestParam(required = false, defaultValue = "000000") String lineId, @RequestParam(required = false, defaultValue = "00") String stationId)
    {
        /*
         *   从设备表crimsdbs.conf_device中统计设备数量；
         *   从告警表crimsrec.rec_alarminfo中统计离线数和故障数(同一个设备的不同类型的故障算一个故障)；

         *   lineId:XXXXXX,若为000000，表示所有线路，stationId将无效；若为XXXX00,表示XXXX的线路

         *   stationId:XX,若为00,则以lineId的值为统计条件；非00时，以lineId+stationId为统计条件

         */
        String id=lineId+stationId;
        if(lineId.equals("000000")){
            //查询所有线路
            //首先在nodearea表里查询出所有车站 再通过车站的stationid 获取他的线路id
            DataSourceContextHolder.setKey("crimsdbs");
            List<DeviceStatStateInfoVO> list = stateStatisticsService.findStation();
            for (DeviceStatStateInfoVO vo : list) {
                String lineName = stateStatisticsService.findNodeNameById(vo.getStationId().substring(0, 4));
                vo.setLineId(vo.getStationId().substring(0, 4));
                vo.setLineName(lineName);
                int devCount = stateStatisticsService.finddevCount(vo.getStationId());
                vo.setDevCount(devCount);
            }
            DataSourceContextHolder.clearKey();
            for (DeviceStatStateInfoVO infoVO : list) {
                String newStationid = infoVO.getStationId();
                int faultCount = stateStatisticsService.findfaultCount(newStationid);
                infoVO.setFaultCount(faultCount);
                int devCount = infoVO.getDevCount();
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后1位
                numberFormat.setMaximumFractionDigits(1);
                if(devCount==0) {
                    String result = "0";
                    infoVO.setFaultRatio(result);
                    System.out.println("1");
                }else {
                    String result = numberFormat.format((float) faultCount / (float) devCount * 100);
                    System.out.println("diliverNum和queryMailNum的百分比为:" + result + "%");
                    infoVO.setFaultRatio(result + "%");
                }
            }
            return list;

        }else if(stationId.equals("00")){
            //查询lineid这一条线路的 假如传8101
            DataSourceContextHolder.setKey("crimsdbs");
            String lineName = stateStatisticsService.findNodeNameById(lineId);
            List<DeviceStatStateInfoVO> list = stateStatisticsService.getDeviceStatStateInfoVO(lineId);

            ArrayList<DeviceStatStateInfoVO> newlist = new ArrayList<>();
            for (DeviceStatStateInfoVO stationInfoVO : list) {
                DeviceStatStateInfoVO infoVO = new DeviceStatStateInfoVO();
                infoVO.setLineId(lineId);
                infoVO.setLineName(lineName);
                infoVO.setStationId(stationInfoVO.getLineId());
                infoVO.setStationName(stationInfoVO.getLineName());
                int devCount = stateStatisticsService.finddevCount(infoVO.getStationId());
                infoVO.setDevCount(devCount);
                newlist.add(infoVO);
            }
            DataSourceContextHolder.clearKey();
            for (DeviceStatStateInfoVO infoVO : newlist) {
                String newStationid = infoVO.getStationId();
                int faultCount = stateStatisticsService.findfaultCount(newStationid);
                infoVO.setFaultCount(faultCount);
                int devCount = infoVO.getDevCount();
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后1位
                numberFormat.setMaximumFractionDigits(1);
                if(devCount==0) {
                    String result = "0";
                    infoVO.setFaultRatio(result);
                }else {
                    String result = numberFormat.format((float) faultCount / (float) devCount * 100);
                    System.out.println("diliverNum和queryMailNum的百分比为:" + result + "%");
                    infoVO.setFaultRatio(result + "%");
                }
            }
           return newlist;
        }else{
            //查询id这一个车站的   假如传810101
            DataSourceContextHolder.setKey("crimsdbs");
            String lineName = stateStatisticsService.findNodeNameById(lineId);
            List<DeviceStatStateInfoVO> list = stateStatisticsService.getDeviceStatStateInfoVO(lineId);

            ArrayList<DeviceStatStateInfoVO> newlist = new ArrayList<>();
            for (DeviceStatStateInfoVO stationInfoVO : list) {
                DeviceStatStateInfoVO infoVO = new DeviceStatStateInfoVO();
                infoVO.setLineId(lineId);
                infoVO.setLineName(lineName);
                infoVO.setStationId(stationInfoVO.getLineId());
                infoVO.setStationName(stationInfoVO.getLineName());
                int devCount = stateStatisticsService.finddevCount(id);
                infoVO.setDevCount(devCount);
                newlist.add(infoVO);
            }
            DataSourceContextHolder.clearKey();
            for (DeviceStatStateInfoVO infoVO : newlist) {
                int faultCount = stateStatisticsService.findfaultCount(id);
                infoVO.setFaultCount(faultCount);
                int devCount = infoVO.getDevCount();
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后1位
                numberFormat.setMaximumFractionDigits(1);
                if(devCount==0) {
                    String result = "0";
                    infoVO.setFaultRatio(result);
                }else {
                    String result = numberFormat.format((float) faultCount / (float) devCount * 100);
                    System.out.println("diliverNum和queryMailNum的百分比为:" + result + "%");
                    infoVO.setFaultRatio(result + "%");
                }
            }
            return newlist;
        }


    }

    @GetMapping("/getStationInfo")
    @ApiOperation(value = "获取指定线路的所有车站信息:按起点到终点排序")
    public List<StationInfoVO> GetStationInfo(@RequestParam(required = true) String lineId)
    {
        DataSourceContextHolder.setKey("crimsdbs");
       String lineName = stateStatisticsService.findNodeNameById(lineId);
       List<StationInfoVO> list = stateStatisticsService.getStationInfo(lineId);

        ArrayList<StationInfoVO> newlist = new ArrayList<>();
        for (StationInfoVO stationInfoVO : list) {
            StationInfoVO infoVO = new StationInfoVO();
            infoVO.setLineId(lineId);
            infoVO.setLineName(lineName);
            infoVO.setStationId(stationInfoVO.getLineId());
            infoVO.setStationName(stationInfoVO.getLineName());
            newlist.add(infoVO);
        }
        DataSourceContextHolder.clearKey();
        return newlist;
    }

    @GetMapping("/getStationDevFaultInfo")
    @ApiOperation(value = "获取指定线路的所有车站设备故障信息")
    public List<DeviceStatStateInfoVO> GetStationDevFaultInfo(@RequestParam(required = true) String lineId)
    {
        /*
         *   从设备表中统计设备数量；
         *   从告警表中统计离线数和故障数(同一个设备的不同类型的故障算一个故障)；

         *   lineId:XXXXXX,若为000000，表示所有线路；若为XXXX00或XXXX,表示XXXX的线路;
         *   前端的判断逻辑：当离线数或故障数大于0时，该站存在设备故障
         */
        //查询lineid这一条线路的 假如传8101
        DataSourceContextHolder.setKey("crimsdbs");
        String lineName = stateStatisticsService.findNodeNameById(lineId);
        List<DeviceStatStateInfoVO> list = stateStatisticsService.getDeviceStatStateInfoVO(lineId);

        ArrayList<DeviceStatStateInfoVO> newlist = new ArrayList<>();
        for (DeviceStatStateInfoVO stationInfoVO : list) {
            DeviceStatStateInfoVO infoVO = new DeviceStatStateInfoVO();
            infoVO.setLineId(lineId);
            infoVO.setLineName(lineName);
            infoVO.setStationId(stationInfoVO.getLineId());
            infoVO.setStationName(stationInfoVO.getLineName());
            int devCount = stateStatisticsService.finddevCount(infoVO.getStationId());
            infoVO.setDevCount(devCount);
            newlist.add(infoVO);
        }
        DataSourceContextHolder.clearKey();
        for (DeviceStatStateInfoVO infoVO : newlist) {
            String newStationid = infoVO.getStationId();
            int faultCount = stateStatisticsService.findfaultCount(newStationid);
            infoVO.setFaultCount(faultCount);
            int devCount = infoVO.getDevCount();
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后1位
            numberFormat.setMaximumFractionDigits(1);
            if(devCount==0){
                String result="0";
                infoVO.setFaultRatio(result);
            }else{
                String result = numberFormat.format((float)faultCount/(float)devCount*100);
                infoVO.setFaultRatio(result + "%");
            }


        }
        return newlist;
    }


    @GetMapping("/getTodoProcessAlarmInfo")
    @ApiOperation(value = "获取待处理的告警")
    public Result<PageInfo<AlarmInfoVO>> GetTodoProcessAlarmInfo(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                                @RequestParam(required = false, defaultValue = "10") int pageSize)
    {
        /*
        * 查询条件为：rec_alarminfo.AlarmDisTime='2000-01-01 01:01:01' AND rec_alarminfo.AlarmAffirmTime='2000-01-01 01:01:01'
        */
        PageHelper.startPage(currentPage,pageSize);
       List<AlarmInfoVO> list = stateStatisticsService.getTodoProcessAlarmInfo();
       if(list.size()==0){
           return Result.error(CodeMsg.SERVER_ERROR);
       }
        PageInfo<AlarmInfoVO> page = new PageInfo<>(list);
        return Result.success(page);
    }

    @GetMapping("/getTodoProcessAlarmInfo1")
    @ApiOperation(value = "获取待处理的告警")
    public Result<List<AlarmInfoVO>> GetTodoProcessAlarmInfo1(@RequestParam("status")int status)
    {
        /*
         * 查询条件为：rec_alarminfo.AlarmDisTime='2000-01-01 01:01:01' AND rec_alarminfo.AlarmAffirmTime='2000-01-01 01:01:01'
         */
        if(status==0){
            List<AlarmInfoVO> list = stateStatisticsService.getTodoProcessAlarmInfoByAlarmDisTime();
            if(list.size()==0){
                return Result.error(CodeMsg.SERVER_ERROR);
            }
            return Result.success(list);
        }
        if(status==1){
            List<AlarmInfoVO> list = stateStatisticsService.getTodoProcessAlarmInfoByAlarmAffirmTime();
            if(list.size()==0){
                return Result.error(CodeMsg.SERVER_ERROR);
            }
            return Result.success(list);
        }

        return null;
    }

    @GetMapping("/getFaultLevelStatInfo")
    @ApiOperation(value = "获取指定线路下的各车站的按级别分类的告警统计")
    public List<FaultlevelStatInfoVO> GetFaultLevelStatInfo(@RequestParam(required = true,defaultValue = "000000") String lineId)
    {
        /*
         *   从设备表中统计设备数量；
         *   从告警表中统计离线数和故障数(同一个设备的不同类型的故障算一个故障)；

         *   lineId:XXXXXX,若为000000，表示所有线路；若为XXXX00或XXXX,表示XXXX的线路;
         *
         */
        if(lineId.equals("000000")){
            //查询所有线路下的车站故障级别
            //首先在nodearea表里查询出所有车站 再通过车站的stationid 获取他的线路id
            DataSourceContextHolder.setKey("crimsdbs");
            List<FaultlevelStatInfoVO> list = stateStatisticsService.findStationforFault();
            for (FaultlevelStatInfoVO vo : list) {
                String lineName = stateStatisticsService.findNodeNameById(vo.getStationId().substring(0, 4));
                vo.setLineId(vo.getStationId().substring(0, 4));
                vo.setLineName(lineName);
            }
            DataSourceContextHolder.clearKey();
            for (FaultlevelStatInfoVO faultVo : list) {
                String id=faultVo.getStationId();
               List<Alarminfo> newList = stateStatisticsService.tjAlarminfo(id);
                for (Alarminfo alarminfo : newList) {
                    if(alarminfo.getAlarmlevel().equals("02")){
                        faultVo.setTwolevelNum(alarminfo.getCounts());
                    }else if(alarminfo.getAlarmlevel().equals("03")){
                        faultVo.setThreelevelNum(alarminfo.getCounts());
                    }else if(alarminfo.getAlarmlevel().equals("04")){
                        faultVo.setFourlevelNum(alarminfo.getCounts());
                    }else{
                        faultVo.setOtherlevelNum(alarminfo.getCounts());
                    }
                }
            }
            return list;
        }else{
            //否则会传一个线路 查询这个线路下的故障级别
            DataSourceContextHolder.setKey("crimsdbs");
            String lineName = stateStatisticsService.findNodeNameById(lineId);
            List<DeviceStatStateInfoVO> list = stateStatisticsService.getDeviceStatStateInfoVO(lineId);

            ArrayList<FaultlevelStatInfoVO> newlist = new ArrayList<>();
            for (DeviceStatStateInfoVO stationInfoVO : list) {
                FaultlevelStatInfoVO infoVO = new FaultlevelStatInfoVO();
                infoVO.setLineId(lineId);
                infoVO.setLineName(lineName);
                infoVO.setStationId(stationInfoVO.getLineId());
                infoVO.setStationName(stationInfoVO.getLineName());
                newlist.add(infoVO);
            }
            DataSourceContextHolder.clearKey();
            for (FaultlevelStatInfoVO faultVo : newlist) {
                String id=faultVo.getStationId();
                List<Alarminfo> list1 = stateStatisticsService.tjAlarminfo(id);
                for (Alarminfo alarminfo : list1) {
                    if(alarminfo.getAlarmlevel().equals("02")){
                        faultVo.setTwolevelNum(alarminfo.getCounts());
                    }else if(alarminfo.getAlarmlevel().equals("03")){
                        faultVo.setThreelevelNum(alarminfo.getCounts());
                    }else if(alarminfo.getAlarmlevel().equals("04")){
                        faultVo.setFourlevelNum(alarminfo.getCounts());
                    }else{
                        faultVo.setOtherlevelNum(alarminfo.getCounts());
                    }
                }
            }
            return newlist;
        }
    }
}
