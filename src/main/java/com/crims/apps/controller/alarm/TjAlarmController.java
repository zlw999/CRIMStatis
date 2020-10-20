package com.crims.apps.controller.alarm;

import com.crims.apps.config.Result;
import com.crims.apps.model.alarminfo.Alarminfo;
import com.crims.apps.model.alarminfo.TjAlarm;
import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.maintainlog.RecMaintainlogtj;
import com.crims.apps.model.nettop.ConfEnum;
import com.crims.apps.service.alarminfo.AlarminfoService;
import com.crims.apps.service.alarminfo.TjAlarmService;
import com.crims.apps.service.nettop.ConfEnumService;
import com.crims.apps.service.nettop.ConfNodeareaService;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("tjalarm")
public class TjAlarmController {
    @Autowired
    private TjAlarmService tjAlarmService;
    @Autowired
    private ConfNodeareaService confNodeareaService;
    @Autowired
    private AlarminfoService AlarmService;
    @Autowired
    private ConfEnumService confEnumService;

    //前台发送请求 他会先去统计表里查 如果没有 就去统计 统计之后加到表里
    //如果去日志表里也查不到 我们不会返回空 在统计表里新加一条数据 除了dsp字段 其他都是空的 dsp里是空

    @GetMapping("findAll")
    public Result<PageInfo<TjAlarm>> findAll(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                   @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                   @RequestParam(required = false) String devicename,
                                                   @RequestParam(required = false) String did,
                                                   @RequestParam(required = false) String PeriodFlag,
                                                   @RequestParam(required = false) String periodinfo,
                                                   @RequestParam(required = false) String deviceid,
                                                   @RequestParam(required = false) String alarmtype,
                                                   @RequestParam(required = false) String alarmlevel,
                                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
                                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
        DataSourceContextHolder.setKey("crimsrec");

        PageHelper.startPage(pageNum, pageSize);
        TjAlarm tjAlarm = new TjAlarm();
        tjAlarm.setDevicename(devicename);
        tjAlarm.setDid(did);
        tjAlarm.setPeriodFlag(PeriodFlag);
        tjAlarm.setStart(start);
        tjAlarm.setEnd(end);
        if(did.length()==2){
            tjAlarm.setAreaid(did.substring(0,2));
            tjAlarm.setLineid(null);
            tjAlarm.setStationid(null);
        }
        if(did.length()==4){
            tjAlarm.setAreaid(did.substring(0,2));
            tjAlarm.setLineid(did.substring(0,4));
            tjAlarm.setStationid(null);
        }
        if(did.length()==6){
            tjAlarm.setAreaid(did.substring(0,2));
            tjAlarm.setLineid(did.substring(0,4));
            tjAlarm.setStationid(did.substring(0,6));
        }
        //判断传过来的类型
        if(alarmtype.length()==5){
            tjAlarm.setAlarmmaintype(alarmtype.substring(0,3));
            tjAlarm.setAlarmsubtype(alarmtype.substring(3,5));
            tjAlarm.setAlarmType(alarmtype);
        }
        if(alarmtype.length()==3){
            tjAlarm.setAlarmmaintype(alarmtype);
            tjAlarm.setAlarmType(alarmtype);
        }
        //tjAlarm.setAlarmmaintype(alarmtype);
        tjAlarm.setPeriodinfo(periodinfo);
        tjAlarm.setAlarmlevel(alarmlevel);
        PageHelper.startPage(pageNum,pageSize);
        List<TjAlarm> listtj = tjAlarmService.findtjAlarm(tjAlarm);

        if(start==null && end==null){
            /*for (TjAlarm Alarm : listtj) {
                TjAlarm sl1 = findSL(tjAlarm.getDeviceid());
                Alarm.setAreaname(sl1.getRegion());
                Alarm.setAreaid(tjAlarm.getDeviceid().substring(0,2));
                Alarm.setLinename(sl1.getLine());
                Alarm.setLineid(tjAlarm.getDeviceid().substring(0,4));
                Alarm.setStationname(sl1.getStation());
                Alarm.setStationid(tjAlarm.getDeviceid().substring(0,6));
            }*/
            PageInfo<TjAlarm> page = new PageInfo<>(listtj);
            return Result.success(page);
        }



        //去统计表里查 如果有还要做个判断 用获取的最大tjtime和传过来的结束时间坐比较
        if (listtj.size() > 0) {
            if(listtj.get(0).getDsp().equals("无")){
                //还要判断一下结束时间是否在这个统计时间之前 如果这个统计时间 小于结束时间 需要重新统计

                List<TjAlarm> nullList = new ArrayList<>();
                PageInfo<TjAlarm> page = new PageInfo<>(nullList);
                return Result.success(page);
            }else{
                if(listtj.get(0).getTJdate().before(end)){
                    //如果最大统计时间还小于传过来的结束时间 需要重新统计
                    //PageHelper.startPage(pageNum,pageSize);
                    List<Alarminfo> list = AlarmService.findAlarmCondition(tjAlarm);
                    //统计之后 因为数据库里可能已经有这个数据 但是不是最新的 我们将其替换
                    //覆盖我们可以通过 查询设备id一样的将其覆盖 这一模式 这一时间的覆盖
                    //我们可以先把查到的删除 在添加新的
                   /* for (TjAlarm tainlogtj : listtj) {
                        tjAlarmService.deleteAll(tainlogtj);
                    }*/
                    if(list.size()>0){
                        Date date = new Date();
                        List<TjAlarm> list1 = new ArrayList<>();
                        for (Alarminfo alarminfo : list) {
                            TjAlarm rectj = new TjAlarm();
                            rectj.setDevicename(alarminfo.getDevicename());
                            rectj.setDeviceid(alarminfo.getDeviceid());
                            rectj.setPeriodFlag(PeriodFlag);
                            rectj.setPeriodinfo(periodinfo);
                            //rectj.setDevicenum(alarminfo.getCounts());
                            rectj.setDevicenum(alarminfo.getCounts());
                            rectj.setAlarmlevel(alarminfo.getAlarmlevel());
                            String typename = findTypename(alarminfo.getAlarmtype());
                            if(alarminfo.getAlarmtype().length()==5){
                                rectj.setAlarmmaintype(alarminfo.getAlarmtype().substring(0,3));
                                rectj.setAlarmsubtype(alarminfo.getAlarmtype().substring(3,5));
                            }
                            if(alarminfo.getAlarmtype().length()==3){
                                rectj.setAlarmmaintype(alarminfo.getAlarmtype());
                            }
                            rectj.setTypename(typename);
                            //rectj.setUser
                            // name(alarminfo.getMaintainuser());
                           // rectj.setUserid(alarminfo.getMaintainuserid());
                            //rectj.setAvgscore(alarminfo.getAvgscore());
                            rectj.setTJdate(date);
                            TjAlarm sl1 = findSL(alarminfo.getDeviceid());
                            rectj.setAreaname(sl1.getRegion());
                            rectj.setAreaid(alarminfo.getDeviceid().substring(0,2));
                            rectj.setLinename(sl1.getLine());
                            rectj.setLineid(alarminfo.getDeviceid().substring(0,4));
                            rectj.setStationname(sl1.getStation());
                            rectj.setStationid(alarminfo.getDeviceid().substring(0,6));
                            rectj.setDsp("1");
                            list1.add(rectj);
                         // tjAlarmService.save(rectj);
                        }
                  //      List<TjAlarm> list3 = tjAlarmService.findtjAlarm(tjAlarm);
                        PageInfo<TjAlarm> page = new PageInfo<>(list1);
                        return Result.success(page);
                    }


                    //PageInfo<RecMaintainlogtj> page = new PageInfo<>(list1);
                    // return Result.success(page);
                }else{
                    for (TjAlarm maintainlogtj : listtj) {
                        TjAlarm sl1 = findSL(maintainlogtj.getDeviceid());
                        maintainlogtj.setAreaname(sl1.getRegion());
                        maintainlogtj.setAreaid(maintainlogtj.getDeviceid().substring(0,2));
                        maintainlogtj.setLinename(sl1.getLine());
                        maintainlogtj.setLineid(maintainlogtj.getDeviceid().substring(0,4));
                        maintainlogtj.setStationname(sl1.getStation());
                        maintainlogtj.setStationid(maintainlogtj.getDeviceid().substring(0,6));
                    }
                    PageInfo<TjAlarm> page = new PageInfo<>(listtj);
                    return Result.success(page);
                }
           /* for (RecMaintainlogtj maintainlogtj : listtj) {
                RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
                maintainlogtj.setRegion(sl1.getRegion());
                maintainlogtj.setLine(sl1.getLine());
                maintainlogtj.setStation(sl1.getStation());
            }
            PageInfo<RecMaintainlogtj> page = new PageInfo<>(listtj);
            return Result.success(page);*/}
        }
        else{
            Date date = new Date();
            //PageHelper.startPage(pageNum,pageSize);
            List<Alarminfo> list = AlarmService.findAlarm(tjAlarm);
            tjAlarmService.delete(tjAlarm);
            List<TjAlarm> list1 = new ArrayList<>();
            if (list.size() > 0) {

                for (Alarminfo recMaintainlog : list) {
                    TjAlarm rectj = new TjAlarm();
                    rectj.setDevicename(recMaintainlog.getDevicename());
                    rectj.setDeviceid(recMaintainlog.getDeviceid());
                    rectj.setPeriodFlag(PeriodFlag);
                    rectj.setPeriodinfo(periodinfo);
                    rectj.setDevicenum(recMaintainlog.getCounts());
                    rectj.setAlarmlevel(recMaintainlog.getAlarmlevel());
                    String typename = findTypename(recMaintainlog.getAlarmtype());
                    if(recMaintainlog.getAlarmtype().length()==5){
                        rectj.setAlarmmaintype(recMaintainlog.getAlarmtype().substring(0,3));
                        rectj.setAlarmsubtype(recMaintainlog.getAlarmtype().substring(3,5));
                    }
                    if(recMaintainlog.getAlarmtype().length()==3){
                        rectj.setAlarmmaintype(recMaintainlog.getAlarmtype());
                    }

                    rectj.setTypename(typename);
                   // rectj.setUsername(recMaintainlog.getMaintainuser());
                   // rectj.setUserid(recMaintainlog.getMaintainuserid());
                   // rectj.setAvgscore(recMaintainlog.getAvgscore());
                    rectj.setTJdate(date);
                    TjAlarm sl1 = findSL(recMaintainlog.getDeviceid());
                    rectj.setAreaname(sl1.getRegion());
                    rectj.setAreaid(recMaintainlog.getDeviceid().substring(0,2));
                    rectj.setLinename(sl1.getLine());
                    rectj.setLineid(recMaintainlog.getDeviceid().substring(0,4));
                    rectj.setStationname(sl1.getStation());
                    rectj.setStationid(recMaintainlog.getDeviceid().substring(0,6));
                    rectj.setDsp("1");
                    //像统计表里添加数据
                    tjAlarmService.save(rectj);
                    list1.add(rectj);
                }
                PageHelper.startPage(pageNum,pageSize);
                List<TjAlarm> list2 = tjAlarmService.findtjAlarm(tjAlarm);
                PageInfo<TjAlarm> page = new PageInfo<>(list2);
                return Result.success(page);
            }else{
                TjAlarm failrec = new TjAlarm();
                failrec.setDsp("无");
                failrec.setPeriodinfo(periodinfo);
                failrec.setPeriodFlag(PeriodFlag);
                failrec.setDevicename(devicename);
                failrec.setTJdate(date);
                failrec.setAlarmlevel(alarmlevel);
                //判断传过来的类型
                if(alarmtype.length()==5){
                    failrec.setAlarmmaintype(alarmtype.substring(0,3));
                    failrec.setAlarmsubtype(alarmtype.substring(3,5));
                    failrec.setAlarmType(alarmtype);
                }
                if(alarmtype.length()==3){
                    failrec.setAlarmmaintype(alarmtype);
                    failrec.setAlarmType(alarmtype);
                }
                failrec.setDeviceid(deviceid);
                tjAlarmService.save(failrec);
                List<TjAlarm> nullList = new ArrayList<>();
                PageInfo<TjAlarm> page = new PageInfo<>(nullList);
                return Result.success(page);
            }


        }

        return null;
    }


