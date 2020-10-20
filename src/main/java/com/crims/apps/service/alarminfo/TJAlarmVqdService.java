package com.crims.apps.service.alarminfo;

import com.crims.apps.model.alarminfo.TJAlarmVqd;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface TJAlarmVqdService {

    List<TJAlarmVqd> getAll();

    PageInfo<TJAlarmVqd> getTJAlarmByAreaID(int currentPage, int pageSize, String areaid, Date vqddate);

    PageInfo<TJAlarmVqd> getTJAlarmByAIDAndLID(int currentPage, int pageSize, String areaid, String lineid, Date vqddate);

    PageInfo<TJAlarmVqd> getTJAlarmByAIDAndLIDAndSID(int currentPage, int pageSize, String areaid, String lineid, String stationid, Date vqddate);

    PageInfo<TJAlarmVqd> getTJAlarmByAll(int currentPage, int pageSize, String areaid, String lineid, String stationid, String alarmtype, Date vqddate);

    PageInfo<TJAlarmVqd> getTJAlarmByAIDAndAType(int currentPage, int pageSize, String areaid, String alarmtype, Date vqddate);

    PageInfo<TJAlarmVqd> getTJAlarmByAIDAndLIDAndAType(int currentPage, int pageSize, String areaid,String lineid, String alarmtype, Date vqddate);

    PageInfo<TJAlarmVqd> getTJAlarmByAType(int currentPage, int pageSize, String alarmtype, Date vqddate);

}
