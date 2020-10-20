package com.crims.apps.dao.alarminfo;

import com.crims.apps.model.alarminfo.Alarminfo;
import com.crims.apps.model.alarminfo.TjAlarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AlarminfoDao {

    List<Alarminfo> findAlarm(TjAlarm tjAlarm);

    List<Alarminfo> findAlarmCondition(TjAlarm tjAlarm);
}