    //统计的方法


    @GetMapping("find")
    public Result<PageInfo<TjAlarm>> findAll(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                      @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        PageHelper.startPage(pageNum,pageSize);
        List<TjAlarm> list = tjAlarmService.findAll();

        for (TjAlarm maintainlogtj : list) {
            TjAlarm sl1 = findSL(maintainlogtj.getDeviceid());
            maintainlogtj.setRegion(sl1.getRegion());
            maintainlogtj.setLine(sl1.getLine());
            maintainlogtj.setStation(sl1.getStation());

        }

        PageInfo<TjAlarm> page = new PageInfo<>(list);


        return Result.success(page);

    }



    //通过id查询车站 区域 列的方法
    public TjAlarm findSL1(String key) {
        DataSourceContextHolder.setKey("crimsdbs");
        TjAlarm recMaintainlogtj = new TjAlarm();

        //通过前两位查区域
        if (key.length() == 2) {

            String region = confNodeareaService.findNodeNameById(key);
            recMaintainlogtj.setRegion(region);
        }
        if (key.length() == 4) {
            //通过前四位查线路
            String line = confNodeareaService.findNodeNameById(key);
            recMaintainlogtj.setLine(line);
        }
        if (key.length() == 6) {
            //通过前六位查车站
            String station = confNodeareaService.findNodeNameById(key);
            recMaintainlogtj.setStation(station);
        }
        DataSourceContextHolder.clearKey();
        return  recMaintainlogtj;

    }


