package com.crims.apps.dao.alarminfo;

import com.crims.apps.model.alarminfo.AlarmInfoHis;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AlarmInfoHisDao {

    List<AlarmInfoHis> getAlarmInfoHisList(Date startDate,Date endDate);

}