package com.crims.apps.controller.maintainlog;

import com.crims.apps.config.ListPageUtil;
import com.crims.apps.config.Result;
import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.maintainlog.RecMaintainlogtj;
import com.crims.apps.service.maintainlog.RecMaintainlogService;
import com.crims.apps.service.maintainlog.RecMaintainlogtjService;
import com.crims.apps.service.nettop.ConfNodeareaService;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("maintainlogtj")
public class RecMaintainlogtjController {
    //首先去记录里查 之后进行运算 存到统计表里    把统计写成一个方法
    @Autowired
    private RecMaintainlogService recMaintainlogService;
    @Autowired
    private RecMaintainlogtjService recMaintainlogtjService;
    @Autowired
    private ConfNodeareaService confNodeareaService;

    //前台发送请求 他会先去统计表里查 如果没有 就去统计 统计之后加到表里
    //如果去日志表里也查不到 我们不会返回空 在统计表里新加一条数据 除了dsp字段 其他都是空的 dsp里是空

    @GetMapping("findAll")
    public Result<PageInfo<RecMaintainlogtj>> findAll(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                      @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                      @RequestParam(required = false) String devicename,
                                                      @RequestParam(required = false) String did,
                                                      @RequestParam(required = false) String PeriodFlag,
                                                      @RequestParam(required = false) String periodinfo,
                                                      @RequestParam(required = false) String deviceid,
                                                      @RequestParam(required = false) String userid,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
        DataSourceContextHolder.setKey("crimsrec");
        PageHelper.startPage(pageNum, pageSize);
        RecMaintainlogtj recMaintainlogtj = new RecMaintainlogtj();
        recMaintainlogtj.setDevicename(devicename);
        recMaintainlogtj.setDid(did);
        recMaintainlogtj.setPeriodFlag(PeriodFlag);
        recMaintainlogtj.setStart(start);
        recMaintainlogtj.setEnd(end);
        recMaintainlogtj.setUserid(userid);
        recMaintainlogtj.setPeriodinfo(periodinfo);
        List<RecMaintainlogtj> listtj = recMaintainlogtjService.findMainlogtj(recMaintainlogtj);

       if(start==null && end==null){
           for (RecMaintainlogtj maintainlogtj : listtj) {
               RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
               maintainlogtj.setRegion(sl1.getRegion());
               maintainlogtj.setLine(sl1.getLine());
               maintainlogtj.setStation(sl1.getStation());
           }
           PageInfo<RecMaintainlogtj> page = new PageInfo<>(listtj);
           return Result.success(page);
       }



        //去统计表里查 如果有还要做个判断 用获取的最大tjtime和传过来的结束时间坐比较
        if (listtj.size() > 0) {
           if(listtj.get(0).getDsp().equals("无")){
                //还要判断一下结束时间是否在这个统计时间之前 如果这个统计时间 小于结束时间 需要重新统计

                List<RecMaintainlogtj> nullList = new ArrayList<>();
                PageInfo<RecMaintainlogtj> page = new PageInfo<>(nullList);
                return Result.success(page);
            }else{
          if(listtj.get(0).getTjtime().before(end)){
              //如果最大统计时间还小于传过来的结束时间 需要重新统计
              List<RecMaintainlog> list = recMaintainlogService.findMainlog(recMaintainlogtj);
              //统计之后 因为数据库里可能已经有这个数据 但是不是最新的 我们将其替换
              //覆盖我们可以通过 查询设备id一样的将其覆盖 这一模式 这一时间的覆盖
              //我们可以先把查到的删除 在添加新的
              for (RecMaintainlogtj tainlogtj : listtj) {
                  recMaintainlogtjService.delete(tainlogtj);
              }
              if(list.size()>0){
                  Date date = new Date();
                  List<RecMaintainlogtj> list1 = new ArrayList<>();
                  for (RecMaintainlog recMaintainlog : list) {
                      RecMaintainlogtj rectj = new RecMaintainlogtj();
                      rectj.setDevicename(recMaintainlog.getDevicename());
                      rectj.setDeviceid(recMaintainlog.getDeviceid());
                      rectj.setPeriodFlag(PeriodFlag);
                      rectj.setPeriodinfo(periodinfo);
                      rectj.setMaintainnum(recMaintainlog.getCounts());
                      rectj.setCounts(recMaintainlog.getCounts());
                      rectj.setUsername(recMaintainlog.getMaintainuser());
                      rectj.setUserid(recMaintainlog.getMaintainuserid());
                      rectj.setAvgscore(recMaintainlog.getAvgscore());
                      rectj.setTjtime(date);
                      RecMaintainlogtj sl1 = findSL(recMaintainlog.getDeviceid());
                      rectj.setRegion(sl1.getRegion());
                      rectj.setLine(sl1.getLine());
                      rectj.setStation(sl1.getStation());
                      rectj.setDsp("1");
                      list1.add(rectj);
                     recMaintainlogtjService.save(rectj);
                  }
                  List<RecMaintainlogtj> list2 = recMaintainlogtjService.findMainlogtj(recMaintainlogtj);
                  for (RecMaintainlogtj maintainlogtj : list2) {
                      RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
                      maintainlogtj.setRegion(sl1.getRegion());
                      maintainlogtj.setLine(sl1.getLine());
                      maintainlogtj.setStation(sl1.getStation());
                  }
                  PageInfo<RecMaintainlogtj> page = new PageInfo<>(list2);
                  return Result.success(page);
              }


              //PageInfo<RecMaintainlogtj> page = new PageInfo<>(list1);
             // return Result.success(page);
          }else{
              for (RecMaintainlogtj maintainlogtj : listtj) {
                  RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
                  maintainlogtj.setRegion(sl1.getRegion());
                  maintainlogtj.setLine(sl1.getLine());
                  maintainlogtj.setStation(sl1.getStation());
              }
              PageInfo<RecMaintainlogtj> page = new PageInfo<>(listtj);
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
            List<RecMaintainlog> list = recMaintainlogService.findMainlog(recMaintainlogtj);
            List<RecMaintainlogtj> list1 = new ArrayList<>();
            if (list.size() > 0) {
                for (RecMaintainlog recMaintainlog : list) {
                    RecMaintainlogtj rectj = new RecMaintainlogtj();
                    rectj.setDevicename(recMaintainlog.getDevicename());
                    rectj.setDeviceid(recMaintainlog.getDeviceid());
                    rectj.setPeriodFlag(PeriodFlag);
                    rectj.setPeriodinfo(periodinfo);
                    rectj.setMaintainnum(recMaintainlog.getCounts());
                    rectj.setCounts(recMaintainlog.getCounts());
                    rectj.setUsername(recMaintainlog.getMaintainuser());
                    rectj.setUserid(recMaintainlog.getMaintainuserid());
                    rectj.setAvgscore(recMaintainlog.getAvgscore());
                    rectj.setTjtime(date);
                    RecMaintainlogtj sl1 = findSL(recMaintainlog.getDeviceid());
                    rectj.setRegion(sl1.getRegion());
                    rectj.setLine(sl1.getLine());
                    rectj.setStation(sl1.getStation());
                    rectj.setDsp("1");
                    //像统计表里添加数据
                    recMaintainlogtjService.save(rectj);
                    list1.add(rectj);
            }
                List<RecMaintainlogtj> list2 = recMaintainlogtjService.findMainlogtj(recMaintainlogtj);
                for (RecMaintainlogtj maintainlogtj : list2) {
                    RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
                    maintainlogtj.setRegion(sl1.getRegion());
                    maintainlogtj.setLine(sl1.getLine());
                    maintainlogtj.setStation(sl1.getStation());
                }
                PageInfo<RecMaintainlogtj> page = new PageInfo<>(list2);
                return Result.success(page);
        }else{
                RecMaintainlogtj failrec = new RecMaintainlogtj();
                failrec.setDsp("无");
                failrec.setPeriodinfo(periodinfo);
                failrec.setPeriodFlag(PeriodFlag);
                failrec.setDevicename(devicename);
                failrec.setTjtime(date);
                failrec.setDeviceid(deviceid);
                recMaintainlogtjService.save(failrec);
                 List<RecMaintainlogtj> nullList = new ArrayList<>();
                 PageInfo<RecMaintainlogtj> page = new PageInfo<>(nullList);
                 return Result.success(page);
            }


        }

        return null;
    }


    //统计的方法


    @GetMapping("find")
    public Result<PageInfo<RecMaintainlogtj>> findAll(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                      @RequestParam(required = false, defaultValue = "10") int pageSize
                                                       ) {
        PageHelper.startPage(pageNum,pageSize);
        List<RecMaintainlogtj> list = recMaintainlogtjService.find();
        for (RecMaintainlogtj maintainlogtj : list) {
            RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
            maintainlogtj.setRegion(sl1.getRegion());
            maintainlogtj.setLine(sl1.getLine());
            maintainlogtj.setStation(sl1.getStation());
        }
        PageInfo<RecMaintainlogtj> page = new PageInfo<>(list);
        return Result.success(page);

    }

    @GetMapping("finduser")
    public Result<PageInfo<RecMaintainlogtj>> finduser(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                      @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        PageHelper.startPage(pageNum,pageSize);
        List<RecMaintainlogtj> list = recMaintainlogtjService.finduser();
        for (RecMaintainlogtj maintainlogtj : list) {
            RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
            maintainlogtj.setRegion(sl1.getRegion());
            maintainlogtj.setLine(sl1.getLine());
            maintainlogtj.setStation(sl1.getStation());
        }
        PageInfo<RecMaintainlogtj> page = new PageInfo<>(list);
        return Result.success(page);

    }


    //通过id查询车站 区域 列的方法
    public RecMaintainlogtj findSL1(String key) {
        DataSourceContextHolder.setKey("crimsdbs");
        RecMaintainlogtj recMaintainlogtj = new RecMaintainlogtj();

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
    public RecMaintainlogtj findSL(String key){
        DataSourceContextHolder.setKey("crimsdbs");
        RecMaintainlogtj recMaintainlogtj = new RecMaintainlogtj();
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


    @GetMapping("findtjUser")
    public Result<PageInfo<RecMaintainlogtj>> findtjUser(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                      @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                      @RequestParam(required = false) String userid,
                                                      @RequestParam(required = false) String did,
                                                      @RequestParam(required = false) String PeriodFlag,
                                                      @RequestParam(required = false) String periodinfo,
                                                      @RequestParam(required = false) String deviceid,
                                                         @RequestParam(required = false) String devicename,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
        DataSourceContextHolder.setKey("crimsrec");
        PageHelper.startPage(pageNum, pageSize);
        RecMaintainlogtj recMaintainlogtj = new RecMaintainlogtj();
        recMaintainlogtj.setDevicename(devicename);
        recMaintainlogtj.setDid(did);
        recMaintainlogtj.setPeriodFlag(PeriodFlag);
        recMaintainlogtj.setStart(start);
        recMaintainlogtj.setEnd(end);
        recMaintainlogtj.setUserid(userid);
        recMaintainlogtj.setPeriodinfo(periodinfo);
        List<RecMaintainlogtj> listtj = recMaintainlogtjService.findMainlogtj(recMaintainlogtj);
        if(start==null && end==null){
            for (RecMaintainlogtj maintainlogtj : listtj) {
                RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
                maintainlogtj.setRegion(sl1.getRegion());
                maintainlogtj.setLine(sl1.getLine());
                maintainlogtj.setStation(sl1.getStation());
            }
            PageInfo<RecMaintainlogtj> page = new PageInfo<>(listtj);
            return Result.success(page);
        }

        if (listtj.size() > 0) {
            if(listtj.get(0).getDsp().equals("无")){
                //还要判断一下结束时间是否在这个统计时间之前 如果这个统计时间 小于结束时间 需要重新统计

                List<RecMaintainlogtj> nullList = new ArrayList<>();
                PageInfo<RecMaintainlogtj> page = new PageInfo<>(nullList);
                return Result.success(page);
            }else{
                if(listtj.get(0).getTjtime().before(end)){
                    //如果最大统计时间还小于传过来的结束时间 需要重新统计
                    List<RecMaintainlog> list = recMaintainlogService.findMainlog(recMaintainlogtj);
                    //统计之后 因为数据库里可能已经有这个数据 但是不是最新的 我们将其替换
                    //覆盖我们可以通过 查询设备id一样的将其覆盖 这一模式 这一时间的覆盖
                    //我们可以先把查到的删除 在添加新的
                   for (RecMaintainlogtj tainlogtj : listtj) {
                        recMaintainlogtjService.delete(tainlogtj);
                    }
                    if(list.size()>0){
                        Date date = new Date();
                        List<RecMaintainlogtj> list1 = new ArrayList<>();
                        for (RecMaintainlog recMaintainlog : list) {
                            RecMaintainlogtj rectj = new RecMaintainlogtj();
                            rectj.setDevicename(recMaintainlog.getDevicename());
                            rectj.setDeviceid(recMaintainlog.getDeviceid());
                            rectj.setPeriodFlag(PeriodFlag);
                            rectj.setPeriodinfo(periodinfo);
                            rectj.setCounts(recMaintainlog.getCounts());
                            rectj.setMaintainnum(recMaintainlog.getCounts());
                            rectj.setUsername(recMaintainlog.getMaintainuser());
                            rectj.setUserid(recMaintainlog.getMaintainuserid());
                            rectj.setAvgscore(recMaintainlog.getAvgscore());
                            rectj.setTjtime(date);
                            RecMaintainlogtj sl1 = findSL(recMaintainlog.getDeviceid());
                            rectj.setRegion(sl1.getRegion());
                            rectj.setLine(sl1.getLine());
                            rectj.setStation(sl1.getStation());
                            rectj.setDsp("1");
                            list1.add(rectj);
                           recMaintainlogtjService.save(rectj);
                        }
                        List<RecMaintainlogtj> list2 = recMaintainlogtjService.findMainlogtj(recMaintainlogtj);
                        for (RecMaintainlogtj maintainlogtj : list2) {
                            RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
                            maintainlogtj.setRegion(sl1.getRegion());
                            maintainlogtj.setLine(sl1.getLine());
                            maintainlogtj.setStation(sl1.getStation());
                        }
                        PageInfo<RecMaintainlogtj> page = new PageInfo<>(list2);
                        return Result.success(page);
                    }


                    //PageInfo<RecMaintainlogtj> page = new PageInfo<>(list1);
                    // return Result.success(page);
                }else{
                    for (RecMaintainlogtj maintainlogtj : listtj) {
                        RecMaintainlogtj sl1 = findSL(maintainlogtj.getDeviceid());
                        maintainlogtj.setRegion(sl1.getRegion());
                        maintainlogtj.setLine(sl1.getLine());
                        maintainlogtj.setStation(sl1.getStation());
                    }
                    PageInfo<RecMaintainlogtj> page = new PageInfo<>(listtj);
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
            List<RecMaintainlog> list = recMaintainlogService.findMainlogCondition(recMaintainlogtj);
            List<RecMaintainlogtj> list1 = new ArrayList<>();
            if (list.size() > 0) {

                for (RecMaintainlog recMaintainlog : list) {
                    RecMaintainlogtj rectj = new RecMaintainlogtj();
                    rectj.setDevicename(recMaintainlog.getDevicename());
                    rectj.setDeviceid(recMaintainlog.getDeviceid());
                    rectj.setPeriodFlag(PeriodFlag);
                    rectj.setPeriodinfo(periodinfo);
                    rectj.setCounts(recMaintainlog.getCounts());
                    rectj.setMaintainnum(recMaintainlog.getCounts());
                    rectj.setUsername(recMaintainlog.getMaintainuser());
                    rectj.setUserid(recMaintainlog.getMaintainuserid());
                    rectj.setAvgscore(recMaintainlog.getAvgscore());
                    rectj.setTjtime(date);
                    RecMaintainlogtj sl1 = findSL(recMaintainlog.getDeviceid());
                    rectj.setRegion(sl1.getRegion());
                    rectj.setLine(sl1.getLine());
                    rectj.setStation(sl1.getStation());
                    rectj.setDsp("1");
                    //像统计表里添加数据
                    recMaintainlogtjService.save(rectj);
                    list1.add(rectj);
                }
                PageInfo<RecMaintainlogtj> page = new PageInfo<>(list1);
                return Result.success(page);
            }else{
                RecMaintainlogtj failrec = new RecMaintainlogtj();
                failrec.setDsp("无");
                failrec.setPeriodinfo(periodinfo);
                failrec.setPeriodFlag(PeriodFlag);
                if(devicename!=""){
                    failrec.setDevicename(devicename);
                }else{
                    failrec.setDevicename("1");
                }
                if(deviceid!=""){
                    failrec.setDeviceid(deviceid);
                }else{
                    failrec.setDeviceid(did+"0000000000");
                }
                failrec.setTjtime(date);

                recMaintainlogtjService.save(failrec);
                List<RecMaintainlogtj> nullList = new ArrayList<>();
                PageInfo<RecMaintainlogtj> page = new PageInfo<>(nullList);
                return Result.success(page);
            }


        }




return null;








    }


}