    //通过id查询车站 区域 列的方法
    public TjAlarm findSL(String key){
        DataSourceContextHolder.setKey("crimsdbs");
        TjAlarm recMaintainlogtj = new TjAlarm();
        //通过设备id 查询区域 线路 车站
        String deviceid = key.substring(0, 6);
        //通过前两位查区域
        String d1 = deviceid.substring(0,2);
        String region = confNodeareaService.findNodeNameById(d1);
        recMaintainlogtj.setRegion(region);
        //通过前四位查线路
        String d2 = deviceid.substring(0,4);
        String line = confNodeareaService.findNodeNameById(d2);
        recMaintainlogtj.setLine(line);
        //通过前六位查车站
        String d3 = deviceid.substring(0,6);
        String station = confNodeareaService.findNodeNameById(d3);
        recMaintainlogtj.setStation(station);
        DataSourceContextHolder.clearKey();
        return recMaintainlogtj;
    }


    //通过类型查类型名
    public String findTypename(String alarmtype){
        DataSourceContextHolder.setKey("crimsdbs");
        ConfEnum confEnum = new ConfEnum();
        if(alarmtype.length()==5){
            //去枚举表查
            if(alarmtype.substring(0,3).equals("001")){
                String id="A001";
                confEnum.setId(id);
                confEnum.setItemValue( alarmtype.substring(3,5));
               String name = confEnumService.findTypename(confEnum);
               return name;
            }else if(alarmtype.substring(0,3).equals("002")){
                String id="A002";
                confEnum.setId(id);
                confEnum.setItemValue( alarmtype.substring(3,5));
                String name = confEnumService.findTypename(confEnum);
                return name;
            }else if(alarmtype.substring(0,3).equals("003")){
                String id="A003";
                confEnum.setId(id);
                confEnum.setItemValue( alarmtype.substring(3,5));
                String name = confEnumService.findTypename(confEnum);
                return name;
            }else{
                String id="A101";
                confEnum.setId(id);
                confEnum.setItemValue( alarmtype.substring(3,5));
                String name = confEnumService.findTypename(confEnum);
                return name;
            }

        }
        if(alarmtype.length()==3){
            confEnum.setItemValue(alarmtype);
            String name = confEnumService.findMainTypename(confEnum);
            return name;
        }
        DataSourceContextHolder.clearKey();
        return null;
    }



