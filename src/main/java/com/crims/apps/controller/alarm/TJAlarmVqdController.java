package com.crims.apps.controller.alarm;

import com.crims.apps.model.alarminfo.TJAlarmVqd;
import com.crims.apps.service.alarminfo.TJAlarmVqdService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/alarmvqd")
public class TJAlarmVqdController {

    public static Logger logger = LoggerFactory.getLogger(TJAlarmVqdController.class);

    @Autowired
    private TJAlarmVqdService tjAlarmVqdService;

    /**
     * 统计查询图诊信息
     * @param currentPage
     * @param pageSize
     * @param tjAlarmVqd
     * @return PageInfo<TJAlarmVqd>
     */
    @PostMapping("/getAlarmVqdInfo")
    @ResponseBody
    @ApiOperation(value = "统计查询图诊信息", notes = "alarmvqd_info")
    public PageInfo<TJAlarmVqd> getAlarmVqdInfo(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                               @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                String areaidSelect,String lineSelect,String stationSelect,String alarmSelect,TJAlarmVqd tjAlarmVqd) {
        try {

            PageInfo<TJAlarmVqd> pageInfo = null;

            //图诊信息诊断路数，告警路数统计

            //根据区域统计
            if("QY".equals(areaidSelect) && StringUtils.isBlank(lineSelect) && StringUtils.isBlank(stationSelect) && StringUtils.isBlank(alarmSelect)){
                pageInfo = tjAlarmVqdService.getTJAlarmByAreaID(currentPage, pageSize, tjAlarmVqd.getAreaid(), tjAlarmVqd.getVqddate());
            }

            //根据区域，线路统计
            if("QY".equals(areaidSelect) && "XL".equals(lineSelect) && StringUtils.isBlank(stationSelect) && StringUtils.isBlank(alarmSelect)){
                pageInfo = tjAlarmVqdService.getTJAlarmByAIDAndLID(currentPage, pageSize, tjAlarmVqd.getAreaid(), tjAlarmVqd.getLineid(), tjAlarmVqd.getVqddate());
            }

            //根据区域，线路，车站统计
            if("QY".equals(areaidSelect) && "XL".equals(lineSelect) && "CZ".equals(stationSelect) && StringUtils.isBlank(alarmSelect)){
                pageInfo = tjAlarmVqdService.getTJAlarmByAIDAndLIDAndSID(currentPage, pageSize, tjAlarmVqd.getAreaid(), tjAlarmVqd.getLineid(),
                        tjAlarmVqd.getStationid(), tjAlarmVqd.getVqddate());
            }

            //根据区域，线路，车站，告警类型统计
            if("QY".equals(areaidSelect) && "XL".equals(lineSelect) && "CZ".equals(stationSelect) && "GJ".equals(alarmSelect)){
                pageInfo = tjAlarmVqdService.getTJAlarmByAll(currentPage, pageSize, tjAlarmVqd.getAreaid(), tjAlarmVqd.getLineid(),
                        tjAlarmVqd.getStationid(), tjAlarmVqd.getAlarmtype(), tjAlarmVqd.getVqddate());
            }

            //根据区域，告警类型统计
            if("QY".equals(areaidSelect) && StringUtils.isBlank(lineSelect) && StringUtils.isBlank(stationSelect) && "GJ".equals(alarmSelect)){
                pageInfo = tjAlarmVqdService.getTJAlarmByAIDAndAType(currentPage, pageSize, tjAlarmVqd.getAreaid(), tjAlarmVqd.getAlarmtype(), tjAlarmVqd.getVqddate());
            }

            //根据区域，线路，告警类型统计
            if("QY".equals(areaidSelect) && "XL".equals(lineSelect) && StringUtils.isBlank(stationSelect) && "GJ".equals(alarmSelect)){
                pageInfo = tjAlarmVqdService.getTJAlarmByAIDAndLIDAndAType(currentPage, pageSize, tjAlarmVqd.getAreaid(), tjAlarmVqd.getLineid(),
                        tjAlarmVqd.getAlarmtype(), tjAlarmVqd.getVqddate());
            }

            //根据告警类型统计
            if(StringUtils.isBlank(areaidSelect) && StringUtils.isBlank(lineSelect) && StringUtils.isBlank(stationSelect) && "GJ".equals(alarmSelect)){
                pageInfo = tjAlarmVqdService.getTJAlarmByAType(currentPage, pageSize, tjAlarmVqd.getAlarmtype(), tjAlarmVqd.getVqddate());
            }

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 导出图诊信息诊断路数数据
     * @return List<TJAlarmVqd>
     */
    @PostMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "导出图诊信息诊断路数数据", notes = "alarmvqd_info")
    public List<TJAlarmVqd> getAll() {
        try {
            List<TJAlarmVqd> list = tjAlarmVqdService.getAll();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
