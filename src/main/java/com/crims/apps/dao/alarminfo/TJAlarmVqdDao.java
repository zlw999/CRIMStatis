package com.crims.apps.dao.alarminfo;

import com.crims.apps.model.alarminfo.TJAlarmVqd;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TJAlarmVqdDao {

    List<TJAlarmVqd> getAll();

    List<TJAlarmVqd> getTJAlarmByAreaID(String areaid, Date vqddate);

    List<TJAlarmVqd> getTJAlarmByAIDAndLID(String areaid, String lineid, Date vqddate);

    List<TJAlarmVqd> getTJAlarmByAIDAndLIDAndSID(String areaid, String lineid, String stationid, Date vqddate);

    List<TJAlarmVqd> getTJAlarmByAll(String areaid, String lineid, String stationid, String alarmtype, Date vqddate);

    List<TJAlarmVqd> getTJAlarmByAIDAndAType(String areaid, String alarmtype, Date vqddate);

    List<TJAlarmVqd> getTJAlarmByAIDAndLIDAndAType(String areaid, String lineid, String alarmtype, Date vqddate);

    List<TJAlarmVqd> getTJAlarmByAType(String alarmtype, Date vqddate);

}