    @PostMapping("tjlie")
    public Result<PageInfo<TjAlarm>> tjlie(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                             @RequestParam(required = false, defaultValue = "10") int pageSize,
                                             @RequestBody TjAlarm alarm
                                           ){
           //会有几种情况 按照区域 车站 线路 设备名 告警级别 告警类型进行分组

        String s1=alarm.getAreaid()+alarm.getLineid()+alarm.getStationid()+alarm.getDevicename()+alarm.getAlarmlevel()+alarm.getAlarmType();
        String s = s1.substring(0, s1.length() - 1);
        System.out.println(s);
          /*List<String> list = new ArrayList<>();
         if(alarm.getAreaid()!=""){
             list.add("areaid");
         }
        if(alarm.getLineid()!=""){
            list.add("lineid");
        }
        if(alarm.getStationid()!=""){
            list.add("stationid");
        }
        if(alarm.getDevicename()!=""){
            list.add("devicename");
        }
        if(alarm.getAlarmType()!=""){

            list.add("alarmmaintype");
            list.add("alarmsubtype");
        }
        if(alarm.getAlarmmaintype()!=""){
            list.add("alarmmaintype");
        }
        if(alarm.getAlarmsubtype()!=""){
            list.add("alarmsubtype");
        }
        if(alarm.getAlarmlevel()!=""){
            list.add("alarmlevel");
        }
        for (String s : list) {
            System.out.println(s);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TjAlarm> list1 = tjAlarmService.tjlie(list);
        String s="";
        for (TjAlarm tjAlarm : list1) {
            s=tjAlarm+",";
        }
        System.out.println(s);*/
        PageHelper.startPage(pageNum, pageSize);
        List<TjAlarm> list1 = tjAlarmService.tjlie(s);
      /*  for (int i = 0; i < list1.size(); i++) {
            if(list1.get(i).getDsp().equals("无")){
                list1.remove(list1.get(i));

            }
        }*/
        System.out.println(list1);

        PageInfo<TjAlarm> page = new PageInfo<>(list1);
        return Result.success(page);
    }

}
